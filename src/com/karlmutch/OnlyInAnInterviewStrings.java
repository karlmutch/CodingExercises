/**
 * Recursive coding example.  This is the type of question asked during interviews
 * where the interview asks do X without using variables.  It is artificial for most languages
 * as the stack consumes space in any event and is really only useful in languages
 * that have tail recursion optimization implemented.
 * 
 * Without tail recursion optimization these types of approaches often fall down due to overhead.
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
