package NewTest.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportExtent {
	
	 

	public static ExtentReports getReportExtent()
	{
		ExtentReports extent;
		String path = "C:\\Users\\Dell\\eclipse-workspace2\\SeleniumFrameworkDesign\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		// set the report name in the html
		reporter.config().setReportName("Web Automation Results");
		// set the title of the html
		reporter.config().setDocumentTitle("Sprint1 Test Result");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		//set ur name as Tester
		extent.setSystemInfo("Tester", "Jothi");
		return extent;
	}
}
