/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 * Accepts a number of arrays and find the shared minimum of them.
 */
package com.karlmutch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Scans two arrays looking for common elements.
 *
 * @return The lowest common element that appears in both arrays, if 
 * 		   there is none an exception will be thrown
 */
public class SharedMimimumOfArrays 
{
	public static int GetMinimum(ArrayList<Integer> array1, 
							     ArrayList<Integer> array2)  throws NoSuchElementException
	{
		Collections.sort(array1);
		Collections.sort(array2);

		Iterator<Integer> firstArrayItem = array1.iterator();
		
		do {
			Integer firstItem = firstArrayItem.next();	// throws an exception when finished 

			for (Integer secondNumber : array2) 
			{
				if (secondNumber > firstItem) {
					continue;
				}
				if (secondNumber == firstItem) {
					return(secondNumber);
				}
			}
		} while (true);
	}
}
