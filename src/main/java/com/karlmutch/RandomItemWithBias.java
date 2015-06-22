/**
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 * Given a list of cities, each city has a population value, write a function that returns a city 
 * in the input list. 
 * 
 * The probability of returning a specific city should be proportional to the population of the city. 
 * For example, if the list contains city1 with 15 pop value and city2 with 5 pop value, then if you 
 * call the function 4 times, on average it should return city1 for 3 times and city2 once. 
 */
package com.karlmutch;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.TreeMap;

public class RandomItemWithBias
{
	// The member variable will store the set of items that have been observed and will use
	// as their key a quantity that is a proportion of all of the quantities seen.  Every time
	// a new item, or city is presented it will be associated with the new sum of all cities.
	// 
	// For example if we are presented with two cities, 100 Hoboken, and 200 Kenwood then the
	// TreeMap will have two keys 100, and 300 for the respective values.
	//
	// When we wish to retrieve a random city biased with respect to the populations we simply
	// generated a random number between 0 and 300.  Then we use the tree set method higherEntry.
	// This will then return the entry in our Treemap that occurs after the random value generated.
	private TreeMap<BigInteger, String> mCitiesSeen = new TreeMap<BigInteger, String>();
	
	private BigInteger mPopulationsAccumulated = BigInteger.ZERO;
	
	public void AddCity(String cityName, BigInteger population)
	{
		mPopulationsAccumulated = mPopulationsAccumulated.add(population);
		mCitiesSeen.put(mPopulationsAccumulated, cityName);
	}
	
	public String GetCity()
	{
		// To get a random city based upon the distribution of the populations you 
		// can locate a random position in the summed series of populations and
		// then if cities are organized using a range of their population and their
		// names sit in a sorted set of ranges we can dip for the random position 
		// and then get the corresponding city they landed on by looking at the
		// city name sitting on the right hand side of the ranges.
		
		// The random number generated will be a small double so in order to preserve
		// precision during the multiply we can use BigDecimal and then convert back
		// to a BigInteger by dropping the fractional part
		
		// Get the sum of all populations as a fixed precision large number
		BigInteger randomInEntirePopulation = new BigDecimal(mPopulationsAccumulated)
		// Multiply the sum by a random number between 0 and 1 to get a place within the
		// population positions of all populations.  The random number generator referenced here
		// is one that is instrumented to allow for testing predictable dips into our collection
		// otherwise when running normally is simply uses the Math.random() and ThreadLocalRandom.current()
													.multiply(BigDecimal.valueOf(com.karlmutch.Random.random()))
		// Then turn it back into an integer to get a place on the scale of positions or integers
													.toBigInteger();
		
		// Now get the name of the city that represents the range into which we fell by fetching 
		// the right hand side of the range and getting the city name from that 
		return(mCitiesSeen.higherEntry(randomInEntirePopulation).getValue());
	}
}
