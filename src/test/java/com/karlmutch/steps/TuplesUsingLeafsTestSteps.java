/**
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 */
package com.karlmutch.steps;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.karlmutch.RunParameters;
import com.karlmutch.TuplesUsingLeafs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TuplesUsingLeafsTestSteps 
{
	private ArrayList<ArrayList<Integer>> mVectors = new ArrayList<ArrayList<Integer>>();
	
	private String mResults = new String();
	
	public TuplesUsingLeafsTestSteps(RunParameters parameters)
	{
	}

	@Given("^a vector of integers$")
	public void loadTest(List<Integer> tupleLayer)
	{
		ArrayList<Integer> input = new ArrayList<Integer>();
		input.addAll(tupleLayer);

		mVectors.add(input);
	}
	
	@When("^creating the tuple set$")
	public void runTest()
	{
		ArrayList<Integer> leftHandSide = new ArrayList<Integer>();
		List<List<Integer>> rightHandSide = new ArrayList<List<Integer>>();
		rightHandSide.addAll(mVectors);

		mResults = TuplesUsingLeafs.descender(leftHandSide, rightHandSide);
	}
	
	@Then("^the printed tuple output will be \"(.*)\"$")
	public void validateTest(String tupleOutput)
	{
		assertThat(mResults, is(tupleOutput));
	}
}
