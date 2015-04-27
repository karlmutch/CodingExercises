/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 */
package com.karlmutch.steps;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import com.karlmutch.MonitorProducerConsumer;
import com.karlmutch.RunParameters;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MonitorProducerConsumerTestSteps 
{
	// The producer consumer test requires concurrency to have two threads
	// passing character data through a ring buffer.  An anonymous inner class
	// is used to encapsulate this behavior

	private final RunParameters mParameters;

	private AtomicBoolean mStopTest = new AtomicBoolean(false);
	private MonitorProducerConsumer mRingBuffer = new MonitorProducerConsumer(1024);
	private StringBuffer mTestOutput = new StringBuffer();

	public final ExecutorService mThreadPool = Executors.newCachedThreadPool();	

	// Producer class to put character data into a ring buffer with a flag
	// to allow the producer loop to break out
    class Producer implements Runnable 
    {
        public Producer(final char [] inputData) 
        {
        	mInputData = inputData;
        	mThreadPool.execute(this);
        }

        public void run() 
        {
        	CharacterIterator currentCharacter = new StringCharacterIterator(String.valueOf(mInputData));
        	while ( !mStopTest.get() && 
        		   (currentCharacter.getIndex() != currentCharacter.getEndIndex()) )
        	{
        		try {
        			mRingBuffer.Put(currentCharacter.current());
        			currentCharacter.next();
        		}
        		catch (InterruptedException interrupted)
        		{
        			// Timed-out check the stop flag otherwise just continue
        		}
        	}

        	// When the producer is done post the completed flag 
        	mStopTest.lazySet(true);
        }

        private final char [] mInputData;
    }

	// Consumer class to retrieve character data from a ring buffer with a flag
	// to allow the retrieval to terminate and break out if when the ring buffer
	// has offered no data worst case after  the timeout interval
    class Consumer implements Runnable 
    {
        public Consumer() 
        {
        	mThreadPool.execute(this);
        }

        public void run() 
        {
        	while (true) {
    			try {
					mTestOutput.append(mRingBuffer.Get());
				} 
    			catch (InterruptedException interrupted) 
    			{
    				// Use timeouts to determine if the test has been signaled to complete
    				if (mStopTest.get()) 
    				{
    					// Tell the outside world to wake up and check if everything is done
    					mTestOutput.notifyAll();
    					break;
    				}
				}        		
        	} 
        }
    }

	public MonitorProducerConsumerTestSteps(RunParameters parameters)
	{
		mParameters = parameters;

		// Have the ring buffer check itself every 1/4 if over or underflow conditions
		// occur
		mRingBuffer.SetTimeout(Duration.ofMillis(250));
	}
 
	@When("running a producer and consumer")
	public void selectTest()
	{
		@SuppressWarnings("unused")
		Consumer consumerRunner = new Consumer();

		@SuppressWarnings("unused")
		Producer producerRunner = new Producer(mParameters.mString.get().toCharArray());
		
		while (!mStopTest.get()) 
		{
			try {
				// A wait can only be done with the object locked as
				// the wait releases the lock automatically
				
				synchronized(mTestOutput) {
					mTestOutput.wait(100);
				}
			} 
			catch (InterruptedException ignored) 
			{
				// Wake up and simply check the termination flag 
			}
		}
	}
	
	@Then("the output string will be the same")
	public void check()
	{
		assertThat(mParameters.mString.get(), is(mTestOutput.toString()));
	}
}
