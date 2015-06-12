/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 * The following question was captured from a Web Site of peoples experiences with phone interviews
 * 
 * Given an unsorted array of integers, sort the array such that the numbers in it form the largest 
 * possible number, for example, given [3, 30, 9, 14, 1], the array should be sorted into 
 * [9, 3, 30, 14, 1] because 9330141 is the largest number you can form from the numbers in the array.
 * 
 */
package com.karlmutch;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CustomSortSequence 
{
	/**
	 * Given an unsorted array of integers, sort the array such that the numbers in it form the largest 
	 * possible number, for example, given [3, 30, 9, 14, 1], the array should be sorted into 
	 * [9, 3, 30, 14, 1] because 9330141 is the largest number you can form from the numbers in the array.
	 * 
	 * @param unsorted The unsorted input of integer to be sorted in descending order then by ascending order 
	 * 
	 * @return A collection of integers that when appended will be the largest value possible.
	 */
	static public List<Integer> SortForLargestInteger(List<Integer> unsorted)
	{
		// Implement a custom sort functor to handle the required sort order needed for the example
		class CustomSortComparator implements Comparator<Integer> 
		{
			@Override
			public int compare(Integer o1, Integer o2) 
			{
				String inOrder = o1.toString() + o2.toString(); 
				String outOfOrder = o2.toString() + o1.toString(); 
				return (outOfOrder.compareTo(inOrder));
			}
		}

		// Use a temporary to do the sort and then return the result which was sorted in place
		List<Integer> workingList = unsorted;
		Collections.sort(workingList, new CustomSortComparator());

		return (workingList);
	}
}
