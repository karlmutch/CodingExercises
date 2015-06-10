/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 */
package com.karlmutch.steps;

import java.util.ArrayList;
import java.util.List;

import com.karlmutch.RunParameters;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DistributedTopKTestSteps 
{
	private final RunParameters mParameters;
	private List<String> mResult;

	public DistributedTopKTestSteps(RunParameters parameters)
	{
		mParameters = parameters;
	}
 
	@When("^using a distributed TopK with (\\d) hosts$")
	public void selectTest(int hosts)
	{
		mResult = new ArrayList<String>();
	}

	@Then("^the sorted TopK will be") 
	public void selectTest(List<String> expectedValues)
	{
    	assertThat(mResult, is(expectedValues));
	}
	
}
