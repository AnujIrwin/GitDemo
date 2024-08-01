package SeleniumFramework.TestComponents;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import SeleniumFramework.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	
	ExtentReports extent = ExtentReporterNG.getReportObject();   ////As inside ExtentReporterClass we are using the method with static keyword hence without even creating the object for that class we are able to directly access the class
	ExtentTest test;
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
	}
	
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Case Passed");
	}
	
	public void onTestFailure(ITestResult result) {
		test.fail(result.getThrowable());
		String filepath = null;
		try {
			 filepath = getScreenshot(result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		test.addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
		// Now to take screenshot what to be done
	}
}
