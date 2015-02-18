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
 * Why such as simple question for coding interviews, this is the logical equivalent of asking someone
 * to string a sentence together for simple literacy.  A coding horror article,
 * http://blog.codinghorror.com/why-cant-programmers-program, explains the original
 * inspiration for why this test exists.
 *
 */
package com.karlmutch;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.IntStream;

public class FizzBuzz 
{
	
	public static List<String> DoFizzBuzz(int startingNumber, int endingNumber)
	{
		List<String> results = new ArrayList<String>();
		
		IntStream.rangeClosed(startingNumber, endingNumber).forEach(aNumber -> results.add(DoFizzBuzz(aNumber)));

		return(results);
	}
	
	public static String DoFizzBuzz(int aNumber) 
	{
		if (aNumber % (5 * 3) == 0) {
			return("FizzBuzz");
		}
		else {
			if (aNumber % 3 == 0) {
				return("Fizz");					
			}
			else {
				if (aNumber % 5 == 0) {
					return("Buzz");					
				}
				else {
					return(Integer.toString(aNumber));
				}
			}
		}
	}

}
