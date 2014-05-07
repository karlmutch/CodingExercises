/**
 * Recursive coding example.  This is the type of question asked during interviews
 * where the interview asks do X without using variables.  It is artificial for most languages
 * as the stack consumes space in any event and is really only useful in languages
 * that have tail recursion optimization implemented.
 * 
 * Without tail recursion optimization these types of approaches often fall down due to overhead.
 * 
 * Getting into the recursion rathole is a problem because very few problems at scale are
 * ever solved by recursion and those that are are very rarely exposed above the level of
 * API's and components.  Recursion as a technique during interview questions is typically
 * successful as a form of memory exercise for the candidate because it is so rarely used
 * in execution of ones job.  Lambda expressions and the like however are becoming more
 * prevalent, for example Java 8 Streams.
 */

package com.karlmutch;

public class OnlyInAnInterviewStrings {

	public static String recurseReverse(final String input)
	{
		if (input.length() < 2) {
			return(input);
		}
		return recurseReverse(input.substring(1) + input.substring(0, 1));
	}
}
