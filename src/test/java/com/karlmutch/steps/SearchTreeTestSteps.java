/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 **/
package com.karlmutch.steps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.karlmutch.RunParameters;
import com.karlmutch.SearchTree;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SearchTreeTestSteps 
{
	private final RunParameters mParameters;

	private SearchTree<Integer, String> mSearchTree = new SearchTree<Integer, String>();
	
	public SearchTreeTestSteps(RunParameters parameters)
	{
		mParameters = parameters;
	}

	@When("^populating a binary search tree$")
	public void selectTest()
	{
		ArrayList<String> itemsInTree = new ArrayList<String>();

		itemsInTree.addAll(Arrays.asList(mParameters.mString.get().split(" ")));
		
		for (String aNode : itemsInTree) {
			List<String> keyValue = Arrays.asList(aNode.split(","));

			mSearchTree.put(Integer.parseInt(keyValue.get(0)), keyValue.get(1));
		}
	}

	@Then("the resulting levels will be:")
	public void check(List<String> expectedOutput)
	{
		// Go through the stream stripping out any lines with drawing characters
		List<String> testOutput = mSearchTree.print();

		// Use the following line if debugging is needed
		//
		// Stream.of(testOutput.toArray(new String[testOutput.size()]))
		//		.forEach(System.out::println);
		
		List<String> trimmedOutput = 
			  Stream.of(testOutput.toArray(new String[testOutput.size()]))
					.map(String::trim)
					.filter(s -> !s.contains("/"))
					.filter(s -> !s.contains("\\"))
					.collect(Collectors.toList());
		assertThat(trimmedOutput, is(expectedOutput));
	}
	
}
