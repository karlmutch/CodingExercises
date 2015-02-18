/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 * Handy interface for using with caching example implementations
 */
package com.karlmutch;

import java.util.Collection;

public interface LRUCache<K,V> {

	   public abstract V put(K key, V item);

	   public abstract V get(K key);

	   public abstract V atomicGetAndSet(K key, V item);
	   
	   public abstract Collection<V> values();
}