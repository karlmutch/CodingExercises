/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 */
package com.karlmutch.steps;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import com.clearspring.analytics.stream.ScoredItem;
import com.karlmutch.DistributedTopK;
import com.karlmutch.RunParameters;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DistributedTopKTestSteps 
{
	private final RunParameters mParameters;
	private List<ScoredItem<String>> mResult;

	public DistributedTopKTestSteps(RunParameters parameters)
	{
		mParameters = parameters;
	}
 
	@When("^using a distributed TopK with (\\d) hosts and a k of (\\d)$")
	public void selectTest(int hosts, int K)
	{
		DistributedTopK topK = new DistributedTopK(hosts, K);

		for (String anItem : mParameters.mStrings.get())
		{
			topK.ProcessData(anItem);
		}

		mResult = topK.StopAndGetTopK();
	}

	@Then("^the sorted TopK will be") 
	public void selectTest(List<String> expectedValues)
	{
		List<String> parsedResults = new ArrayList<String>();
		
		// Take the sorted items in the TopK scored results and extract just the strings that were counted
		// without other items and insert them into descending order into the parsedResults
		mResult.stream().map( entry -> { return entry.getItem(); } ).forEach(item -> { parsedResults.add(item); } );
		
    	assertThat(parsedResults, is(expectedValues));
	}	
}
