/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 */
package com.karlmutch.steps;


import com.karlmutch.CountOnes;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CountOnesTestSteps
{
	private long mNumber;
	
	private int mNumberOfOnes = 0;

    @Given("^a number (\\d*)$")
    public void initialize(long aNumber) 
    {
    	mNumber = aNumber;
   }

    @When("^counting the ones digits$")
    public void print() 
    {
    	mNumberOfOnes = CountOnes.CountTheOnes(mNumber);
    }

    @Then("^a total of (\\d*) ones will be found$")
    public void check(int expectedTally) {
    	assertThat(mNumberOfOnes, is(expectedTally));
    }
}