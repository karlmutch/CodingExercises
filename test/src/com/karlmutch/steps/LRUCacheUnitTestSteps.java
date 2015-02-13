/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 */
package com.karlmutch.steps;


import java.util.ArrayList;
import java.util.List;

import com.karlmutch.LRUCacheMinimalist;
import com.karlmutch.RunParameters;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LRUCacheUnitTestSteps
{
	private final RunParameters mParameters;
	private LRUCacheMinimalist<Integer, String> mCache;
	private int mItemCount = 0;

	public LRUCacheUnitTestSteps(RunParameters parameters)
	{
		mParameters = parameters;
	}

	@When("^adding the series to an LRU cache with a size of (\\d*)$")
    public void select(int cacheSize) 
    {
		mCache = new LRUCacheMinimalist<Integer, String>(cacheSize);
		for (String anItem : mParameters.mStrings.get())
		{
			mCache.put(++mItemCount, anItem);
		}
    }

    @Then("^adding the item \"(.*?)\" will result in")
    public void check(String additionalItemToCache, List<String> expectedItems)
    {
    	mCache.put(++mItemCount, additionalItemToCache);

    	assertThat(mCache.values().toArray(), is(expectedItems.toArray()));
    }
}

