/**
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
