/**
 * A common question in interviews is to give ways to swap numbers without using
 * temporaries.
 */
package com.karlmutch;

public class OnlyInAnInterviewSwapNumbers 
{

	static void Swap(int first, int second)
	{
		// Using addition and subtraction, the problem with this is
		// that overflow is an issue
		first += second;
		second = first - second;
		first = first - second;
		
		// The ages old 'C' way
		first ^= second;
		second = first ^ second;
		first = first ^ second;
	}
}
