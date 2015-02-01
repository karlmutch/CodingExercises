/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 */
package com.karlmutch.steps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.karlmutch.FizzBuzz;
import com.karlmutch.RunParameters;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class FizzBuzzTestSteps 
{
	private final RunParameters mParameters;

	private List<String> mFizzBuzzResults = new ArrayList<String>();
	
	public FizzBuzzTestSteps(RunParameters parameters)
	{
		mParameters = parameters;
	}

    @When("^FizzBuzz is used$")
    public void select() 
    {
    	mFizzBuzzResults = FizzBuzz.DoFizzBuzz(mParameters.mRangeStart.get().intValueExact(), 
    										   mParameters.mRangeEnd.get().intValueExact());
    }

    @Then("^the sequence of strings and numbers \"(.*?)\" should be seen$")
    public void check(String expectedStrings) 
    {
    	List<String> expectedTestResults = new ArrayList<String>();
    	Collections.addAll(expectedTestResults, expectedStrings.replace(" ", "").split("\\,"));
       
    	assertThat(mFizzBuzzResults, is(expectedTestResults));
    }
}
