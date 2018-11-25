package com.automationcalling.testrunner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.testng.TestNGCucumberRunner;


@CucumberOptions(
        features = {"src/test/resources/wcag_508_featuretest/WCAG_508_Compliance_Test.feature"},
        glue = {"com.automationcalling.stepdefinition"},
        plugin = {"pretty", "html:target/cucumber-report", "json:target/cucumber.json"})
public class Runner extends AbstractTestNGCucumberTests {
}
