/**
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 */
package com.karlmutch.steps;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.math.BigInteger;

import com.karlmutch.RandomItemWithBias;
import com.karlmutch.RunParameters;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RandomItemWithBiasTestSteps 
{
	private final RunParameters mParameters;
	
	private RandomItemWithBias mCities= new RandomItemWithBias();
	
	private String mChoosenCity = new String();

	public RandomItemWithBiasTestSteps(RunParameters parameters)
	{
		mParameters = parameters;
	}

	@Given("^a city \"(.*)\" of (\\d+) people$")
	public void selectACity(String cityName, BigInteger population)
	{
		mCities.AddCity(cityName, population);
	}
	
	@When("^choosing a city using test specified random number$")
	public void runTest()
	{
		mChoosenCity = mCities.GetCity();
	}
	
	@Then("^\"(.*)\" will be the city selected$")
	public void validateTest(String cityName)
	{
		assertThat(mChoosenCity, is(cityName));
	}
}
