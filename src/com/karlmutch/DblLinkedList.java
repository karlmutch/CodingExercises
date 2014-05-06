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
