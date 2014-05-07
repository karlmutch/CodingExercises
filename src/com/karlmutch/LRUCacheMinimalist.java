/** 
 * Enhances original version, LRUCacheLinkedHashMap<K, V>, by using inheritance 
 * rather than encapsulation further simplifying the approach.  To create 
 * a cache of 1024 items the following would work.  Java 8 does re-implement 
 * the LinkedHashMap so that if the data structure contains long linked 
 * lists of items on the leaf it reverts to a BST implementation to prevent 
 * wrong doing, from a simple linked list style chain. 
 * 
 * LinkedHashMap is O(1)
 * 
 * @code
	Map<String, String> example = Collections.synchronizedMap(new LRUCacheMinimalist<String, String>(1024));
 * @code
 */

package com.karlmutch;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class LRUCacheMinimalist<K, V> extends LinkedHashMap<K, V> 
{
	public LRUCacheMinimalist(final int maxEntries) 
	{
		// The + 1 on the number of entries allows slop so that the order in which the
		// removeEldestEntry wont cause the cache to hold one less than we expect.
	    super(maxEntries + 1, 0.75f, true);
	    mMaxEntries = maxEntries;
	}
	
	@Override
	protected boolean removeEldestEntry(final Map.Entry<K, V> eldest) 
	{
	    return(super.size() > mMaxEntries);
	}

	private final int mMaxEntries;
}

