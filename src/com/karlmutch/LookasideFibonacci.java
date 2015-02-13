/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 * Often interview questions might drift to the topic of performing iterative
 * operations, then converting them to recursive, and then attacking performance
 * issues by using already completed results.
 * 
 * This code example generates Fibonacci numbers and then stores any progress
 * in a lookaside data structure for later use should results need to be repeated.
 * 
 * TODO Testing is needed
 */

package com.karlmutch;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.concurrent.atomic.AtomicLong;

public class LookasideFibonacci 
{
	public LookasideFibonacci()
	{
		// Initializers to load the first few items into the cache which avoids having 
		// special case code in the recursive generator
		mCache.add(BigInteger.ZERO);
		mCache.add(BigInteger.ONE);
		mCache.add(BigInteger.ONE);
	}

	/**
	 * Retrieves the n'th Fibonacci number
	 * 
	 * @param number Which of the numbers from the Fibonacci sequence is wanted
	 * 
	 * @return The n'th number from the sequence
	 */
	public BigInteger get(int number)
	{
		if (number < 3) 
		{
			if (number < 1)
			{
				throw new IllegalArgumentException("The Fibonacci sequence only begins with a 1'st element."
												   + "  The number requested does not exist");
			}
			return(BigInteger.valueOf(1));
		}

		mCountCalcs.incrementAndGet();

		return(get(number - 1).add(get(number - 2)));
	}

	public BigInteger getWithLookaside(int number)
	{
		if (mCache.size() <= number)
		{
			// Calculate a result and then cache it for later use
			mCache.add(number, getWithLookaside(number - 1).add(getWithLookaside(number - 2)));

			mCountCalcs.incrementAndGet();
		}
		return(mCache.get(number));
	}

	// While this wont make the counter logically safe for threaded used
	// making it atomic will at least allow multiple threads to
	// manipulate it without 
	public AtomicLong mCountCalcs = new AtomicLong(0);
	
	// Cheap synchronization to ensure thread safety
	private List<BigInteger> mCache = Collections.synchronizedList(new ArrayList<BigInteger>());
}
