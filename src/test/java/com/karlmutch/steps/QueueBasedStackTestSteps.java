/**
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 * This test will exercise the queue based stack with added semantics to allow the
 * test case to supply numeric values as wait times.  Positive values will be used 
 * to add wait times in milliseconds during the push operations into the queue.
 * Negative values will be used to control pauses after the item being popped
 * is dequeued and will be treated as absolute values.
 * 
 **/
package com.karlmutch.steps;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.karlmutch.QueueBasedStack;
import com.karlmutch.RunParameters;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class QueueBasedStackTestSteps 
{
	private RunParameters mParameters;
	
	private List<String> mResults = new ArrayList<String>();

	class TestPopper implements QueueBasedStack.PopperFunc
	{
		@Override
		public void postResult(final String result) 
		{
			mResults.add(result);

			try {
				// Negative Numbers allow the input data to stall the consumer
				Integer pauseConsumer = Integer.parseInt(result);
				
				if (-1 == pauseConsumer.compareTo(0)) {
					Thread.sleep(Math.abs(pauseConsumer));
				}
			} 
			catch (NumberFormatException ignored) {
				// Ignored the case the input not a number.  In these cases it remains in the output
				// list but is never used for pausing the consumer
			} 
			catch (InterruptedException ignored)  
			{
				// If the pause is broken then continue and do not act explicitly on the broken wait
				Thread.interrupted();
			}
		}
	}

	public QueueBasedStackTestSteps(RunParameters parameters)
	{
		mParameters = parameters;
	}

	@When("^pushing and popping items off a stack$")
	public void selectTest()
	{
		// Start a popper thread to accept the results of the popper thread
		Thread mPopperWorker;
		QueueBasedStack mQueue = new QueueBasedStack();

		{
			Runnable popWorker = () -> {
	
				mQueue.popper(new TestPopper());
			};

			mPopperWorker = new Thread(popWorker);
			mPopperWorker.start();
		}

		// Pushing items on to the stack
		for (String anItem : mParameters.mStrings.get()) 
		{
			mQueue.pusher(anItem, 1, TimeUnit.SECONDS);

			try {
				// Negative Numbers allow the input data to stall the consumer
				Integer pauseConsumer = Integer.parseInt(anItem);
				
				if (1 == pauseConsumer.compareTo(0)) 
				{
					try {
						Thread.sleep(Math.abs(pauseConsumer));
					}
					catch (InterruptedException ignored)  
					{
						// If the pause is broken then continue and do not act explicitly on the broken wait
						Thread.interrupted();
					}
				}
			}
			catch (NumberFormatException ignored) {
				// Ignored the case the input not a number.  In these cases it remains in the output
				// list but is never used for pausing the consumer
				continue;
			} 
		}

		// Signal completion to the consumer
		mQueue.stop();

		// Wait for the worker to stop so that the output list contains all queued
		// items
		do {
			try {
				mPopperWorker.join();
			}
			catch (InterruptedException ignored) 
			{
				// If the worker thread does not exit after five seconds and we are interrupted
				// it is possible that the last item in the test was a number of milliseconds for pausing
				// that had us waiting for a long time unexpectedly.
				//
				// Even if this is the case and it is the last item in the queue that was left 
				// then the test results will have enough in them to probably validate correctly
				Thread.interrupted();
			}
		} while (mPopperWorker.getState() != Thread.State.TERMINATED);
	}

	@Then("the popped items will be:")
	public void check(List<String> expectedOutput)
	{
		// Use the following line if debugging is needed
		//
		// Stream.of(testOutput.toArray(new String[testOutput.size()]))
		//		.forEach(System.out::println);
		
		assertThat(mResults, is(expectedOutput));
	}
}
