/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 */
package com.karlmutch.steps;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

import com.karlmutch.BalancedParens;
import com.karlmutch.RunParameters;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BalancedParensTestSteps 
{
	private final RunParameters mParameters;
	private int mResult = -3;

	public BalancedParensTestSteps(RunParameters parameters)
	{
		mParameters = parameters;
	}

    @When("^testing for balanced parentheses$")
    public void select() 
    {
    	mResult = BalancedParens.CheckParens(mParameters.mString.get());
    }

    @Then("^the expression will be balanced$")
    public void checkBalanced() 
    {
    	assertThat(mResult, is(-1));
    }

    @Then("^the expression will be unbalanced$")
    public void checkUnbalanced() 
    {
    	assertThat(mResult, not(-1));
    }

    @Then("^the expression will be unbalanced at position (\\d*)$")
    public void checkUnbalancedFull(Integer position) 
    {
    	assertThat(mResult, is(position));
    }
}
