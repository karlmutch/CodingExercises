/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 */
package com.karlmutch;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Main {

	public static void main(String[] args) 
	{
		// Currently these tests are fairly rudimentary and will be converted to a unit test framework
		// when time allows
		DblLinkedList<String> list = new DblLinkedList<String>();
		
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");

		List<SearchTree<String, String>.Node> nodes = new ArrayList<SearchTree<String, String>.Node>();
		SearchTree<String, String> enclosingType = new SearchTree<String, String>();
		nodes.add(enclosingType.new Node("1", "1", 0));
		nodes.add(enclosingType.new Node("2", "2", 0));
		nodes.add(enclosingType.new Node("3", "3", 0));
		SearchTree<String, String> bTree = new SearchTree<String, String>(nodes);
		
		IntStream.range(4, 10).forEach(
				number -> { bTree.put(String.valueOf(number), String.valueOf(number)) ;
							bTree.print() ; 
							System.out.println("Tree is " + (bTree.isValid() ? "" : "in") + "valid");							
				});

		System.out.println(String.join(",", bTree.traverseSpiral()));

		OverlappingRanges ranges = new OverlappingRanges();
		//  (1,3), (3,5), (7,9), (12,30), (32,42) ] and (9,31)
		ranges.add(new OverlappingRanges.Range(1, 3));
		ranges.add(new OverlappingRanges.Range(3, 5));
		ranges.add(new OverlappingRanges.Range(7, 9));
		ranges.add(new OverlappingRanges.Range(12, 30));
		ranges.add(new OverlappingRanges.Range(32, 42));
		System.out.println(ranges.toString());
		
		ranges.add(new OverlappingRanges.Range(9, 31));
	}
}
