package com.karlmutch;

public class MonitorProducerConsumer {

	public MonitorProducerConsumer()
	{
		mBuffer = new char[256];
	}

	public MonitorProducerConsumer(int size)
	{
		mBuffer = new char[size];
		
		mBufferCount = 0;
		mIn = 0;
		mOut = 0;
	}

	public synchronized void Put(char character)
	{
		// Wait until there is some space to place the character into our buffer
		while (mBufferCount == mBuffer.length) {
			try {
				wait();
			}
			catch (InterruptedException exception) {
			}
			// If there are multiple characters retrieved by the consumer then we may
			// have the condition that the consumer has pulled multiple characters since we
			// entered this state and we need to flush the notifies until we have room for
			// new data
		}
		mBuffer[mIn] = character;
		mIn = ++mIn % mBuffer.length;
		++mBufferCount;
		
		// Let any waiting consumers know about the presence of new data in our ring buffer
		notify();
	}
	
	public synchronized char Get()
	{
		// Wait until there is at least one character in the buffer
		while (mBufferCount == 0) {
			try {
				wait();
			}
			catch (InterruptedException exception) {
			}
			// When waiting for data we may received notifies due to old actions by the producer
			// but for which data has already been consumed so we need to loop back around and
			// check the application level conditions for new data being ready
		}

		char result = mBuffer[mOut];
		mOut = ++mOut % mBuffer.length;
		--mBufferCount;
		
		// Let any waiting producers know that some space has been cleared inside the buffer
		notify();
		
		return(result);
	}

	private char [] mBuffer;
	private int mBufferCount;
	private int mIn, mOut;

}
