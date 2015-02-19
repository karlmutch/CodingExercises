/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 */
package com.karlmutch.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import com.karlmutch.RunParameters;
import com.karlmutch.SimpleCalculator;

public class SimpleCalculatorTestSteps 
{
	private final RunParameters mParameters;
	
	private Float mResult;
	
	public SimpleCalculatorTestSteps(RunParameters parameters)
	{
		mParameters = parameters;
	}

	@When("^performing a trivial calculation$")
	public void select()
	{
		mResult = SimpleCalculator.performSimpleCalculation(mParameters.mString.get());
	}
	
	@Then("^the result will be (\\d+)$")
	public void check(float expectedResult)
	{
    	assertThat(mResult, is(expectedResult));
	}
}