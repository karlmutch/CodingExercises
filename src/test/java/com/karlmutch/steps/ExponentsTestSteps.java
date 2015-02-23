/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 */

package com.karlmutch.steps;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.math.BigInteger;

import com.karlmutch.Exponents;
import com.karlmutch.RunParameters;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ExponentsTestSteps 
{
	private final RunParameters mParameters;
	
	private Integer mPower;
	private BigInteger mPowerResult;
	
	public ExponentsTestSteps(RunParameters parameters)
	{
		mParameters = parameters;
	}

    @When("^rasing it to the power (\\d+)$")
    public void select(Integer power) 
    {
    	mPower = power;
    	mPowerResult = Exponents.pow(mParameters.mNumber.get(), 
    								 BigInteger.valueOf(mPower));
    }

    @Then("^it will be the same as the library function$")
    public void check() 
    {
    	assertThat(mPowerResult, is(mParameters.mNumber.get().pow(mPower)));
    }
}
