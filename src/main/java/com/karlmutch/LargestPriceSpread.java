/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 * Writing coding interview questions hasn't made me rich. Maybe trading Apple stocks will.
 *	I have an array stock_prices_yesterday where:
 *
 * The indices are the time in minutes past trade opening time, which was 9:30am local time.
 * The values are the price in dollars of Apple stock at that time.
 * For example, the stock cost $500 at 10:30am, so stock_prices_yesterday[60] = 500.
 * 
 * Write an efficient algorithm for computing the best profit I could have made from 1 purchase and 1 sale of 1 Apple stock yesterday.
 * 
 * No "shorting"—you must buy before you sell. You may not buy and sell in the same time step (at least 1 minute must pass).
**/
package com.karlmutch;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.TreeMap;

public class LargestPriceSpread 
{
	static public class BuySellBracket 
	{
		public BuySellBracket() {
			buyMinute = 0;
			buyPrice = Integer.MAX_VALUE;
			sellMinute = Integer.MAX_VALUE;
			sellPrice = Integer.MIN_VALUE;
		}
		
		BuySellBracket(final BuySellBracket other)
		{
			buyMinute = other.buyMinute;
			buyPrice = other.buyPrice;
			sellMinute = other.sellMinute;
			sellPrice = other.sellPrice;			
		}
		
		public Integer buyMinute;
		public Integer buyPrice;

		public Integer sellMinute;
		public Integer sellPrice;
	};

	public static Optional<BuySellBracket> GetLargestPositiveSpread(Integer [] stock_prices_yesterday)
	{
		
		// Build a set of times sorted into their price order to allow highest lowest style queries
		TreeMap<Integer, ArrayList<Integer>> priceToTimes = new TreeMap<Integer, ArrayList<Integer>>(); 
		
		Integer yesterdaysMinute = 0;
		for ( Integer aPrice : stock_prices_yesterday) {
			
			if (!priceToTimes.containsKey(aPrice)) {
				priceToTimes.put(aPrice, new ArrayList<Integer>());
			}

			priceToTimes.get(aPrice).add(yesterdaysMinute);
			yesterdaysMinute++;
		}

		// Now for every low price, high price pair check the minutes in the day they occurred and they
		// were in the right order to get the widest spread and use that as our low and high.  This has to be done
		// both for the lowest point in the day and the highest point in the day
		BuySellBracket largestSpreadByLowestBuyPrice = new BuySellBracket();

		largestSpreadByLowestBuyPrice.sellPrice = 0;
		largestSpreadByLowestBuyPrice.sellMinute = priceToTimes.size();

		for (Entry<Integer, ArrayList<Integer>> buySide : priceToTimes.entrySet()) 
		{
			largestSpreadByLowestBuyPrice.buyPrice = buySide.getKey();
			for (Integer timeSlot : buySide.getValue()) 
			{
				largestSpreadByLowestBuyPrice.buyMinute = timeSlot;
				
				// Make sure that we can identify any point in the day after the buy that
				// there was an opportunity to sell, by using a reverse iterator of sorts
				// bumping along until we either move to a loosing position or we find a
				// later time of the day that would work for the sell				
				for (Entry<Integer, ArrayList<Integer>> sellSide : priceToTimes.descendingMap().entrySet()) 
				{
					if (sellSide.getKey() >= largestSpreadByLowestBuyPrice.buyPrice) {
						// No point to this transaction anymore
						break;
					}
					for (Integer sellTimeSlot : sellSide.getValue()) 
					{
						if (sellTimeSlot > largestSpreadByLowestBuyPrice.buyMinute) {
							largestSpreadByLowestBuyPrice.sellMinute = sellTimeSlot;
							largestSpreadByLowestBuyPrice.sellPrice = sellSide.getKey();
							break;
						}
					}					
				}
			}
			
			if (0 != largestSpreadByLowestBuyPrice.sellPrice) {
				// We have a solution from the buy side that gives us a selling opportunity
				break;
			}
		}

		// It could be the case that moving from the lowest buy prices to the highest possible
		// sell price might give us a less than optimal solution because the highest sell 
		// price of the day could have been before the lowest possible buy price.  If we used an 
		// earlier but higher buy price then we might be able to exploit the earlier high so lets see if
		// this is possible
		BuySellBracket largestSpreadByHighestSellPrice = new BuySellBracket();

		largestSpreadByHighestSellPrice.buyPrice = Integer.MAX_VALUE;
		largestSpreadByHighestSellPrice.buyMinute = 0;
		
		for (Entry<Integer, ArrayList<Integer>> sellSide : priceToTimes.descendingMap().entrySet()) 
		{
			largestSpreadByLowestBuyPrice.sellPrice = sellSide.getKey();
			for (Integer timeSlot : sellSide.getValue()) 
			{
				largestSpreadByLowestBuyPrice.buyMinute = timeSlot;
				
				// Make sure that we can identify any point in the day before the sell that
				// there was an opportunity to bug, 
				// bumping along until we either move to a loosing position or we find an
				// earlier time of the day that would work for the buy				
				for (Entry<Integer, ArrayList<Integer>> buySide : priceToTimes.entrySet()) 
				{
					if (buySide.getKey() >= largestSpreadByHighestSellPrice.sellPrice) {
						// No point to this transaction anymore
						break;
					}
					for (Integer buyTimeSlot : buySide.getValue()) 
					{
						if (buyTimeSlot < largestSpreadByHighestSellPrice.sellMinute) {
							largestSpreadByLowestBuyPrice.buyMinute = buyTimeSlot;
							largestSpreadByLowestBuyPrice.buyPrice = buySide.getKey();
							break;
						}
					}					
				}
			}
			
			if (Integer.MAX_VALUE != largestSpreadByHighestSellPrice.buyPrice) {
				// We have a solution from the sell side that gives us a buying opportunity
				break;
			}
		}

		// Now we check to see which is the optimal solution or if the sock was in a loosing situation all day
		if (largestSpreadByHighestSellPrice.buyPrice == Integer.MAX_VALUE) {
			if (0 == largestSpreadByLowestBuyPrice.sellPrice) {
				return Optional.empty();				
			}
			return Optional.of(largestSpreadByLowestBuyPrice);
		}
		else {
			if ((largestSpreadByHighestSellPrice.sellPrice - largestSpreadByHighestSellPrice.buyPrice) > 
				(largestSpreadByLowestBuyPrice.sellPrice - largestSpreadByLowestBuyPrice.buyPrice)) 
			{
				return Optional.of(largestSpreadByHighestSellPrice);			
			}
			else {
				return Optional.of(largestSpreadByLowestBuyPrice);
			}
		}
	}

