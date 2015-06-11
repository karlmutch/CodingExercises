/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 * The Distributed Top-K problem is a Google Interview question.
 * 
 * The objective of this question is to test candidates to see how they go about a divide and conquer
 * solution that requires partitioning a problem in two steps and recombining the problem.
 * 
 * Problem Statement
 * -----------------
 * 
 * Given a distributed system observing a very large number of HTTP requests determine 
 * the Top-K of all observed URLs. 
 * 
 * Some approach to solve the problem require multiple passes of the requests and
 * merging totals into a single master machine then calculating a cut off for URLs 
 * that could be evenly distributed across the machines and not fully visible to
 * the master.  Multiple passes however to recount values that are not clustered
 * on a single machine are expensive and would require machines in our cluster to
 * re-process data resulting in a solution that does not meet our requirements.
 * 
 * Our Solution
 * ------------
 * 
 * The approach I am taking to solving this problem is to partition data as our first step and
 * route URLs to specific nodes in our cluster as they are being consumed by any arbitrary host.  
 * 
 * As URLs are seen on individual nodes in our cluster the node can capture the Top-K using a
 * non distributed technique.  Also a query to every node can yield the top-K for each then simply 
 * sorting the list is sufficient to get the Top-K using a single request to each node.
 * 
 * For the purposes of handling the Top-K within a single host I am going to use a library
 * from Clearspring Technologies, Inc.  The scope of doing streaming arbitrary numbers of items
 * through Top-K is beyond the scope of this set of coding examples.
 * 
 * Count-Min Sketch uses the properties of filters and hashing to perform approximations of Top-K
 * with excellent characteristics.  The seminal paper that described this technique is 
 * An Improved Data Stream Summary: The Count-Min Sketch and its Applications
 * http://www.eecs.harvard.edu/~michaelm/CS222/countmin.pdf
 * 
 * So the steps in this example will be
 * 
 * 	1.  Create N hosts modeled as threads
 * 
 *  2.  Receive messages at random on any of N threads, hash and mod the result to determine 
 *      which N host has the responsibility for processing that message
 *  
 *  3.  Query each of the N hosts for their respective Top-K data
 *  
 *  4.  Collate results to determine cluster wide Top-K at point of query
 *
 * While this method does not give accurate integers counts or occurrences of terms it is found
 * like many other techniques to be very solid in the presence of data which has power-law like
 * frequencies. 
 */

package com.karlmutch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.clearspring.analytics.stream.ConcurrentStreamSummary;
import com.clearspring.analytics.stream.ScoredItem;

// Guava is used to provide a hashing function which is central to the design but not
// to the intent of the question which was to write the application layer code
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class DistributedTopK 
{
	private int mHosts;
	private int mK;

	// The murmur 3 hash is used to generate hashing that will be used to assign ownership
	// of values destined for Top-K to our simulated hosts
	HashFunction mHashingForHostAssignment = Hashing.murmur3_32();

	static private AtomicBoolean mStop = new AtomicBoolean(false);
	
	// For information about the reasons for choosing this implementation please
	// see http://stackoverflow.com/questions/1301691/java-queue-implementations-which-one
	private List<ConcurrentLinkedQueue<String>> mWorkQueues;
	
	private ForkJoinPool mForkJoinPool; 

	// Used to create sort collections of the TopK results
	class TopKSorter implements Comparator<ScoredItem<String>> 
	{
	    @Override
	    public int compare(ScoredItem<String> left, ScoredItem<String> right) {
	    	// Switch them up to get the items in descending order
	        int result = Long.compare(right.getCount(), left.getCount());
	        
	        if (result == 0) {
	        	return(right.getItem().compareTo(left.getItem()));
	        }

	        return(result);
	    }
	}

	// Create a priority sorted collection to hold the topK results.  This will be written too by threads
	// when they terminate and so I made thread safe at the method level.  The performance hit
	// is not a great concern for our proof of concept.
	private List<ScoredItem<String>> mResults = 
			Collections.synchronizedList(new ArrayList<ScoredItem<String>>());

	static private AtomicInteger mActiveThreads = new AtomicInteger(0);

	/**
	 * 
	 * @param simulatedCountHosts
	 * @param K
	 */
	public DistributedTopK(int simulatedCountHosts, int K)
	{
		mHosts = simulatedCountHosts;
		mK = K;
		mWorkQueues = new ArrayList<ConcurrentLinkedQueue<String>>(mHosts);

		for (int i = 0 ; i < mHosts ; ++i) {
			mWorkQueues.add(new ConcurrentLinkedQueue<String>());			
		}

		// Set our thread counter to record how many simulated hosts we have running in the thread pool
		mActiveThreads.set(mHosts);
		mForkJoinPool = new ForkJoinPool(simulatedCountHosts + 1);

		// Start a number of threads, one for each simulated host and have them
		// use the process loop
		mForkJoinPool.submit(() -> 
		Arrays.stream(IntStream.rangeClosed(0, mHosts-1).toArray())
		      .parallel()
		      .forEach((i) -> { Process(mWorkQueues.get(i), mK, mResults); }));

		// Having started the worker threads for our hosts we return to the caller
		// who in turn will send data into this instance and when completed will
		// stop any processing and retrieve the Top-K
	}

	/**
	 * Method that processes traffic from a queue and that simulates one of the hosts in
	 * our distributed cluster.
	 * 	
	 * @param workQueue
	 * @param k
	 * @param results
	 */
	static public void Process(ConcurrentLinkedQueue<String> workQueue, 
							   int k, List<ScoredItem<String>> results)
	{
		ConcurrentStreamSummary<String> urlStream = new ConcurrentStreamSummary<String>(k);

		do 
		{
			String message = workQueue.poll();
			
			if (null == message) {

				if (mStop.get()) { break ; }
				
				try {
					// A busy wait which would not normally be used in production code but
					// used here to simplify the coding example for which this is not the
					// principle concern / objective.
					 Thread.sleep(250) ;
				}
				catch (Exception ignoredException) { }
			}
			else {
				urlStream.offer(message);
			}
		} while (true);

		results.addAll(urlStream.peekWithScores(k));

		// Decrement the counter used by the main to determine when threads have all completed
		mActiveThreads.decrementAndGet();
	}

	/**
	 * 
	 * @param aMessage
	 */
	public void ProcessData(final String aMessage) 
	{
		// Locate the host that has the responsibility for counting this value, first
		// by generating a hash of the value then using a mod operation to assign
		// to one of the known hosts in our simulated cluster
		int hash = Math.abs(mHashingForHostAssignment.newHasher().
											 putUnencodedChars(aMessage).
											 hash().asInt());
		ProcessData(hash % (mHosts - 1), aMessage);
	}

	/**
	 * 
	 * @param host
	 * @param aMessage
	 */
	public void ProcessData(int host, final String aMessage) 
	{
		mWorkQueues.get(host).add(aMessage);
	}

	/**
	 * 
	 * @return
	 */
	public List<ScoredItem<String>> StopAndGetTopK() 
	{
		mStop.set(true);

		// Wait for the hosts to all complete their work
		
		// A busy wait which would not normally be used in production code but
		// used here to simplify the coding example for which this is not the
		// principle concern / objective.
		while (0 != mActiveThreads.get()) { 
			try {
				Thread.sleep(250) ;
			}
			catch (Exception ignoredException) {}
		}

		// Sort using the count in descending order which is done using the Comparator
		Collections.sort(mResults, new TopKSorter());
		
		// Return only the TopK Slice
		return(mResults.subList(0, mK));
	}
}
