/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 * Odd Man out problems are common questions for testing knowledge of
 * set and collection operations.  Fairly useful but the solutions to
 * these problems are best solved using utility libraries such as 
 * Guava Collections2 operations for set like joins, unions and the like
 */
package com.karlmutch;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class OddManOut 
{

	/**
	 * Method for quickly checking if an array has duplicates
	 * 
	 * @return true if there was a duplicate discovered
	 */
	public static boolean hasDuplicates(final String [] itemsToCheck)
	{
		final Set<String> uniqueItems = new HashSet<String>(Arrays.asList(itemsToCheck));
		
		return(uniqueItems.size() != itemsToCheck.length);
	}

	/**
	 * This version might be faster if you are lucky enough to get a duplicate early,
	 * or you know the duplicates are very common and you are likely to encounter
	 * them early.
	 * 
	 * @return True if there is a duplicate
	 */
	public static boolean hasDuplicatesUsingIteration(final String [] itemsToCheck)
	{
		Set<String> itemsSeen = new HashSet<String>();
		for (String anItem : itemsToCheck)
		{
			if (!itemsSeen.add(anItem))
			{
				return(true);
			}
		}
		return(false);
	}	

	/**
	 * Method for stripping out any duplicates using a HashSet that constrains
	 * values to being unique
	 */
	public static String [] stripDuplicates(final String [] itemsToCheck)
	{
		return(new HashSet<String>(Arrays.asList(itemsToCheck))
					.toArray(new String[0]));
	}

	/**
	 * Method for stripping out any duplicates using a HashSet that constrains
	 * values to being unique
	 */
	public static String [] stripStableDuplicates(final String [] itemsToCheck)
	{
		return(new LinkedHashSet<String>(Arrays.asList(itemsToCheck))
					.toArray(new String[0]));
	}

}
