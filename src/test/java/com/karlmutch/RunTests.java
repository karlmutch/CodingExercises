/**
 *
 * (c) 2015 by Karl Mutch is licensed under a Creative Commons Attribution 4.0 International License.
 * 
 */
package com.karlmutch;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import org.junit.runner.RunWith;

@CucumberOptions(features="resources/com/karlmutch")
@RunWith(Cucumber.class)
public class RunTests {
}