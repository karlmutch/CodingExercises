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
		
		BigInteger masterProduct = BigInteger.ONE;
		for (BigInteger anItem : input) {
			masterProduct = masterProduct.multiply(anItem);
		}
		for (BigInteger anItem : input) {
			productResults.add(masterProduct.divide(anItem));
		}
		return(productResults.toArray(new BigInteger[input.length]));
	}
}
