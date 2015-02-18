/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 */
package com.karlmutch.steps;


import com.karlmutch.AvoidLoopsToPrintNumbers;
import com.karlmutch.RunParameters;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AvoidLoopsToPrintNumbersTestSteps
{
	private final RunParameters mParameters;
	private String mResult;

	public AvoidLoopsToPrintNumbersTestSteps(RunParameters parameters)
	{
		mParameters = parameters;
	}

    @When("^you request it be printed$")
    public void print() 
    {
    	mResult = AvoidLoopsToPrintNumbers.printNumberSequences(mParameters.mRangeStart.get().intValueExact(), mParameters.mRangeEnd.get().intValueExact());
    }

    @Then("^a string of \"(.*?)\" is printed$")
    public void check(String expectedString) {
    	assertThat(mResult, is("{" + expectedString + "}"));
    }
}