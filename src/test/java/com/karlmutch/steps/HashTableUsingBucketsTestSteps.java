/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 */
package com.karlmutch.steps;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import com.karlmutch.HashTableUsingBuckets;
import com.karlmutch.RunParameters;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HashTableUsingBucketsTestSteps 
{
	private final RunParameters mParameters;
	private Optional<String> mResult;

	public HashTableUsingBucketsTestSteps(RunParameters parameters)
	{
		mParameters = parameters;
		mResult = Optional.empty();
	}

    @When("^we use the key \"(.*?)\" to search a hash map$")
    public void select(String key) 
    {
    	// Create the hash map using buckets
    	// perform the search
    	// save the result for later comparison
    	
    	HashTableUsingBuckets hashTable = new HashTableUsingBuckets();

    	mParameters.mKeyValuePairs.get().forEach(kvPair -> hashTable.put(kvPair.getFirst(), kvPair.getSecond()));

    	mResult = hashTable.get(key);
    	
    }

    @Then("^the retrieved value will be \"(.*?)\"$")
    public void checkFound(String expectedValue) 
    {
    	assertThat(mResult, not(Optional.empty()));
    	assertThat(mResult.get(), is(expectedValue));
    }

    @Then("^no value will be found$")
    public void checkNotFound() 
    {
    	assertThat(mResult, is(Optional.empty()));
    }

}
