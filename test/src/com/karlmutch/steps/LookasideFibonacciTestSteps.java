/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 */
package com.karlmutch.steps;

import com.karlmutch.LookasideFibonacci;
import com.karlmutch.RunParameters;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LookasideFibonacciTestSteps 
{

	private final RunParameters mParameters;

	private LookasideFibonacci mSeries = new LookasideFibonacci();
	private long mComputations = 0;
	
	public LookasideFibonacciTestSteps(RunParameters parameters)
	{
		mParameters = parameters;
	}

    @When("^computing the fibonacci number$")
    public void select() 
    {
    	mSeries.getWithLookaside(mParameters.mRangeEnd.get().intValueExact());
    	mSeries.mCountCalcs.set(0);
    	mSeries.getWithLookaside(mParameters.mNumber.get().intValueExact());
    	mComputations = mSeries.mCountCalcs.get();
    }

    @Then("^a total of (\\d*) computations will be done$")
    public void check(long expectedTally) {
    	assertThat(mComputations, is(expectedTally));
    }
}
