/**
 * This class implements a solution to the common question count
 * the number of times a digit, in this case a 1, occurs in a number.
 * 
 * This question is commonly covered in many books websites and the like.
 * 
 * By all accounts it seems to be a prevalent question with Microsoft Interviews.
 * 
 * If the interviewer asks about ones counting etc you might want to know 
 * about Hamming Weight or population count problems.  Both of which I 
 * have not dug into given I have never needed these in reality.  These thing
 * * are more often than not solved by cardinality capabilities within products
 * such as redis and in-memory data structures found in many 3rd party java 
 * packages.
 * 
 * Doing bit counters can be done in languages that have fixed width types 
 * using code such as the following 'C'.  This is similar to fixed width solutions
 * to counting problems and problems where people say, do something without using 
 * memory or registers that typically use XOR's to fold results together, I don't 
 * squirm trying to solve these as there are lots of solutions online to bit twidling
 * trying to do it yourself is way to error prone and frustrating costing time and
 * money
 *
     unsigned int input;
     unsigned int bitCount;

     bitCount = input - ((input >> 1) & 033333333333) - ((input >> 2) & 011111111111);
     bitCount = ((bitCount + (bitCount >> 3)) & 030707070707) % 63;
 * 
 * another example I have often seen uses a signed int in C which possibly 
 * would be portable to Java, but of course again it is fixed width
 * 
    x = (x & (0x55555555)) + ((x >> 1) & (0x55555555));
    x = (x & (0x33333333)) + ((x >> 2) & (0x33333333));
    x = (x & (0x0f0f0f0f)) + ((x >> 4) & (0x0f0f0f0f));
    x = (x & (0x00ff00ff)) + ((x >> 8) & (0x00ff00ff));
    x = (x & (0x0000ffff)) + ((x >> 16) & (0x0000ffff));
 */

package com.karlmutch;

import java.math.BigInteger;

public class CountOnes {

	public static int CountTheOnes(long number)
	{
		long theNumber = number;
		int numberOfOnes = 0;
		
		while (theNumber != 0) {
			if (theNumber % 10 == 1) {
				++numberOfOnes;
			}

			theNumber /= 10;
		}

		return(numberOfOnes);
	}

	public static int CountTheOnes(BigInteger number)
	{
		BigInteger maskInSingleDigits = BigInteger.TEN;
		BigInteger theNumber = number;
		int numberOfOnes = 0;
		
		while (theNumber.compareTo(BigInteger.ZERO) != 0) {
			if (theNumber.mod(maskInSingleDigits).compareTo(BigInteger.ONE) == 0) {
				++numberOfOnes;
			}
			theNumber = theNumber.divide(maskInSingleDigits);
		}

		return(numberOfOnes);
	}
}
