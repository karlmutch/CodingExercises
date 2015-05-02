/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 */
package com.karlmutch.steps;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.karlmutch.RunParameters;
import com.karlmutch.RunParameters.Pair;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

public class TestDataCollector 
{
	private final RunParameters mParameters;


	public TestDataCollector(RunParameters parameters)
	{
		mParameters = parameters;
	}

	@Before
	public void cleanPrevious()
	{
		mParameters.mRangeStart = Optional.empty();
		mParameters.mRangeEnd = Optional.empty();
		
		mParameters.mNumber = Optional.empty();
		
		mParameters.mString = Optional.empty();
		mParameters.mStrings = Optional.empty();

		mParameters.mIntegers = Optional.empty();
	}
	
    @Given("^a number range from (\\d*) to (\\d*)$")
    public void initialize(BigInteger aStartNumber, BigInteger anEndNumber)
    {
    	mParameters.mRangeStart = Optional.of(aStartNumber);
    	mParameters.mRangeEnd = Optional.of(anEndNumber);
    }

    @Given("^a string \"(.*)\"$")
    public void initialize(String anInputString)
    {
    	mParameters.mString = Optional.of(anInputString);
    }
    
    @Given("^a random string (\\d*) characters long$")
    public void initialize(long desiredLength)
    {
    	StringBuffer resultingString = new StringBuffer();
    	new Random().ints(desiredLength, ' ', '~' + 1).forEach(chr -> resultingString.append(chr));

        mParameters.mString = Optional.of(resultingString.toString());
    }

    @Given("^a number (\\d*)$")
    public void initialize(BigInteger aNumber)
    {
    	mParameters.mNumber = Optional.of(aNumber);
    }
    
    @Given("a series of strings:")
    public void initialize(List<String> items)
    {
    	mParameters.mStrings = Optional.of(items);
    }

    @Given("a series of integers:")
    public void initializeIntegers(List<BigInteger> items)
    {
    	mParameters.mIntegers = Optional.of(items);
    }

    @Given("^a series of (\\d*) items containing random big integer strings$")
    public void initializeStringsRandom(int desiredLength)
    {
    	List<String> resultingStrings = new ArrayList<String>(desiredLength);
    	
    	SecureRandom random = new SecureRandom();
    	
    	resultingStrings = Stream.generate(() -> new BigInteger(130, random).toString(32))
    			.limit(desiredLength)
    			.collect(Collectors.toList());

        mParameters.mStrings = Optional.of(resultingStrings);
    }

    @Given("multiple series of integers:")
    public void initializeSeries(List<String> items)
    {
    	mParameters.mStrings = Optional.of(items);
    	mParameters.mIntegerSeries = Optional.of(new ArrayList<ArrayList<BigInteger>>());
    	
    	for (String series : mParameters.mStrings.get()) 
    	{
    		ArrayList<BigInteger> integerSeries = new ArrayList<BigInteger>();
    		
    		for (String aNumber : series.split(",")) 
    		{
    			integerSeries.add(new BigInteger(aNumber.trim()));
    		}
    		
    		mParameters.mIntegerSeries.get().add(integerSeries);
    	}
    }

    @Given("multiple key value pairs:")
    public void initializeKVPairs(List<String> items)
    {
    	mParameters.mKeyValuePairs = Optional.of(new ArrayList<Pair<String, String>>());
    	
    	for (String series : items) 
    	{
    		String [] kvPair = series.split(",", 2);
    		mParameters.mKeyValuePairs.get().add(new Pair<String, String>(kvPair[0].trim(), kvPair[1].trim()));
    	}
    }
}
