package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/Cucumber",glue="sanjuhosmane.stepdefinition"
,monochrome=true,tags="@Regression",plugin= {"html:target/cucmber.html"})

public class TestNgTestRunner extends AbstractTestNGCucumberTests{
	

}
