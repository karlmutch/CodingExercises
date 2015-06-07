/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 * Overlapping range examples come in many forms.  One form I found after completion that gave me additional
 * use cases can be found at https://www.interviewcake.com/question/merging-ranges
 * 
 * While this test case dose not do adjacencies this would be a simple variation of what we do have so far
 * 
 * Your company built an in-house calendar tool called HiCal. You want to add a feature to see the times 
 * in a day when everyone is available.
 * To do this, you’ll need to know when any team is having a meeting. In HiCal, a meeting is stored as a
 * tuple of integers (start_time, end_time) . These integers represent the number of 30-minute blocks past 9:00am.
 * 
 * For example:
 * 
 * (2, 3) # meeting from 10:00 – 10:30 am
 * (6, 9) # meeting from 12:00 – 1:30 pm
 * 
 * Write a function condense_meeting_times() that takes an array of meeting time ranges and returns an 
 * array of condensed ranges.
 * 
 * For example, given:
 * 
 * [(0, 1), (3, 5), (4, 8), (10, 12), (9, 10)]
 * 
 * your function would return:
 * 
 * [(0, 1), (3, 8), (9, 12)]
 * 
 * Do not assume the meetings are in order. The meeting times are coming from multiple teams.
 * 
 * In this case the possibilities for start_time and end_time are bounded by the number of 
 * 30-minute slots in a day. But soon you plan to refactor HiCal to store times as Unix 
 * timestamps (which are big numbers). Write something that's efficient even when we 
 * can't put a nice upper bound on the numbers representing our time ranges. 
 */
package com.karlmutch;

import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class OverlappingRanges 
{
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
			// Assume that each of the two ranges start and end follow (start <= end)
			return ((start <= rightHandSide.end) && (rightHandSide.start <= end));
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

	public boolean compress()
	{
		boolean anyChanges = false;

		TreeSet<Range> newRanges = new TreeSet<Range>();
		
		Optional<Range> mWorkingRange = Optional.empty();
		for (Range aRange : mRanges) {
			if (mWorkingRange.isPresent()) {
				if (aRange.overlaps(mWorkingRange.get())) {
					mWorkingRange = mWorkingRange.get().merge(aRange);
					anyChanges = true;
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
		
		anyChanges |= (mRanges.size() != newRanges.size());
		mRanges = newRanges;
		
		return(anyChanges);
	}
	
	@Override
	public String toString()
	{
		return(mRanges.stream().map(Range::toString).collect(Collectors.joining(", "))); 
	}

	// Using a Tree Set here allows the operations operating in order to be predictable
	TreeSet<Range> mRanges = new TreeSet<Range>();
}
