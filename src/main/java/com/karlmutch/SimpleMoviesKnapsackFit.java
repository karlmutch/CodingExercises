/**
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 * This code example solves a knapsack packing example detailed on the cakeinterview.com website.
 * 
 *  You've built an in-flight entertainment system with on-demand movie streaming.
 *  
 *  Users on longer flights like to start a second movie right when their first one ends, but they complain 
 *  that the plane usually lands before they can see the ending. So you're building a feature for choosing 
 *  two movies whose total run-times will equal the exact flight length.
 *  
 *  Write a function that takes an integer flight_length (in minutes) and an array of integers 
 *  movie_lengths (in minutes) and returns a boolean indicating whether there are two numbers in 
 *  movie_lengths whose sum equals flight_length.
 *  
 *  When building your function:
 *  
 *  Assume your users will watch exactly two movies. Don't make your users watch the same movie twice. Optimize for runtime over memory
 *  
 * Bonus
 * 
 * What if we wanted the movie lengths to sum to something close to the flight 
 * length (say, within 20 minutes)? What if we wanted to fill the flight length as 
 * nicely as possible with any number of movies (not just 2)?
 */
package com.karlmutch;

import java.util.HashMap;

public class SimpleMoviesKnapsackFit 
{
	private static class Movie
	{
		long mLength;			// The run time of each movie
		long mMovieCount;		// The number of movies that are this long
		long mMoviesWatched;	// The number of movies that are this long, and have been already allocated

		public Movie(long length)
		{
			mLength = length;
			mMovieCount = 1;
			mMoviesWatched = 0;
		}
		
		public boolean watchMovie()
		{
			if (mMoviesWatched >= mMovieCount) { return(false); } ;
			mMoviesWatched++;

			return(true);
		}
	};

	public static boolean CanFit(long flight_length, Long [] movieLengths)
	{
		// This is inefficient compared to an approach that implements a sparse array which
		// uses the movie length as the index.  Android Java has a sparse array implementation
		// however the JDK does not.  To implement this one method is to have a class that 
		// encapsulates two arrays.  The first array contains the index and the second array 
		// encapsulates the data items
		HashMap<Long, Movie> movies = new HashMap<Long, Movie>();
		
		for (long aLength : movieLengths) {
			if (!movies.containsKey(aLength)) {
				movies.put(aLength, new Movie(aLength));
				continue;
			}
			movies.get(aLength).mMovieCount++;
		}
		
		for (Movie aMovie : movies.values()) {
			aMovie.mMoviesWatched++;

			if (movies.containsKey(flight_length - aMovie.mLength)) 
			{
				if (movies.get(flight_length - aMovie.mLength).watchMovie()) {
					return(true);
				}
			}

			aMovie.mMoviesWatched--;
		}
		return (false);
	}
}
