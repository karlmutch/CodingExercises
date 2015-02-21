package com.karlmutch;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "com.karlmutch.steps",
        features = "classpath:com/karlmutch"
)
public class RunUnitTest {
}
