package com.karlmutch;

public interface LRUCache<K,V> {

	   public abstract V put(K key, V item);

	   public abstract V get(K key);

	   public abstract V atomicGetAndSet(K key, V item); 
}