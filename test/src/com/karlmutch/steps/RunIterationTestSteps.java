package com.karlmutch.steps;


import com.karlmutch.AvoidLoopsToPrintNumbers;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RunIterationTestSteps
{
	private int mStart;
	private int mEnd;
	
	private String mResult;

    @Given("^a sequence starting at (\\d*) ending at (\\d*)$")
    public void initialize(int start, int end) 
    {
    	mStart = start;
    	mEnd = end;
    }

    @When("^you request it be printed$")
    public void print(int addedFuel) 
    {
    	AvoidLoopsToPrintNumbers.printNumberSequences(mStart,  mEnd);
    }

    @Then("^a string of \"1 2 3 4 5\" is printed$")
    public void check(int expectedFuelLevel) {
    	assertThat(mResult, is(""));
    }
}