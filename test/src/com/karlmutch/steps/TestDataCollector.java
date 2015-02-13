/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 */
package com.karlmutch.steps;


import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import com.karlmutch.RunParameters;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

public class TestDataCollector 
{
	private final RunParameters mParameters;

	public TestDataCollector(RunParameters parameters)
	{
		mParameters = parameters;
	}

	@Before
	public void cleanPrevious()
	{
		mParameters.mRangeStart = Optional.empty();
		mParameters.mRangeEnd = Optional.empty();
		
		mParameters.mNumber = Optional.empty();
	}
	
    @Given("^a number range from (\\d*) to (\\d*)$")
    public void initialize(BigInteger aStartNumber, BigInteger anEndNumber)
    {
    	mParameters.mRangeStart = Optional.of(aStartNumber);
    	mParameters.mRangeEnd = Optional.of(anEndNumber);
    }
    
    @Given("^a number (\\d*)$")
    public void initialize(BigInteger aNumber)
    {
    	mParameters.mNumber = Optional.of(aNumber);
    }
    
    @Given("a series of strings:")
    public void initialize(List<String> items)
    {
    	mParameters.mStrings = Optional.of(items);
    }
}
