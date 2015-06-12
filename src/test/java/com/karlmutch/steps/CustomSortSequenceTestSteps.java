/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 */
package com.karlmutch.steps;

import java.util.ArrayList;
import java.util.List;

import com.karlmutch.CustomSortSequence;
import com.karlmutch.RunParameters;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CustomSortSequenceTestSteps 
{
	private final RunParameters mParameters;
	private List<Integer> mResults = new ArrayList<Integer>();

	public CustomSortSequenceTestSteps(RunParameters parameters)
	{
		mParameters = parameters;
	}

	@When("^sorting to create the largest possible number from the integers$")
	public void selectTest()
	{
		List<Integer> inputs = new ArrayList<Integer>();
		mParameters.mIntegers.get().forEach(big -> inputs.add(big.intValueExact()));
		
		mResults = CustomSortSequence.SortForLargestInteger(inputs);
	}

	@Then("^the sorted list will be") 
	public void selectTest(List<Integer> expectedValues)
	{
    	assertThat(mResults, is(expectedValues));
	}	
}
