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
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import com.karlmutch.InsertionSort;
import com.karlmutch.RunParameters;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When; 

public class InsertionSortTestSteps 
{
	private final RunParameters mParameters;
	private Optional<List<String>> mResult;
	
	DecimalFormat mFormat = new DecimalFormat();
    
	public InsertionSortTestSteps(RunParameters parameters)
	{
		mParameters = parameters;
		mResult = Optional.empty();
	    mFormat.setMaximumFractionDigits(5);
	}

    @When("^sorting using an insertion sort$")
    public void select() 
    {
    	Date startTime = new Date();

    	String [] sortItems = mParameters.mStrings.get().toArray(new String[mParameters.mStrings.get().size()]);
    	mResult = Optional.of(Arrays.asList(InsertionSort.<String>sort(sortItems)));

    	long duration = new Date().getTime() - startTime.getTime();
    	
    	System.out.println("Insertion counters standard, took " + TimeUnit.MILLISECONDS.toSeconds(duration) + 
    			" seconds and had assigns " + mFormat.format(new BigDecimal(InsertionSort.sAssigns)) + 
				   			    " compares " + mFormat.format(new BigDecimal(InsertionSort.sCompares)) + 
				   			    " swaps " + mFormat.format(new BigDecimal(InsertionSort.sSwaps)));
    }

    @When("^sorting using an optimized insertion sort$")
    public void selectOptimized() 
    {
    	Date startTime = new Date();

    	String [] sortItems = mParameters.mStrings.get().toArray(new String[mParameters.mStrings.get().size()]);
    	mResult = Optional.of(Arrays.asList(InsertionSort.<String>sortIntervals(sortItems)));

    	long duration = new Date().getTime() - startTime.getTime();

    	System.out.println("Insertion counters optimizd, took " + TimeUnit.MILLISECONDS.toSeconds(duration) + 
    			" seconds and had assigns " + mFormat.format(new BigDecimal(InsertionSort.sAssigns)) + 
				   			    " compares " + mFormat.format(new BigDecimal(InsertionSort.sCompares)) + 
				   			    " swaps " + mFormat.format(new BigDecimal(InsertionSort.sSwaps)));
    }

    @Then("^the sorted collection will be")
    public void checkFound(List<String> expectedValues) 
    {
    	assertThat(mResult, not(Optional.empty()));
    	assertThat(mResult.get(), is(expectedValues));
    }
    
	Optional<String> previousValue;

	@Then("^the result will be a sorted collection")
    public void checkJustForSortOrder() 
    {
    	previousValue = Optional.of("");
    	mResult.get().forEach(item -> { assertTrue(item.compareTo(previousValue.get()) >= 0) ; previousValue = Optional.of(item);});
    }
}
