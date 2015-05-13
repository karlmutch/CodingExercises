/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 */
package com.karlmutch.steps;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Optional;

import com.karlmutch.LargestProduct;
import com.karlmutch.RunParameters;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LargestProductTestSteps 
{
	private final RunParameters mParameters;
	private BigInteger mLargestProduct = BigInteger.ZERO;
	
    
	public LargestProductTestSteps(RunParameters parameters)
	{
		mParameters = parameters;
	}

    @When("^searching for the largest product of any of (\\d*) items$")
    public void select(int nProducts) 
    {
    	BigInteger [] items = mParameters.mIntegers.get().toArray(new BigInteger[mParameters.mIntegers.get().size()]);
    	mLargestProduct = LargestProduct.HighestNProduct(items, nProducts);
    }

    @Then("^the product will be (\\d*)$")
    public void check(BigInteger product)
    {
    	assertThat(mLargestProduct, is(product));
    }
}
