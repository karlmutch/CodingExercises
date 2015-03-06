/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 **/
package com.karlmutch.steps;

import java.util.ArrayList;

import com.karlmutch.RunParameters;
import com.karlmutch.SharedMimimumOfArrays;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SharedMinimumOfArraysTestSteps 
{
	private final RunParameters mParameters;
	
	private int mResult = Integer.MIN_VALUE;

	public SharedMinimumOfArraysTestSteps(RunParameters parameters)
	{
		mParameters = parameters;
	}

	@When("searching for the minimum in two arrays")
	public void selectTest()
	{
		ArrayList<Integer> array1 = new ArrayList<Integer>();
		ArrayList<Integer> array2 = new ArrayList<Integer>();

		for (String anArray : mParameters.mStrings.get()) 
		{
			String [] items = anArray.replaceAll(" ", "").split(",");
			ArrayList<Integer> workingArray = new ArrayList<Integer>();

			for (String item : items) {
				workingArray.add(Integer.parseInt(item));
			}
			if (array1.isEmpty()) {
				array1 = workingArray;
			}
			else {
				array2 = workingArray;
			}
		}

		mResult = SharedMimimumOfArrays.GetMinimum(array1, array2);
	}

	@Then("the resulting minimum will be (\\d+)$")
	public void check(int expectedOutput)
	{
		assertThat(mResult, is(expectedOutput));
	}
}