	public static Optional<BuySellBracket> GetLargestPositiveSpread_OnePass(Integer [] stock_prices_yesterday)
	{
		BuySellBracket bestTrade = new BuySellBracket();

		BuySellBracket workingTrade = new BuySellBracket();
		workingTrade.buyMinute = 0;
		workingTrade.buyPrice = stock_prices_yesterday[0];

		Integer minute = -1;

		for (Integer aPrice : stock_prices_yesterday) {

			++minute;

			// The sell side will always be able to be bumped if there is an older best trade
			if (bestTrade.sellPrice < aPrice) {

				bestTrade.sellPrice = aPrice;
				bestTrade.sellMinute = minute;
			}

			// Is this a potential sell point ?
			if ((aPrice > workingTrade.buyPrice) && (aPrice > workingTrade.sellPrice)) 
			{
				// If this is a sell point that is better than the previous one within the working trade
				// window then use it
				workingTrade.sellPrice = aPrice;
				workingTrade.sellMinute = minute;
				continue;
			}
			
			// If this is not a sell point, it could be a buy point.
			if (aPrice < workingTrade.buyPrice) 
			{
				// If this minute has the potential to be a buy point because the price dipped then
				// take the existing trade and see if it is better than the best trade we currently 
				// have going before attempting to formulate a new entry point for a trade
				if ((workingTrade.sellPrice > workingTrade.buyPrice) &&
					((workingTrade.sellPrice - workingTrade.buyPrice) > (bestTrade.sellPrice - bestTrade.buyPrice)) )
				{
					// The working trade has become the best trade 
					bestTrade = new BuySellBracket(workingTrade);
				}

				// Get ready for a new working trade by taking the lowest buy point so far
				// and getting ready to look for for the highest sell point we can find after
				// the current point in time
				if (bestTrade.buyPrice < aPrice) {
					workingTrade.buyMinute = bestTrade.buyMinute;
					workingTrade.buyPrice = bestTrade.buyPrice;
				}
				else {
					workingTrade.buyMinute = minute;
					workingTrade.buyPrice = aPrice;
				}
				workingTrade.sellMinute = minute;
				workingTrade.sellPrice = Integer.MIN_VALUE;
			}
		}
		
		// Check to see which of the current best and the working trade is the one we should be used
		if ((workingTrade.sellPrice > workingTrade.buyPrice) &&
			((workingTrade.sellPrice - workingTrade.buyPrice) > (bestTrade.sellPrice - bestTrade.buyPrice)) )
		{
			// The working trade has become the best trade 
			bestTrade = new BuySellBracket(workingTrade);
		}

		return Optional.of(bestTrade);
	}
}
