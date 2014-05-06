package com.karlmutch;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListMap;

// Example of a simple cache using a LinkedList and a HashMap.  There
// are many other ways to do this that are far better however these 
// are interview answers
//
// Examples of ways to do this include ehCache based technologies that
// can do inboard and offboard caching and Guava caches.

public class TrivialCache {

	private final int mMaxEntries;
	
	private ConcurrentLinkedQueue<String> mLRU = new ConcurrentLinkedQueue<String>();
	private ConcurrentSkipListMap<String, String> mCacheMemory = new ConcurrentSkipListMap<String, String>();
	
	public TrivialCache(int maxEntries)
	{
		mMaxEntries = maxEntries;
	}

	public String Put(final String key, final String value)
	{
		String oldValue = mCacheMemory.get(key);
		
		mCacheMemory.put(key, value);
		mLRU.add(key);

		if (mMaxEntries < mCacheMemory.size()) {
			mCacheMemory.remove(mLRU.poll());
		}
		
		return(oldValue);
	}
	
	public String Get(final String key)
	{
		String result = mCacheMemory.get(key);
		
		if (result != null) {
			mLRU.remove(key);
			mLRU.offer(key);
		}
		
		return(result);
	}
}
