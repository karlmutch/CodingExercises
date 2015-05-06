/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 *
 * This problem comes from a Google+ group and was described as
 * 
 * You have an array of integers, and for each index you want to find the product of every integer except 
 * the integer at that index. Write a function get_products_of_all_ints_except_at_index() that takes an 
 * array of integers and returns an array of the products.
 * 
 **/
package com.karlmutch;

import java.math.BigInteger;
import java.util.ArrayList;

public class ArrayProductExceptIndex 
{

	public static BigInteger [] get_products_of_all_ints_except_at_index(BigInteger [] input)
	{
		ArrayList<BigInteger> productResults = new ArrayList<BigInteger>(input.length);
		
		BigInteger trackedProduct = BigInteger.ONE;
		
		// Go forwards using lookaside to store previous products
		for (BigInteger anItem : input) {
			BigInteger nextProduct = trackedProduct.multiply(anItem);
			productResults.add(trackedProduct);
			trackedProduct = nextProduct;
		}
		
		trackedProduct = BigInteger.ONE;

		// Having done the products going from front to back we still need to do them from back to
		// the front to ensure every item includes multiplied items after it as well as before it.
		for ( int backIndex = input.length - 1 ; backIndex >= 0 ; --backIndex)
		{
			productResults.set(backIndex, productResults.get(backIndex).multiply(trackedProduct));
			trackedProduct = trackedProduct.multiply(input[backIndex]);
		}
		return(productResults.toArray(new BigInteger[input.length]));
	}
}
