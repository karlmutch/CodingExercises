/**
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

public class LookasideFibonacci 
{
	/**
	 * Retrieves the n'th Fibonacci number
	 * 
	 * @param number Which of the numbers from the Fibonacci sequence is wanted
	 * 
	 * @return The n'th number from the sequence
	 */
	public static BigInteger get(int number)
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

		return(get(number - 1).add(get(number - 2)));
	}

	public static BigInteger getWithLookaside(int number)
	{
		if (mCache.size() < number)
		{
			// Calculate a result and then cache it for later use
			mCache.add(number, getWithLookaside(number - 1).add(getWithLookaside(number - 2)));
		}
		return(mCache.get(number));
	}

	// Cheap synchronization to ensure thread safety
	public static List<BigInteger> mCache = Collections.synchronizedList(new ArrayList<BigInteger>());

	// Use a static initializer block to load the first few items into the cache which avoids having 
	// special case code in the recursive generator
	static {
		mCache.add(BigInteger.ZERO);
		mCache.add(BigInteger.ONE);
		mCache.add(BigInteger.ONE);
	}
}
