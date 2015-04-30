/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 * A common question to test knowledge of stacks is to have a candidate test a string
 * for balanced parentheses.  This examples show this example. 
 */
package com.karlmutch;

import java.util.HashMap;
import java.util.Stack;

public class BalancedParens 
{
	/**
	 * This method checks a string to ensure that there are balanced parentheses 
	 * and returns the position of any invalid parentheses
	 * 
	 * @param expression A string containing parentheses along with any other characters that will be ignored.
	 * 
	 * @return An integer indicating the starting, or ending unbalanced parentheses.
	 * 		   If the parentheses are balanced a value of -1 will be returned. 
	 * 		   If the input was invalid -2 will be returned
	 */
	static public int CheckParens(final String expression)
	{
		if (null == expression) {
			return(-2);
		}
		
		@SuppressWarnings("serial")
		HashMap<Character, Character> startParentheses = new HashMap<Character, Character>() {{
			put('{','}');
			put('[',']');
			put('(',')');
		}};

		@SuppressWarnings("serial")
		HashMap<Character, Character> endParentheses = new HashMap<Character, Character>() {{
			put('}','{');
			put(']','[');
			put(')','(');
		}};

		class Token {
			public Token(int position, Character parentheses)
			{
				mPosition = position;
				mParentheses = parentheses;
			}

			public final int mPosition;
			public final Character mParentheses;
		};
		
		Stack<Token> parenthesesHistory = new Stack<Token>();
		
		int stringPosition = 0;
		for (Character character : expression.toCharArray())
		{
			++stringPosition;
			// If we have an opening brace save it for later
			if (startParentheses.containsKey(character)) {
				parenthesesHistory.add(new Token(stringPosition, character));
				continue;
			}

			// When encountering a closing brace check that it balances the opener
			if (endParentheses.containsKey(character)) {
				Token matching = parenthesesHistory.pop();
				// Underflowed on braces
				if (null == matching) {
					return(stringPosition);
				}
				// Check that the closing brace matched the open brace type
				if (matching.mParentheses.equals(startParentheses.get(character))) {
					return(matching.mPosition);
				}
			}
		};

		// Handle the case where there are too many opening braces
		if (!parenthesesHistory.isEmpty()) {
			// Get the first orphaned brace from the bottom of the stack
			// and use that as our error case
			return(parenthesesHistory.lastElement().mPosition);
		}

		// If we get there it means we have a completely balanced set of braces
		return(-1);
	}
}
