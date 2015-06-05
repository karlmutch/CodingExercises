/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 */
package com.karlmutch.steps;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

import com.karlmutch.SimpleMoviesKnapsackFit;
import com.karlmutch.RunParameters;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SimpleMoviesKnapsackFitTestCase 
{
	private final RunParameters mParameters;
	private boolean mFilledSchedule = false;
	
	public SimpleMoviesKnapsackFitTestCase(RunParameters parameters)
	{
		mParameters = parameters;
	}

    @When("^a flight of (\\d) minutes long is flown$")
    public void select(long flightLength) 
    {
    	ArrayList<Long> lengthsOfMovies = new ArrayList<Long>(mParameters.mIntegers.get().size());
    	mParameters.mIntegers.get().forEach(aLength -> { lengthsOfMovies.add(aLength.longValueExact()); });

    	mFilledSchedule = SimpleMoviesKnapsackFit.CanFit(flightLength, lengthsOfMovies.toArray(new Long [lengthsOfMovies.size()]));
    }

    @Then("^the movie schedule will be full$")
    public void initializeIntegers()
    {
    	assertThat(mFilledSchedule, is(true));
    }

    @Then("^the movie schedule will not be complete$")
    public void initializeIntegersFail()
    {
    	assertThat(mFilledSchedule, is(false));
    }
}
