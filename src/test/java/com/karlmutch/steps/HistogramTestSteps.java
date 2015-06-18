/**
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 * Reworded interview problem, to protect the innocent.
 */
package com.karlmutch.steps;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import com.karlmutch.Histogram;
import com.karlmutch.RunParameters;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HistogramTestSteps 
{
	private final RunParameters mParameters;

	private Histogram mHistogram = new Histogram();
	private double mResult = 0;

	public HistogramTestSteps(RunParameters parameters)
	{
		mParameters = parameters;
	}

	@When("^calculating the (\\d+)th percentile$")
	public void selectTest(int percentile)
	{
		mParameters.mIntegers.get().forEach(big -> mHistogram.addValue(big.doubleValue()));
		
		mResult = mHistogram.computePercentile(percentile);
	}

	@Then("^the result will be (\\d+)$") 
	public void selectTest(Integer expectedValue)
	{
    	assertThat(Double.valueOf(mResult).intValue(), is(expectedValue));
	}
}
