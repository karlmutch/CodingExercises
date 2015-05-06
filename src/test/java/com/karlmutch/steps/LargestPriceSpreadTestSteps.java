/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 */
package com.karlmutch.steps;

import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Optional;

import com.karlmutch.LargestPriceSpread;
import com.karlmutch.RunParameters;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LargestPriceSpreadTestSteps
{
	private final RunParameters mParameters;
	private Optional<LargestPriceSpread.BuySellBracket> mTradeData;
	
    
	public LargestPriceSpreadTestSteps(RunParameters parameters)
	{
		mParameters = parameters;
		mTradeData = Optional.empty();
	}

    @When("^searching for the best trade$")
    public void select() 
    {
    	ArrayList<Integer> prices = new ArrayList<Integer>();
    	for (BigInteger aValue : mParameters.mIntegers.get().toArray(new BigInteger[0])) {
    		prices.add(aValue.intValueExact());
    	}
    	
    	mTradeData = LargestPriceSpread.GetLargestPositiveSpread_OnePass(prices.toArray(new Integer[0]));
    }

    @Then("^no profitable trade will be found$")
    public void testNoTrade()
    {
    	assertThat(mTradeData, not(Optional.empty()));    	
    }
    
    @Then("^a buy at (\\d*) minutes at a price of (\\d*), and sell at (\\d*) minutes at a price of (\\d*) will be found$")
    public void testValidTrade(Integer buyMinute, Integer buyPrice, Integer sellMinute, Integer sellPrice)
    {
    	assertThat(mTradeData, not(Optional.empty()));
    	assertThat(mTradeData.get().buyMinute, is(buyMinute));
    	assertThat(mTradeData.get().buyPrice, is(buyPrice));
    	assertThat(mTradeData.get().sellMinute, is(sellMinute));
    	assertThat(mTradeData.get().sellPrice, is(sellPrice));
    }
    
}
