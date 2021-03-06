/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 * On occasions creativeness might be tested by people asking that problems
 * be solved non-traditionally.  Printing numbers in sequence are a common
 * example of this type of problem.  
 * 
 * You can do this using divide and conquer strategies starting with a 
 * single method to print one number and then creating more methods to 
 * slowly increase the number of times your first method is called
 * in a manner similar to loop unrolling done by compilers but in reverse.
 * 
 * Another way is to exploit looping that is implicit in the language, for
 * example in initializers that are bundled into constructors as this 
 * example exploits.
 * 
 * And yet another way might be to use the new Java 8 streams and ranges.
 */

package com.karlmutch;

public class AvoidLoopsToPrintNumbers 
{

	@SuppressWarnings("serial")
	public static String printNumberSequences(int start, int end)
	{
		// One strategy is to avoid loops in our code but exploit them in someone else's code
		//
		// This code is not using Java 8 initializers but I imagine this type of
		// thing is scattered everywhere in newer language mechanisms
		return(new java.util.BitSet() {{ set(start, end + 1); }}.toString());
	}
}
