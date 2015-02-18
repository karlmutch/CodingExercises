/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 */
package com.karlmutch.steps;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.karlmutch.FindPrimes;
import com.karlmutch.RunParameters;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FindPrimesTestSteps
{
	private final RunParameters mParameters;
    List<BigInteger> mObservedPrimes;

	public FindPrimesTestSteps(RunParameters parameters)
	{
		mParameters = parameters;
	}

    @When("^requesting the known primes$")
    public void selectScenario() 
    {
        mObservedPrimes = new FindPrimes(mParameters.mRangeStart.get(), mParameters.mRangeEnd.get()).getPrimes();
    }

    @Then("^the sequence of numbers \"(.*?)\" should be seen$")
    public void check(String expectedNumberList) 
    {
        String[] splitNumbers = expectedNumberList.split("\\,");

    	ArrayList<BigInteger> expectedPrimes = new ArrayList<BigInteger>();
       
        for (String aPrimeNumber : splitNumbers) {
        	expectedPrimes.add(new BigInteger(aPrimeNumber.trim()));
        }

    	assertThat(mObservedPrimes, is(expectedPrimes));
    }

}
