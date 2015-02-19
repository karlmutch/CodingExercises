/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 **/
package com.karlmutch;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SimpleCalculator 
{
	public static float performSimpleCalculation(final String expression)
	{
		Deque<String> itemsInExpression = new ArrayDeque<String>();

		itemsInExpression.addAll(Arrays.asList(expression.split(" ")));

		Float operand = Float.parseFloat(itemsInExpression.pop());

		// First argument in expression must be an operand
		do 
		{
			String operator = itemsInExpression.pop();

			switch (operator) {
				case "+" : {
					operand = operand + Float.parseFloat(itemsInExpression.pop());
					break;
				}
				case "-" : {
					operand = operand - Float.parseFloat(itemsInExpression.pop());
					break;
				}
				case "*" : {
					operand = operand * Float.parseFloat(itemsInExpression.pop());
					break;
				}
				case "/" : {
					operand = operand / Float.parseFloat(itemsInExpression.pop());
					break;
				}
				default : {
					throw new UnsupportedOperationException();
				}
			}
			
		} while (!itemsInExpression.isEmpty());

		return(operand);
	}
}
