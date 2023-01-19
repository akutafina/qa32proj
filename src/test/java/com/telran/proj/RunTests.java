package com.telran.proj;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
      tags = "~@wip",
//      tags = "(@smoke or @ui) and (not @slow)",
        glue = {"com.telran.proj.steps"},
//      plugin = {"pretty","json:build/cucumber-report/cucumber.json"}
        plugin = {"pretty", "html:target/cucumber.html"}
)

public class RunTests {

}