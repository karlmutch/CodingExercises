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
