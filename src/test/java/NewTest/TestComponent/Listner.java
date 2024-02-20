package NewTest.TestComponent;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import NewTest.resources.ReportExtent;

public class Listner extends BaseTest implements ITestListener {
	
	ExtentTest test;
	//ExtentReports extent = ReportExtent.getReportExtent();
	//ExtentReports extent = ReportExtent.getReportExtent();
	//In parallel execution , due to concurrency issue, the result of test ll be mapped wrongly to avoid this we are using Threadlocal
			ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
	//	test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.PASS, "Test Passed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
		//If test fails, ask it to show errormessage using below code
		extentTest.get().fail(result.getThrowable());
		// to declare driver
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//call the screenshot method and pass the result value
		String FilePath = null;
		try {
		FilePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// to insert the screenshot in the report
		extentTest.get().addScreenCaptureFromPath(FilePath, result.getMethod().getMethodName());
		
		//pass this Listeners in XML file
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		//extent.flush();
	}


}
