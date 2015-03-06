/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 * Power functions are sometimes asked about in interviews.  Often specific methods of
 * calculations such as power functions have implementations beyond the simple algorithm
 * used to define the function.
 * 
 * For example method of obtaining square roots, and this the power function.  Power functions
 * can be calculated using repeated squares.  This is generally efficient when dealing with 
 * powers larger than 4.
 * 
 * Function exp-by-squaring(x,n)
     if n<0 then return exp-by-squaring(1/x, -n);
     else if n=0 then return 1;
     else if n=1 then return x;
     else if n is even then return exp-by-squaring(x2, n/2);
     else if n is odd then return x * exp-by-squaring(x2, (n-1)/2).
 * 
 * 
 **/
package com.karlmutch;

import java.math.BigInteger;

public class Exponents 
{
	// Recursive solution to computer power functions
	public static BigInteger pow(BigInteger number, BigInteger power)
	{
		// Handle cases where the power is a negative number, this has an issue with floats and
		// will need testing as I suspect it will not work
		if (power.compareTo(BigInteger.ZERO) == -1) {
			return(Exponents.pow(BigInteger.ONE.divide(number), power.negate()));
		}
		
		switch (BigInteger.ONE.compareTo(power)) {
			case 1 : {
				// The power was 0
				return(BigInteger.ONE);
			}
			case 0 : {
				// The power was 1
				return(number);
			}
		}

		if (power.testBit(0)) {
			// The number is ODD
			//
			//x * pow(square(x), ((power-1) / 2))
			return (number.multiply(
						pow(number.multiply(number), 
							power.subtract(BigInteger.ONE).divide(BigInteger.valueOf(2)))));
		}
		else {
			// The number is EVEN
			//
			//x * pow(square(x), (power / 2))
			return(pow(number.multiply(number), power.divide(BigInteger.valueOf(2))));
		}
	}
}
