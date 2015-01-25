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

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FindPrimesTestSteps
{
	private FindPrimes mPrimeFinder;
	
    @Given("^a number range between (\\d*) and (\\d*)$")
    public void initialize(BigInteger aStartNumber, BigInteger anEndNumber)
    {
    	mPrimeFinder = new FindPrimes(aStartNumber, anEndNumber);
    }

    @When("^requesting the known primes$")
    public void print() 
    {
    }

    @Then("^the sequence of numbers \"(.*?)\" should be seen$")
    public void check(String expectedNumberList) 
    {
        String[] splitNumbers = expectedNumberList.split("\\,");

    	ArrayList<BigInteger> expectedPrimes = new ArrayList<BigInteger>();
       
        for (String aPrimeNumber : splitNumbers) {
        	expectedPrimes.add(new BigInteger(aPrimeNumber.trim()));
        }

        List<BigInteger> observedPrimes = mPrimeFinder.getPrimes();
    	assertThat(observedPrimes, is(expectedPrimes));
    }
}
