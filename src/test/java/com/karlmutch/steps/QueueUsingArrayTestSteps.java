/**
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 * This test will exercise the array based queue with a specific capacity.
 * 
 * This queue has been implemented using a ring buffer overlaying the array.
 **/
package com.karlmutch.steps;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.karlmutch.QueueUsingArray;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class QueueUsingArrayTestSteps 
{
	QueueUsingArray mQueue;
	
	@Given("^an array of (\\d) entries backing a queue$")
	public void setupTest(int capacity)
	{
		mQueue = new QueueUsingArray(capacity);
	}

	@When("^pushing \"(.*)\" into the queue expecting it to succeed$")
	public void doAPush(String newItem)
	{
		assertTrue(mQueue.push(newItem));
	}

	@When("^pushing \"(.*)\" into the queue expecting it to fail$")
	public void doAPushFail(String newItem)
	{
		assertFalse(mQueue.push(newItem));
	}

	@When("^popping an item from the queue expecting it to be \"(.*)\"$")
	public void doAPop(String poppedItem)
	{
		assertThat(mQueue.pop().get(), is(poppedItem));
	}
	
	@When("^popping an item from the queue expecting it to fail$")
	public void doAPop()
	{
		assertThat(Optional.empty(), is(mQueue.pop()));
	}

	@Then("^the remaining queue will contain")
	public void validateTest(List<String> itemsRemaining)
	{
		ArrayList<String> itemsLeft = new ArrayList<String>();

		Optional<String> item = mQueue.pop();
		while (item.isPresent()) {
			itemsLeft.add(item.get());
			item = mQueue.pop();
		};
		
		assertEquals(itemsLeft, itemsRemaining);
	}

	@Then("^the remaining queue will be empty")
	public void validateTest()
	{
		Optional<String> item = mQueue.pop();
	
		assertEquals(item, Optional.empty());
	}
}
