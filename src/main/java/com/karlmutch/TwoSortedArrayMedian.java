/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 * Extract the Median value of two previously sorted arrays
 * 
 * A binary search problem that can easily be done using iterative methods but can be expensive to
 * do so on very large data sets. 
 *
 */
package com.karlmutch;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class TwoSortedArrayMedian 
{

    static BigInteger getkth(List<BigInteger> shorterArray, List<BigInteger> longerArray, int k)
    {
        // Ensure that the length of the first array is always the smaller of the two
        if (shorterArray.size() > longerArray.size()) {
            return getkth(longerArray, shorterArray, k);
        }
        
        // If the smaller array has nothing in it then we know the mid point of the 
        // larger has our desired median value
        if (shorterArray.size() == 0) {
            return longerArray.get(k - 1);
        }

        // If we only have a count of 1 items left to compare from each array then we know
        // the minimum value is the desired one and when returning the caller can split the 
        // difference through division
        if (k == 1) {
        	// Equivalent to min function in c++
            return ((1 != shorterArray.get(0).compareTo(longerArray.get(0))) ? shorterArray.get(0) : longerArray.get(0));
        }

        int newMidPoint = k / 2;
        int i = (shorterArray.size() < newMidPoint) ? shorterArray.size() : newMidPoint;
        int j = (longerArray.size() < newMidPoint) ? longerArray.size() : newMidPoint;

        if (shorterArray.get(i - 1).compareTo(longerArray.get(j - 1)) == 1) 
        {
        	// if the smaller sized array's mid point is larger than the larger arrays mid point
            return getkth(shorterArray, longerArray.subList(j, longerArray.size()), k - j);
        }
        else {
        	// And now the case where the smaller array can be chopped 
            return getkth(shorterArray.subList(i, shorterArray.size()), longerArray, k - i);
        }
    }

	public static BigInteger GetMedian(ArrayList<BigInteger> array1, 
		     						   ArrayList<BigInteger> array2)
	{
		// This solution uses a binary search method that splits itself across
		// the two sorted arrays with a left side and a right side
	    int leftEnd = (array1.size() + array2.size() + 1) >> 1;
        int rightStart = (array1.size() + array2.size() + 2) >> 1;

        return(getkth(array1, array2, leftEnd).add(getkth(array1, array2, rightStart)))
        			.divide(BigInteger.valueOf(2));
	}
}