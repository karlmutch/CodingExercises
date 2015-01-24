/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 * FizzBuzz, while I heard rumors people do ask about it I had never encountered the 
 * occasion until recently so here it is.
 * 
 * This is a simple problem but one that requires some analysis to realize that there
 * is an overlap, common factors in the case of FizzBuzz, between conditions that the 
 * interviewee needs to both detect and address.
 *
 */

package com.karlmutch;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class FizzBuzz {
	
	public static ArrayList<String> DoFizzBuzz(int startingNumber, int endingNumber)
	{
		ArrayList<String> results = new ArrayList<String>();
		
		IntStream.rangeClosed(startingNumber, endingNumber).forEach(aNumber -> 
		{
			if (aNumber % (5 * 3) == 0) {
				results.add("FizzBuzz");
			}
			else {
				if (aNumber % 3 == 0) {
					results.add("Fizz");					
				}
				else {
					if (aNumber % 5 == 0) {
						results.add("Buzz");					
					}
					else {
						results.add(Integer.toString(aNumber));
					}
				}
			}
		});

		return(results);
	}

}
