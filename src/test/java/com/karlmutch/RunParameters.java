/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 */
package com.karlmutch;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RunParameters 
{
	// This inner class should be using javatuple.org however this project undertakes to
	// do everything using vanilla JDK and so we write the Pair class ourselves
	static public class Pair <T, U>
	{
	   private final T mFirst;
	   private final U mSecond;

	   public Pair( T f, U s )
	   {
		   mFirst = f;
		   mSecond = s;
	   }

	   public T getFirst()
	   {
		   return mFirst;
	   }
	   public U getSecond()
	   {
		   return mSecond;
	   }

	   @Override
	   public int hashCode()
	   {
		   return((mFirst == null? 0 : mFirst.hashCode() * 31) + (mSecond == null? 0 : mSecond.hashCode()));
	   }

	   public boolean equals( Object right )
	   {
			if ( this == right )
			{
			  return true;
			}
			if ( right == null || !(getClass().isInstance( right )) )
			{
			  return false;
			}
			
		    try {
			    @SuppressWarnings("unchecked")
				Pair<T, U> other = getClass().cast( right );
	
			    return ((mFirst == null) ? other.mFirst == null : mFirst.equals( other.mFirst )) &&
			    	   ((mSecond == null) ? other.mSecond == null : mSecond.equals( other.mSecond ));
		    }
		    catch (Exception exception) {
		    	return(false);
		    }
	   }
	}

	public Optional<BigInteger> 	mRangeStart;
	public Optional<BigInteger> 	mRangeEnd;

	public Optional<BigInteger> 	mNumber;
	
	public Optional<String>	mString;
	public Optional<List<String> >	mStrings;
	
	public Optional<List<BigInteger> >	mIntegers;

	public Optional<ArrayList<ArrayList<BigInteger>>> mIntegerSeries;

	public Optional<ArrayList<Pair<String, String>>> mKeyValuePairs;
}
