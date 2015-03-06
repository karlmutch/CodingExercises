/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 * Becoming more and more common the interview question of using stacks to 
 * implement queues is another 'knows the answer before the interview' type
 * fire drill.
 * 
 * Using monitor and offer/poll/drain type questions is often asked because
 * it is assumed that these demonstrate core skills.  This might be valid 
 * logic but generally these types of core features come from the long 
 * ago and now are never used because of their limited applicability and so
 * result in juniors being able to answer them but not often seniors unless
 * of course they spent their time reading the 'Top 100 Java Questions' lists  
 * 
 */

package com.karlmutch;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class QueueBasedStack 
{
	private AtomicBoolean sShutdown = new AtomicBoolean(false);

	private BlockingQueue<String> sProducerQueue = new ArrayBlockingQueue<String>(100);
	private Queue<String> sConsumerQueue = new LinkedBlockingQueue<String>();

	@FunctionalInterface
	public interface PopperFunc
	{
		public void postResult(String result);
	}

	public void popper(PopperFunc callback)
	{
		String queueHead = null;

		do {
			try {
				if ((queueHead = sProducerQueue.poll(100, TimeUnit.MILLISECONDS)) != null)
				{
					// The poll will have removed the head so it must be manually
					// inserted into the consumer queue then the rest of the 
					// producer queue can be drained if anything else is left
					sConsumerQueue.add(queueHead);
					sProducerQueue.drainTo(sConsumerQueue);
					
					for (String item : sConsumerQueue)
					{
						callback.postResult(item);
					}
					
					sConsumerQueue.clear();
				}
			} 
			catch (InterruptedException ignoredException) 
			{
				Thread.interrupted();
			}
		} while(!sShutdown.get() || !sProducerQueue.isEmpty());
	}
	
	public boolean pusher(final String itemToQueue, int timeout, TimeUnit period)
	{
		try {
			return(sProducerQueue.offer(itemToQueue, timeout, period));
		}
		catch (InterruptedException ignoredException) 
		{
			Thread.interrupted();
		}

		return(false);
	}
	
	public boolean stop()
	{
		return(sShutdown.getAndSet(true));
	}
}
