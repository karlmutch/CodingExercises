/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 */
package com.karlmutch.steps;

import java.math.BigInteger;

import com.karlmutch.RunParameters;
import com.karlmutch.TwoSortedArrayMedian;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class TwoSortedArrayMedianSteps 
{
	private final RunParameters mParameters;

	private BigInteger mResult = BigInteger.ZERO;
	
	public TwoSortedArrayMedianSteps(RunParameters parameters)
	{
		mParameters = parameters;
	}

    @When("^we find the median item by sorted position$")
    public void select()
    {
    	mResult = TwoSortedArrayMedian.GetMedian(mParameters.mIntegerSeries.get().get(0), 
    											 mParameters.mIntegerSeries.get().get(1));

    }

    @Then("^the median items value will be (\\d*)$")
    public void check(BigInteger expectedMedian) 
    {
    	assertThat(mResult, is(expectedMedian));
    }
}
