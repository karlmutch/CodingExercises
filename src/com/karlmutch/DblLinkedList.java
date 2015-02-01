/**
 * The crazed notion of writing solutions with double linked lists on whiteboards
 * is something to be feared. Like rebalancing B-Tree's, questions such as recursively
 * reversing lists in Java simply do not make sense.  Being overly familiar with
 * this type of coding is a sign of Not Invented Here thinking :-)
 * 
 * TODO I will write a recursive based reverse as time allows or if some asks 
 * 		when I have this file open
 * 
 * TODO Unit Testing is not yet done
 */
package com.karlmutch;

import java.util.Optional;

public class DblLinkedList<T> {

	class Item {
		
		Item(final T data)
		{
			mData = data;
		}

		public T mData;
		public Optional<Item> mPrev = Optional.empty();
		public Optional<Item> mNext = Optional.empty();
	}	

	public Optional<Item> mFirst = Optional.empty();
	public Optional<Item> mLast = Optional.empty();

	DblLinkedList()
	{
		mFirst = Optional.empty();
	}
	
	public boolean isEmpty()
	{
		return(!mFirst.isPresent());
	}
	
	public void add(final T data)
	{
		Item newItem = new Item(data);
		
		if (isEmpty()) {
			mFirst = Optional.of(newItem);
		}
		else {
			if (mLast.isPresent()) {
				newItem.mPrev = mLast;
				mLast.get().mNext = Optional.of(newItem);
			}
			else {
				mFirst.get().mNext = Optional.of(newItem);
				newItem.mPrev = mFirst;
			}
			mLast = Optional.of(newItem);
		}
		print();
	}
	
	// The use of Java 8 offers big opportunities for optimization
	// in this method
	public void print()
	{
		StringBuffer outputList = new StringBuffer();

		Optional<Item> item = mFirst;
		while (item.isPresent()) 
		{
			outputList.append(item.get().mData.toString());
			if (!item.get().mNext.isPresent()) {
				break;
			}
			outputList.append(", ");
			item = item.get().mNext;
		}
		
		System.out.println(outputList.toString());
	}
}
