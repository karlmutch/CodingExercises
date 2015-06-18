/**
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 * Reworded interview problem, to protect the innocent.
 */
package com.karlmutch;

import java.util.Map;
import java.util.TreeMap;

/**
 * Web Servers and logging systems on the Internet often have to record the time taken to process requests 
 * sent by users. To record summary information about response times one can store them in a summarized form.
 * For example the response times can be counted by using rounded values for the times and then 
 * incrementing a counter associated with the rounded time. 
 * 
 * For example numeric values can be grouped into ranges such as 0-100, 100-200, etc., and counted
 * to record the number of values in each range. This type of summary is often referred to as a histogram.
 * 
 * Implement a histogram where values are bucketed into ranges of 100. E.g. all
 * values in the range 1100-1200 belong in one bucket. The result is that for efficiency, we store only
 * the count of values in each bucket. Individual values that have been observed are not explicitly recorded.
 *
 * For example, suppose we have the following collection of values:
 * 
 * 709, 521, 744, 406, 970
 * 
 * If we group by 100s, the histogram would be:
 * 
 * 400-499: 1
 * 500-599: 1
 * 700-799: 2
 * 900-999: 1
 * 
 * One nice property of a histogram is that it can be used to quickly compute percentiles. The Nth 
 * percentile of a collection of values, is the value that is larger than N% of the other values. In 
 * our example above, the 80th percentile is 744, because 20% of the values (i.e. one value) are 
 * larger than that. You can quickly (but only approximately) compute such percentiles from a histogram.
 */
public class Histogram 
{
	// We need a potentially sparse set of values to store counts of ranges of numbers
	// 
	// There are many ways to do this.  Using the assumption stated in the addValue method
	// description given to us we could use an array offset to the smallest amount but that
	// would require a bit of work to calculate offsets etc which would be the way I would do
	// it if efficiency is needed as it would be O(1) but I am going with O(log n)
	// so will stick with the TreeSet to make things simple
	private TreeMap<Integer, Integer> mHistogram;

	// To do percentiles we need a total for the entire histogram
	private int mTotalCounts;

	public Histogram() {
		mHistogram = new TreeMap<Integer, Integer>();
		mTotalCounts = 0;
	}

	/**
	 * Add a value to this histogram.
	 *
	 * @param value The numeric value to be added. You may assume that the smallest
	 *    and largest values in a Histogram will differ by no more than 10,000.
	 */
	public void addValue(double value) {
		// Determine which bucket the value is in by dividing by 100 
		int bucket = (int)(value / 100);
		
		// Get the existing value if one and then replace it.
		int count = mHistogram.containsKey(bucket) ? mHistogram.get(bucket) : 0;
		mHistogram.put(bucket, count + 1);
		
		// Increment the population
		mTotalCounts++;
	}

	/**
	 * Compute (approximately) the specified percentile of all values in this histogram.
	 *
	 * @param percent The percentile to compute. E.g. 50 --> 50th
	 *     percentile, 90 --> 90th percentile, 99.9 --> 99.9th percentile.
	 *
	 * @return An approximation of the specified percentile.
	 */
	public double computePercentile(double percent) 
	{
		// Iterate the sorted tree set and accumulate the counts we find until we get to the desire percentile
		// and then return the Key in which we find ourselves
		double desiredAmount = (percent / 100.0) * mTotalCounts;
		
		int sum  = 0 ;
		for (Map.Entry<Integer, Integer> anEntry : mHistogram.entrySet()) 
		{
			sum += anEntry.getValue();
			
			if (sum >= desiredAmount) {
				return(anEntry.getKey() * 100);
			}
			
		}
		return (0.0);
	}
}
