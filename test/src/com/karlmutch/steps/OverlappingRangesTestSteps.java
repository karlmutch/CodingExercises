/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
**/
package com.karlmutch.steps;

import com.karlmutch.OverlappingRanges;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class OverlappingRangesTestSteps 
{
	OverlappingRanges ranges = new OverlappingRanges();

	@Given("an empty range")
	public void start() 
	{}

	@When("adding a range starting at (\\d+) and ending at (\\d+) to the original range")
	public void selectTest(int start, int end)
	{
		ranges.add(new OverlappingRanges.Range(start, end));
	}

	@When("merging the ranges so far")
	public void selectCompressionTest()
	{
		ranges.compress();
	}

	
	@Then("the resulting range will be \"(.*?)\"$")
	public void check(String expectedOutput)
	{
		assertThat(ranges.toString(), is(expectedOutput));
	}
}
