/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 * On occasion interviewers like to talk about sorting.  Much has occurred over the years 
 * since some of the well known sorting algorithms were initially conceived of.  As 
 * engineers have become used to large data sets and parallelism has crept into our
 * everyday use of computers through improved compilers and the like sort algorithms
 * have steadily matured.  Most modern libraries and runtime environments with sorting 
 * capabilities now employ hybrid sorting.  Multiple sorting technique are combined.
 * One popular one is called Timsort which employs a mixture of an insertion sort,
 * followed by a merge sort.
 * 
 * Often the reason behind the selection of the sorts that are combined are entirely
 * pragmatic.  Insertion sort for example is used in Timsort because it has been
 * found in practice to be much more efficient on small and lightly disordered data.
 * 
 * Because of the massive variety of algorithms now available it seems a little more
 * of a memory exercise when asked about an one of many arbitrary options available.
 * 
 * Timsort has become widely popular and is now used within Python where it originated,
 * Java starting in 7, and the Android SDK.
 * 
 * Being fairly lengthy to write when asked about this option you might be best to talk
 * about the building blocks it is based upon.
 * 
 * This code example implements the trivial insertion sort portion of our Timsort solution
 */

package com.karlmutch;

public class InsertionSort 
{
	// The following two variables are used to track the number of operations performed by
	// the various implementations of the insertion sort to allow comparisons of each
	// version
	static public long sCompares = 0;
	static public long sSwaps = 0;
	static public long sAssigns = 0;
	
	private static void resetCounters()
	{
		sCompares = 0;
		sSwaps = 0;
		sAssigns = 0;
	}
	/**
	 * This method implements an unoptimized insertion sort
	 * 
	 * This method will overwrite the input and return the reference to it
	 * 
	 * @param sortMe An array list that will be overwritten
	 */
	public static <T extends Comparable<? super T>> T [] sort(T [] sortMe)
	{
		resetCounters();		// Reset the performance tracking counters
		if (null == sortMe || sortMe.length <= 1) {
			return(sortMe);
		}

		// Lists of 1 of course are already sorted :-) so start at position 1
		for (int i = 1 ; i < sortMe.length ; ++i) 
		{
			// The compares portion of the conditional portion of the for loop is there
			// simply to track comparisons inside our various implementations
			for (int j = i ; (j > 0) && (0 != ++sCompares) && (0 > sortMe[j].compareTo(sortMe[j-1])) ; --j) 
			{
				// Someone will ask for a swap without a temporary in your lifetime
				// when they have run out of other things to ask :-)
				//
				// It wont work here as we have an arbitrary object but it does not
				// matter anyway as registers will typically be the only thing hit
				// when swapping, and in many cases we are just dealing with references
				// in any event
				++sSwaps;
				T temporary = sortMe[j];
				sortMe[j] = sortMe[j-1];
				sortMe[j-1] = temporary;
			}
		}
		return (sortMe);
	}
	
	/**
	 * This method of the insertion sort uses several optimizations to reduce comparison
	 * operations by using search intervals and binary search operations.
	 * 
	 * In the best case it still works using O(N) and in worst and
	 * average case reduces the number of comparisons.
	 * 
	 * This method will overwrite the input and return the reference to it
	 * 
	 * @param sortMe An array list that will be overwritten
	 */
	public static <T extends Comparable<? super T>> T [] sortIntervals(T [] sortMe)
	{
		resetCounters();		// Reset the performance tracking counters

		if (null == sortMe || sortMe.length <= 1) {
			return(sortMe);
		}

		// This optimization of insertion sorting uses the temporary that stores 
		// the current known highest variable of the sorted portion of the array
		// that could be swapped on every outer step of our scanning loop.  
		// This in effect creates a hole
		// inside the array where we started and fills it back in after 
		// looking into the elements above the insertion location, that is the place
		// where any change will occur
		T temporary;

		// Lists of 1 of course are already sorted :-) so start at position 1
		for (int i = 1 ; i < sortMe.length ; ++i) 
		{
			temporary = sortMe[i];	// i is now the 
			int left = 0;			// The lower bound offset from the insertion point, and
			int right = i;			// the upper bound within which the binary search will 
			int step = 1;			// take place 
			
			int center;			// Arithmetic center of the range being searched, calculated via approximation
			
			// Do some approximation inside the range to take a stab at where
			// our center point might be by starting close to the end of the existing
			// sorted portion of the array then stepping back into our sorted range 
			// looking for a likely center
			do {
				center = right - step;
				step <<= 1;				// This will start small a grow by powers of two as we search
				
				++sCompares;			// Track the operations
				if (0 > temporary.compareTo(sortMe[center])) {
					right = center;
				}
				else {
					left = center + 1;
				}
			} while (left + step <= right);

			// Now we have an approximation we use a standard binary chop
			// style search
			while (left != right) {
				center = left + (right-left) / 2;	// Cut the range being searched in half
				
				++sCompares;
				if (0 > temporary.compareTo(sortMe[center])) {
					right = center;
				}
				else {
					left = center + 1;
				}
			}
			
			// Now shift the items starting from the insertion point up to leave
			// a gap for insertion
			int j = i;
			for ( ; j > left; --j) {
				++sAssigns;
				sortMe[j] = sortMe[j-1];
			}
			++sAssigns;
			sortMe[j] = temporary;
		}

		return (sortMe);
	}
}
