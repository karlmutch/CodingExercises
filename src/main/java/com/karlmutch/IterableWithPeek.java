/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 * Interviewers are increasingly turning to iterator coding exercises during whiteboard sessions.
 * 
 * Exercises with iterators often take the form of asking interviewees to extend existing iterators, 
 * or to implement one of the methods of iterators on a custom data structure. One example of the
 * later might be to have a candidate implement a queue using an array, and implement for example 
 * an add method.
 * 
 * Iterators are simple enough to often fit within a whiteboard and so are quite common.
 * 
 * One common exercise is to extend an existing iterator with peek functionality.  This code example
 * does this.
 **/

package com.karlmutch;

import java.util.Iterator;
import java.util.Optional;

public class IterableWithPeek<T> implements Iterable<T> 
{
	private T [] mCollection;
	
	public IterableWithPeek(T [] collection) 
	{
		mCollection = collection;
	}

	@Override
	public Iterator<T> iterator() 
	{
		return new IteratorWithPeek();
	}

	public class IteratorWithPeek implements Iterator<T> 
	{
		private int mPosition = 0;
	
		@Override 
		public boolean hasNext() 
		{
			return (mPosition < mCollection.length);
		}
	
		@Override
		public T next() 
		{
			++mPosition;
			return (mCollection[mPosition-1]);
		}
		
		public T peek() 
		{
			if (mPosition >= mCollection.length) {
				return(null);
			}

			return (mCollection[mPosition]);
		}
		
        @Override
        public void remove() 
        {
            throw new UnsupportedOperationException();
        }
	}
}