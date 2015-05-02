/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 */
package com.karlmutch.steps;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.math.BigInteger;
import java.util.List;

import com.karlmutch.ArrayProductExceptIndex;
import com.karlmutch.RunParameters;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ArrayProductExceptIndexTestSteps 
{
	private final RunParameters mParameters;
	private BigInteger [] mResult;

	public ArrayProductExceptIndexTestSteps(RunParameters parameters)
	{
		mParameters = parameters;
	}

    @When("^the product of the array excluding individual items at their original positions is done$")
    public void select() 
    {
    	mResult = ArrayProductExceptIndex.get_products_of_all_ints_except_at_index(
    					mParameters.mIntegers.get().toArray(new BigInteger[mParameters.mIntegers.get().size()])) ;
    }

    @Then("a series of integers is produced:")
    public void initializeIntegers(List<BigInteger> items)
    {
    	assertThat(mResult, is(items.toArray(new BigInteger[items.size()])));
    }

}
