/**
 * This is a common question with people that appreciate tricks and tips behind
 * problems.  These tips and tricks are rendered largely redundant especially
 * in Java where BigInteger has a method, isProbablyAPrime.  Also an optimization
 * of how it is done within this class is to make use of the nextProbablePrime
 * method also found inside the BigInteger class to deal with iteration across
 * ranges.
 * 
 * Being told to code up something like this to see how one thinks is a red
 * flag, unless it purely intended to get you writing java statements on a whiteboard
 * in a syntax familiarity exercise.
 * 
 * This example has some Java 8 streams example code and also traditional
 * iterative coding that is not explicitly parallel.
 * 
 * Common questions following implementing this code that follow is
 * how to cache results that have been previously calculated etc.  This can 
 * be done using high and low water marks etc
 * 
 * There are many more ways to implement testing of ranges especially within 
 * smaller ranges by using sieves of which there are many types but for this example
 * the assumption is that we will use iteration and use the Java isProbablePrime to
 * get approximate results and then use brute force but setting the limit up to 
 * which we test division of preceding integers by using sqrt(n) as our upper limit
 * and eliminating evens.
 * 
 * TODO Testing is not yet done, strawman implementation
 * 
 */

package com.karlmutch;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

/**
 * This class can be used to specify a range of numbers from which the list of primes is desired.
 * 
 * It does not use the best approach but is good enough without pretending.  If you wish to
 * used a production prime library then going to crypto and other code libraries is a much more
 * solid approach.
 *
 */

public class FindPrimes {

	private BigInteger mStarting = BigInteger.valueOf(2);
	private BigInteger mTerminating = BigInteger.valueOf(100);
	
	ArrayList<BigInteger> mCandidates;

	public FindPrimes(final BigInteger startingNumber, final BigInteger terminatingNumber)
	{
		mStarting = startingNumber;
		mTerminating = terminatingNumber;
		BigInteger difference = mTerminating.subtract(mStarting);
		if (difference.compareTo(BigInteger.ZERO) < 0) {
			return;
		}

		// Java 8 streams based implementation, I am still churning through learning the new
		// API's, this took me about 15 minutes to put together using online documentation so
		// it is not yet something I throw together from memory or instinctively but I am getting
		// there
		Stream<BigInteger> candidates = Stream.iterate(mStarting, n -> n.add(BigInteger.ONE)).
											   limit(difference.longValue()).
											   parallel().
											   filter(n -> n.isProbablePrime(1));

		// Streams are consumed so once this is done there is nothing left in the candidates stream
		candidates.forEach(n -> mCandidates.add(n));
	}
	
	// The BigInteger package does not have a sqrt so we have a 'good enough' method here that will bias on the high side
	//
	// Other methods are available using approximation etc and a good starting point to read about these can be found 
	// here http://web.archive.org/web/20110714075116/http://www.merriampark.com/bigsqrt.htm.
	//
	// I am not a professional mathematician so wont do much more than stop here and say that if one is a domain where
	// this is really important them domain experts should be consulted
	private static BigInteger sqrt(BigInteger n) 
	{
		BigInteger a = BigInteger.ONE;

		BigInteger b = new BigInteger(n.shiftRight(5).add(new BigInteger("8")).toString());

		while (b.compareTo(a) >= 0) 
		{
			BigInteger mid = new BigInteger(a.add(b).shiftRight(1).toString());
			if (mid.multiply(mid).compareTo(n) > 0) {
				b = mid.subtract(BigInteger.ONE);
			}
			else {
				a = mid.add(BigInteger.ONE);
			}
		}
		return a.add(BigInteger.ONE);
	}

	private static boolean isAPrime(BigInteger aCandidate)
	{
		final BigInteger even = BigInteger.valueOf(2);
		final BigInteger three = BigInteger.valueOf(3);
		
		if ((0 == aCandidate.mod(even).compareTo(BigInteger.ZERO)) ||
			(0 == aCandidate.mod(three).compareTo(BigInteger.ZERO)))
		{
			return(false);
		}

		if (1 != aCandidate.compareTo(three)) {
			if (0 == aCandidate.compareTo(BigInteger.ONE)) {
				return(false);
			}
			return(true);
		}

		// One optimization at this point is to record the primes we have seen so far into a concurrent collection
		// and first divide again those, but lets just use brute force, no whiteboard can fit optimal solutions 
		// in any event
		BigInteger divisor = BigInteger.valueOf(5);
		final BigInteger stoppingPoint = sqrt(aCandidate);
		while (divisor.compareTo(stoppingPoint) > 0)
		{
			if (aCandidate.mod(divisor).compareTo(BigInteger.ZERO) == 0) {
				return(false);
			}
		}

		return(true);

	}

	public ArrayList<BigInteger> getPrimes()
	{
		ArrayList<BigInteger> actuals = new ArrayList<BigInteger>();

		// The list of candidates that we will now be checking long form
		// have come from a potential parallel implementation which could
		// cause issues for calling clients that assume tight ordering so I will
		// sort the inputs before beginning
		
		Collections.sort(mCandidates);
		
		ArrayList<BigInteger> results = new ArrayList<BigInteger>();
		
		// Switch back to a more traditional non streaming implementation
		for (BigInteger aCandidate : mCandidates) 
		{
			if (isAPrime(aCandidate)) {
				actuals.add(aCandidate);
			}
		}

		return(results);
	}
}
