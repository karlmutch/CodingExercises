/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 */
package com.karlmutch;

import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class OverlappingRanges {

	public OverlappingRanges()
	{
	}
	
	static public class Range implements Comparable<Range>
	{
		public int start;
		public int end;

		public Range(int start, int end)
		{
			this.start = start;
			this.end = end;
		}

		@Override 
		public boolean equals(Object rightHandSide) 
		{
		    if (this == rightHandSide) return (true);
		    if (!(rightHandSide instanceof Range)) return (false);

		    Range that = (Range)rightHandSide;
		    return ((start == that.start) && (end == that.end));
		}

		@Override
		public int compareTo(Range rightHandSide)
		{
			final int BEFORE = -1;
			final int EQUAL = 0;
			final int AFTER = 1;

		    if (this.equals(rightHandSide)) return EQUAL;			

		    if ((start < rightHandSide.start) || ((start == rightHandSide.start) && (end < rightHandSide.end))) {
		    	return(BEFORE);
		    }
		    return (AFTER);
		}
		
		public boolean overlaps(final Range rightHandSide)
		{
			// Treat adjacent ranges as though they were overlaps			
			return (((start >= rightHandSide.start) && (start <= rightHandSide.end)) || 
					((end <= rightHandSide.end) && (end >= rightHandSide.start)));
		}

		public Optional<Range> merge(final Range rightHandSide)
		{
			if (!overlaps(rightHandSide)) {
				return (Optional.empty());
			}
			
			Range mergedRange = new Range(Integer.min(start, rightHandSide.start),
										  Integer.max(end, rightHandSide.end));
			
			return(Optional.of(mergedRange));
		}
		
		@Override
		public String toString()
		{
			return(new StringBuilder().append("{").append(start).append(", ").append(end).append("}").toString());
		}
	}

	public void add(final Range newRange)
	{
		Optional<Range> mergedItem = Optional.empty();

		for (Range existingRange : mRanges) 
		{
			mergedItem = existingRange.merge(newRange);

			if (mergedItem.isPresent()) {
				mRanges.remove(existingRange);
				break;
			}
		}

		if (mergedItem.isPresent()) {
			mRanges.add(mergedItem.get());
		}
		else {
			mRanges.add(newRange);
		}
	}

	public void compress()
	{
		TreeSet<Range> newRanges = new TreeSet<Range>();
		
		Optional<Range> mWorkingRange = Optional.empty();
		for (Range aRange : mRanges) {
			if (mWorkingRange.isPresent()) {
				if (aRange.overlaps(mWorkingRange.get())) {
					mWorkingRange = mWorkingRange.get().merge(aRange);
					continue;
				}
				else {
					newRanges.add(mWorkingRange.get());
				}
			}
			mWorkingRange = Optional.of(aRange);
		}
		
		if (mWorkingRange.isPresent()) {
			newRanges.add(mWorkingRange.get());
		}
		mRanges = newRanges;
	}
	
	@Override
	public String toString()
	{
		return(mRanges.stream().map(Range::toString).collect(Collectors.joining(", "))); 
	}

	// Using a Tree Set here allows the operations operating in order to be predictable
	TreeSet<Range> mRanges = new TreeSet<Range>();
}
