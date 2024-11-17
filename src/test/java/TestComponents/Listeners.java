package TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import ecom.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener
{

	static public ExtentTest test;
	
	ExtentReports extent = ExtentReporterNG.getReporterObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();	
	@Override
	public void onTestStart(ITestResult result) 
	{
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
//		test.log(Status.PASS,"Test Passed");
		extentTest.get().log(Status.PASS,"Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		//test.log(Status.FAIL, "Test Failed");
		extentTest.get().fail(result.getThrowable()); 
		
		try {
			driver =(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		}
		catch(Exception e1){
			e1.printStackTrace();
		}
		
		String filePath = null;
		try {
			filePath =getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
	
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	public void onFinish(ITestResult result)
	{
		extent.flush(); //generates reports 
	}

}
