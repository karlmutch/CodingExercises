/**
 *
* (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 * Interviews can involve implementing commonly used structures with specific
 * constraints.  A popular example is to implement a HashTable using arrays. 
 * 
 *  The following code example attempts to do this without using external or
 *  JDK libraries.  The exception being the use of the Optional generic to
 *  try and increase readability
 */

package com.karlmutch;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Optional;

public class HashTableUsingBuckets 
{
	public static int sTableSize = 256;	// Must be a power of two
	
	// A hash table entry that supports chaining together items
	// that appear within the same bucket, in other words hash to the same
	// value
	class HashEntry {
		
		private final String mKey;
		private String mValue;
		private Optional<HashEntry> mNext;
		
		HashEntry(String key, String value) {
			mKey = key;
			mValue = value;
			mNext = Optional.empty();
		}

		public String getKey() {
			return (mKey);
		}
		
		public String getValue() {
			return (mValue);
		}
		
		public void setValue(String value) {
			mValue = value;
		}
		
		public Optional<HashEntry> getNext() {
			return(mNext);
		}
		
		public void setNext(Optional<HashEntry> next) {
			mNext = next;
		}
	}

	private int getHash(String key)
	{
		// At this time I am concentrating simply on getting the example
		// working and then later on will select a key function
		return(key.hashCode());
	}
	
	// Probably a little too complex for a coding whiteboard but one can
	// probably put something up that is known to have type erasure issues
	// and then discuss it
	@SuppressWarnings("unchecked")
	Optional<HashEntry> [] mTable = (Optional<HashEntry>[]) 
			Array.newInstance(Optional.of(new HashEntry("", "")).getClass(), sTableSize);

	public HashTableUsingBuckets()
	{
		Arrays.fill(mTable, Optional.empty());
	}
	
	public Optional<String> get(String key)
	{
		int bucket = getHash(key) % mTable.length;
		
		Optional<HashEntry> next = mTable[bucket];
		do {
			if (next.isPresent()) {
				if (key.equals(next.get().getKey())) {
					return(Optional.of(next.get().getValue()));
				}
				next = next.get().getNext();
			}			
		} while(next.isPresent());

		return(Optional.empty());
	}

	public void put(String key, String value)
	{
		int bucket = getHash(key) % mTable.length;
		
		if (!mTable[bucket].isPresent()) {
			mTable[bucket] = Optional.of(new HashEntry(key, value));
			return;
		}

		Optional<HashEntry> next = mTable[bucket];

		while (next.isPresent() && next.get().getNext().isPresent()) {
			next = next.get().getNext();
		};
		
		next.get().setNext(Optional.of(new HashEntry(key, value)));
	}

	public Optional<String> delete(String key) 
	{
		int bucket = getHash(key) % mTable.length;
		
		Optional<HashEntry> previous = Optional.empty();
		Optional<HashEntry> next = mTable[bucket];
		do {
			if (mTable[bucket].isPresent()) {
				if (key == next.get().getKey()) {
					previous.get().setNext(next.get().getNext());
					String valueOfDeletedEntry = next.get().getValue();
					return(Optional.of(valueOfDeletedEntry));
				}
				previous = next;
				next = next.get().getNext();
			}			
		} while(mTable[bucket].isPresent());

		return(Optional.empty());
	}

}
