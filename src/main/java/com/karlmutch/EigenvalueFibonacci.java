/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 * This code example generates Fibonacci numbers using Eigenvalues as described in
 * http://scottsievert.github.io/blog/2015/01/31/the-mysterious-eigenvalue/. I dont
 * expect I'll find this in a technical interview but its really interesting so thought
 * I'd test the notion.
 * 
 */

package com.karlmutch;

import java.math.BigInteger;

public class EigenvalueFibonacci 
{
	/**
	 * Retrieves the n'th Fibonacci number
	 * 
	 * @param number Which of the numbers from the Fibonacci sequence is wanted
	 * 
	 * @return The n'th number from the sequence
	 * 
	 * TODO Add a power function for BigInteger
	 */
	public static BigInteger get(BigInteger n)
	{
	    double lambda1 = (1 + Math.sqrt(5)) / 2;
	    double lambda2 = (1 - Math.sqrt(5)) / 2;

	    return BigInteger.valueOf((long)((Math.pow(lambda1, n.longValueExact()) - 
	    								  Math.pow(lambda2, n.longValueExact())) / Math.sqrt(5)));
	}
}
