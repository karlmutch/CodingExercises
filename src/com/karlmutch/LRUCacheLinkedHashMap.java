package com.karlmutch;

import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;
import java.util.Map;

// This method of implementing a cache is the shortest path using just JDK classes for a naieve
// cache
public class LRUCacheLinkedHashMap<K, V> implements LRUCache<K, V> 
{
	// By using a soft reference we are making some accommodation for the JVM to throw things away but
	// in practice these approaches are not useful
	private final Map<K, SoftReference<V> > mCache;
  
	@SuppressWarnings("serial")
	public LRUCacheLinkedHashMap(final int cacheSize) 
	{
		// Normally this class will use insertion order which is not what is needed
		// instead we used the third argument to specify access order for eviction
		mCache = new LinkedHashMap<K, SoftReference<V> > (cacheSize, 0.75f, true) 
			{    
				@Override
				protected boolean removeEldestEntry(Map.Entry<K, SoftReference<V> > eldest) 
				{
					// Specify the eviction behavior to be based upon the size policy
					return (size() > cacheSize);
				}
			};
	}
 
	@Override
	public V put(K key, V value) 
	{
	    SoftReference<V> previousValueReference = mCache.put(key, new SoftReference<V>(value));
	    return ((previousValueReference != null) ? previousValueReference.get() : null);
	}
 
	@Override
	public V get(K key) 
	{
		SoftReference<V> valueReference = mCache.get(key);
		return ((valueReference != null) ? valueReference.get() : null);
	}
	
	@Override
	public V atomicGetAndSet(K key, V value) 
	{
	    V result = get(key);
	    put(key, value);
	    return result;
	}
}