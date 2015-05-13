/**
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 * Given an array_of_ints, find the highest_product you can get from three of the integers. From Interview Cake.
 */
package com.karlmutch;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Optional;
import java.util.PriorityQueue;

public class LargestProduct 
{
	static public class MinComparator implements Comparator<BigInteger>
	{
	    public int compare( BigInteger left, BigInteger right )
	    {
	        return right.compareTo(left);
	    }
	}
	public static BigInteger HighestNProduct(BigInteger [] input, int itemsInProduct) 
	{
		// O(n log n) Time O(1) Space
		if ((itemsInProduct < 2) ||
		    (itemsInProduct > input.length)) 
		{
			return (BigInteger.ZERO);
		}

		// Any method by which the maximum product of any n arbitrary items in the input
		// array can be arrived at have a constant amount of space required typically the
		// number of items in the product.
		
		// To solve the issue of tracking the maximum product of an arbitrary number of items
		// in this case a min and max heap will be used.  In the min heap the lowest numbers
		// will be kept and in the max heap the largest.  The only reason to have the min heap 
		// is to allow negative numbers to be catered for which could in some cases be larger
		// in an absolute sense than the positive numbers and when multiplied together become
		// positive.
		//
		// Java : Implementation note: this implementation provides O(log(n)) time for the enqueing and 
		// dequeue methods (offer, poll, remove() and add); linear time for the remove(Object) and 
		// contains(Object) methods; and constant time for the retrieval methods (peek, element, and size).
		//
		// For a min max heap finding the min max respectively is an O(1), insertion is O(long n),
		// however see blow for a short cut to prevent insertions.
		PriorityQueue<BigInteger> minHeap = new PriorityQueue<BigInteger>(itemsInProduct);
		PriorityQueue<BigInteger> maxHeap = new PriorityQueue<BigInteger>(itemsInProduct, new MinComparator());
		
		// As we scan the inputs we will track the numbers that are discarded from the heaps 
		// as a shortcut to prevent new numbers being inserted into the heap and then immediately discarded
		Optional<BigInteger> largestDiscarded = Optional.empty();
		Optional<BigInteger> smallestDiscarded = Optional.empty();

		if (0 != minHeap.size()) {
			smallestDiscarded = Optional.of(minHeap.peek());
		}
		
		if (0 !=maxHeap.size()) {
			largestDiscarded = Optional.of(maxHeap.peek());
		}

		for (BigInteger anItem : input) 
		{
			// Check to see if our logic has yet to full initialize our heaps. If not
			// we add each item into each heap doing full discards
			if (anItem.signum() < 0) {
				if (smallestDiscarded.isPresent()) {
					// If we had a negative input and it was larger than the largest
					// discarded minimum it will not be included so skip it
					if (anItem.compareTo(smallestDiscarded.get()) < 0) {
						continue;
					}
				}

				// Having tested for any short cuts we now revert to using the heap.  When using the heap
				// after insertion log n, look at the length and pop off items that are no longer relevant
				// to maintain O(1) space constraints
				//
				// O(log n)
				minHeap.add(anItem);
				while (minHeap.size() > itemsInProduct) {
					// O(log n)   Pulls the front item from the queue
					minHeap.poll();
				}
			}
			else {
				if (largestDiscarded.isPresent()) {
					// Now check to see if the supplied integer is smaller than, or equal to
					// the highest one discarded.  If it is then it wont be of interest.
					if (anItem.compareTo(largestDiscarded.get()) < 0) {
						continue;
					}
				}
				// Having tested for any short cuts we now revert to using the heap.  When using the heap
				// after insertion O(log n), look at the length and pop off items that are no longer relevant
				// to maintain O(1) space constraints
				//
				// O(log n)
				maxHeap.add(anItem);
				
				while (maxHeap.size() > itemsInProduct) 
				{
					// Pulls the front item from the queue
					//
					// O(log n)
					BigInteger newSmallMax = maxHeap.poll();
					if (null != newSmallMax) {
						if (largestDiscarded.isPresent()) {
							largestDiscarded = Optional.of(anItem.max(largestDiscarded.get()));
						}
						else {
							largestDiscarded = Optional.of(anItem);
						}
					}
				}
			}
		}			// End of for (BigInteger anItem : input)

		// Now we look for the largest products greedily
		//
		// Suck in the largest positive numbers into a sorted set
		LinkedList<BigInteger> largestIntegers = new LinkedList<BigInteger>();

		while ((null != maxHeap.peek())) {
			largestIntegers.add(maxHeap.poll());
		}

		// It is possible that pairs of negative numbers could be multiplied together to create larger products
		// than the positive integers we have captured.  For this to work the negative values should be examined
		// with the lowest pairs inside the positive integer sets and the positive ones overwritten if their
		// products are smaller
		LinkedList<BigInteger> twoProductItems = new LinkedList<BigInteger>();
		while (minHeap.size() > 1) {
			BigInteger factor1 = minHeap.poll();
			BigInteger factor2 = minHeap.poll();
			
			twoProductItems.add(factor1.multiply(factor2));
		}
		
		// Now take the top n numbers from the set for negative number products and the individual large positive numbers
		// and multiple them together to get the result
		BigInteger result = BigInteger.ONE;
		int product = itemsInProduct;
		
		BigInteger largestFromMaxPool = largestIntegers.peek();
		BigInteger largestFromMinPool = twoProductItems.peek();

		if (null != largestFromMaxPool) {
			largestIntegers.removeFirst();
		}
		
		if (null != largestFromMinPool) {
			twoProductItems.removeFirst();
		}
		
		while (product > 0 && (null != largestFromMinPool) || (null != largestFromMaxPool))
		{
			// Check to see if we can use the single max value for the product
			if ((product == 1) ||
				(null == largestFromMinPool) ||
				(largestIntegers.isEmpty() && (0 > largestFromMinPool.compareTo(largestFromMaxPool))) ||
				(0 > largestFromMinPool.compareTo(largestFromMaxPool.multiply(largestIntegers.peek())))) 
			{
				result = result.multiply(largestFromMaxPool);
				--product;
				if (!largestIntegers.isEmpty()) {
					largestIntegers.removeFirst();
				}
				largestFromMaxPool = largestIntegers.peek();
				continue;
			}
			
			// We have a condition where the product of two negative items is larger 
			// than the two largest values in the single positive integer max heap
			result = largestFromMinPool.multiply(result);
			product -= 2;
			
			if (!twoProductItems.isEmpty()) {
				twoProductItems.removeFirst();
			}
			largestFromMinPool = twoProductItems.peek();
		}
		
		return(result);
	}
}
