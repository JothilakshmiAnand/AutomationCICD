package CucumberFramework;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;



@CucumberOptions(features = "src/test/java/CucumberFramework", glue = "NewTest.StepDefn",
monochrome = true, tags = "@ErrorValidation", plugin = {"html:target/cucumber.html"})
public class TestNGCucumberRunner extends AbstractTestNGCucumberTests {
   
}

