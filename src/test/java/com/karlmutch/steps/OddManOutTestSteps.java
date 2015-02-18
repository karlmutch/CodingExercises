/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 */
package com.karlmutch.steps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.karlmutch.OddManOut;
import com.karlmutch.RunParameters;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class OddManOutTestSteps 
{
	private final RunParameters mParameters;
	
	private Boolean mStableResult = false;
	private List<String> mResult = new ArrayList<String>();
	
	private Boolean mDuplicates = false;
	
	public OddManOutTestSteps(RunParameters parameters)
	{
		mParameters = parameters;
	}

	@When("^stripping duplicates$")
    public void select() 
    {
		mResult.addAll(Arrays.asList(OddManOut.stripDuplicates(
				mParameters.mStrings.get().toArray(new String[0]))));
    }

	@When("^stripping, in a stable manner, any duplicates$")
    public void selectStable() 
    {
		mStableResult = true;
		mResult.addAll(Arrays.asList(OddManOut.stripStableDuplicates(
				mParameters.mStrings.get().toArray(new String[0]))));
    }
	
	@When("^detecting duplicates using collection length$")
	public void selectDetectLengths()
	{
		mDuplicates = OddManOut.hasDuplicates(mParameters.mStrings.get().toArray(new String[0]));
	}

	@When("^detecting duplicates using collection contents$")
	public void selectDetectContents()
	{
		mDuplicates = OddManOut.hasDuplicatesUsingIteration(mParameters.mStrings.get().toArray(new String[0]));		
	}

	@Then("^the collection will be seen to contain duplicates$")
	public void checkDuplicates()
	{
    	assertThat(mDuplicates, is(true));
	}

	@Then("^the collection will be seen to contain no duplicates$")
	public void checkNoDuplicates()
	{
    	assertThat(mDuplicates, is(false));
	}

    @Then("^the collection will be left with")
    public void check(List<String> expectedItems)
    {
    	List<String> resultsExpected = new ArrayList<String>();
    	resultsExpected.addAll(expectedItems);

    	if (!mStableResult) {
    		Collections.sort(mResult);
    		Collections.sort(resultsExpected);
    	}

    	assertThat(mResult, is(resultsExpected));
    }
}