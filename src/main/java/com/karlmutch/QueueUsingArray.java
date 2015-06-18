/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 * The following question was captured from a Web Site of peoples experiences with phone interviews
 * 
 * Using an array, implement a queue.
 * 
 */
package com.karlmutch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class QueueUsingArray 
{
	private int mCount = 0;			// The number of items we have in our queue
	private int mCurrentHead = 0;	// The position in the array that represents the end or insertion point of the queue
	private ArrayList<String> mQueueBacking = new ArrayList<String>(1);

	public QueueUsingArray(int capacity)
	{
		mQueueBacking = new ArrayList<String>(capacity);
		mQueueBacking.addAll(Collections.nCopies(capacity, ""));
	}

	public Optional<String> pop()
	{
		if (mCount == 0) {
			return(Optional.empty());
		}

		// Get the head item and advance to the next available item with wrap around
		Optional<String> result = Optional.of(mQueueBacking.get(mCurrentHead));

		if (++mCurrentHead >= mQueueBacking.size()) {
			mCurrentHead = 0;
		}
		mCount--;

		return(result);
	}
	
	public boolean push(String newItem)
	{
		// Check to see if we have any room
		if (mCount + 1 > mQueueBacking.size()) {
			return(false);
		}
		
		// Place the new item into the array mCount items from the current head of the queue
		int position = (mCurrentHead + mCount++) % mQueueBacking.size();
		mQueueBacking.set(position, newItem);

		return(true);
	}
}
