/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 * A trivially simple Random number generator wrapper than when used can supply numbers 
 * provided by a third party and upon exhausting these return to using the default java.util.Random()
 * generator.
 * 
 * This class is intended for test purposes alone.
 */
package com.karlmutch;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("serial")
public class Random extends java.util.Random
{
	static ConcurrentLinkedQueue<Double> mTestData = new ConcurrentLinkedQueue<Double>();

	static public void supplyTestData(List<Double> testData)
	{
		// Ensure data from old test cases has been removed
		mTestData.clear();

		mTestData.addAll(testData);
	}

	@Override
	public double nextDouble() 
	{
		Double testData = mTestData.poll();
		
		if (null == testData) {
			testData = ThreadLocalRandom.current().nextDouble();
		}
		
		return(testData);
	}

	static public double random()
	{
		return(new Random().nextDouble());
	}
}
