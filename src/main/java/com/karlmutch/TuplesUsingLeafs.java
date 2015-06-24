/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 *
 * Iteration questions seem to be gaining in popularity as they test candidates abilities
 * to think about traversing exotic data structure.  Some of these problem are extremely
 * difficult to do without using recursion and are pretty macho.  Speaking to one ex-googler who
 * had done many many interviews it seems the success rates for interviewees to complete these
 * types of exercises is about 10%.
 * 
 * This specific exercise is to traverse vectors of values and create tuples from each permutation
 * of the items within the vectors.  For example three arrays of values as follows 
 * 
 *  A = [1,2,3]
 *  B = [4,5]
 *  C = [6,7,8]
 *  
 *  Using just A and B tuples of length 2 would be produced as follows:
 *  
 *  A*B = [(1,4), (1,5), (2,4), (2,5), (3,4), (3,5)]
 *  
 *  Using A, B and C results in tuples of length 3 as follows
 *  
 *  A*B*C = [(1,4,6), (1,4,7), (1,4,8), ..., (3,5,8)]
**/
package com.karlmutch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TuplesUsingLeafs 
{
	static public String descender(List<Integer> leftHandSide, 
								   List<List<Integer>> rightHandSide)
	{
		if (rightHandSide.isEmpty())
		{
			return(new String("( " + leftHandSide.stream().map(Object::toString).collect(Collectors.joining(", "))) + " )");
	    }
	  
		String result= new String();
	    List<List<Integer> > newRightHandSide = rightHandSide.subList(1, rightHandSide.size());

	    for (Integer anItem : rightHandSide.get(0))
	    {
	      List<Integer> newLeftHandSide = new ArrayList<Integer>();
	      newLeftHandSide.addAll(leftHandSide);
	      newLeftHandSide.add(anItem);
	      result += descender(newLeftHandSide, newRightHandSide);
	    }
		return(result);
	}
}
