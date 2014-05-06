package com.karlmutch;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class QueueBasedStack 
{
	private static AtomicBoolean sShutdown = new AtomicBoolean(false);

	private static BlockingQueue<String> sProducerQueue = new ArrayBlockingQueue<String>(100);
	private static Queue<String> sConsumerQueue = new LinkedBlockingQueue<String>();

	public void popper()
	{
		String queueHead = null;

		do {
			try {
				while ((queueHead = sProducerQueue.poll(1, TimeUnit.SECONDS)) != null)
				{
					sConsumerQueue.add(queueHead);
					sProducerQueue.drainTo(sConsumerQueue);
					
					for (String item : sProducerQueue)
					{
						System.out.println(item);
					}
					
					sConsumerQueue.clear();
				}
			} catch (InterruptedException ignoredException) 
			{
				Thread.interrupted();
			}
		} while(!sShutdown.get());
	}
	
	public boolean pusher(final String itemToQueue, int timeout, TimeUnit period)
	{
		try {
			sProducerQueue.offer(itemToQueue, timeout, period);

			return(true);
		}
		catch (InterruptedException ignoredException) 
		{
			Thread.interrupted();
		}

		return(false);
	}
}
