/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 */
package com.karlmutch.steps;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import com.karlmutch.InsertionSort;
import com.karlmutch.IterableWithPeek;
import com.karlmutch.RunParameters;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When; 

public class IteratorWithPeekTestSteps 
{
	private final RunParameters mParameters;

	private TreeSet<Integer> mPeekPoints = new TreeSet<Integer>();
	private ArrayList<String> mResult = new ArrayList<String>();

	public IteratorWithPeekTestSteps(RunParameters parameters)
	{
		mParameters = parameters;
	}
	
    @Given("we peek before iteration step numbers")
    public void initializePeekPoints(List<Integer> items)
    {
    	mPeekPoints.addAll(items);
    	
    	IterableWithPeek<String> mCollection = new IterableWithPeek<String>(
    			mParameters.mStrings.get().toArray(new String[mParameters.mStrings.get().size()]));
    	
    	int positionCount = 0;
    	IterableWithPeek<String>.IteratorWithPeek testIterator = (IterableWithPeek<String>.IteratorWithPeek) mCollection.iterator();
    	do {
	    	if (mPeekPoints.contains(positionCount++)) {
	    		mResult.add(testIterator.peek());
	    	}
    		mResult.add(testIterator.next());
    	} while (testIterator.hasNext());
    }
    
    @Then("the observed values will be")
    public void verify(List<String> expectedValues)
    {
    	assertThat(mResult, is(expectedValues));
    }
}
