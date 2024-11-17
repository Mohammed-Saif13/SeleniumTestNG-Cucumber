package ecom.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG 
{
	static public ExtentReports extent;
	
	public static ExtentReports getReporterObject()
	{
		String path =System.getProperty("user.dir"+"\\reports\\index.html");
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("Test Results");
		reporter.config().setReportName("Purchase Order Results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Saif");
		return extent ;
		
	}

}
