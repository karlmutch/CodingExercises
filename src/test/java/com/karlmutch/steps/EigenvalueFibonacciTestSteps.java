/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 */
package com.karlmutch.steps;

import java.math.BigInteger;

import com.karlmutch.LookasideFibonacci;
import com.karlmutch.EigenvalueFibonacci;
import com.karlmutch.RunParameters;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class EigenvalueFibonacciTestSteps 
{
	private final RunParameters mParameters;
	private BigInteger mComputedFibonacciResult;

	public EigenvalueFibonacciTestSteps(RunParameters parameters)
	{
		mParameters = parameters;
	}

    @When("^computing the fibonacci number using Eigenvalues$")
    public void select() 
    {
    	mComputedFibonacciResult = EigenvalueFibonacci.get(mParameters.mNumber.get());
    }

    @Then("^the fibonacci result will be (\\d*)$")
    public void check(BigInteger expectedResult) 
    {
    	assertThat(mComputedFibonacciResult, is(expectedResult));
    }

    @Then("^the result will be the same as the lookaside result$")
    public void check() 
    {
    	assertThat(mComputedFibonacciResult, is(new LookasideFibonacci().getWithLookaside(mParameters.mNumber.get().intValueExact())));
    }

}
