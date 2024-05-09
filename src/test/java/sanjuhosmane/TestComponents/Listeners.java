package sanjuhosmane.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import sanjuhosmane.resources.ExtentReporterNg;

public class Listeners extends BaseTest implements ITestListener{

	ExtentReports extent=ExtentReporterNg.getReportObject();
	 ExtentTest test;
	 ThreadLocal<ExtentTest> extenttest=new ThreadLocal<ExtentTest>();
	 
	@Override
	public void onTestStart(ITestResult result) {
	   test=extent.createTest(result.getMethod().getMethodName());
	   extenttest.set(test);
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		extenttest.get().log(Status.PASS,"Test Passed");
		
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		extenttest.get().fail(result.getThrowable());
		String filePath=null;
		try {
			driver=(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			 filePath=getScreenShot(result.getMethod().getMethodName(),driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
		extenttest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}
	
	@Override 
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
