package utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Testbase.BaseClass;

public class ExtentReportUtility  implements ITestListener{
	public ExtentSparkReporter sparkreporter;
	public ExtentReports extents;
	public ExtentTest test;
	String repName;
	
	public void onStart(ITestContext context) {
		String timestamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName="Test-report" + timestamp +".html";
		sparkreporter = new ExtentSparkReporter(".\\report\\" + repName);
		
		sparkreporter.config().setDocumentTitle(" OpenCart Automation report"); //Title of the Report 
		sparkreporter.config().setReportName("Functional testing");   //name of the Report
		sparkreporter.config().setTheme(Theme.DARK);  //theme for the report 
		
		extents=new  ExtentReports();
		extents.attachReporter(sparkreporter);
		
		extents.setSystemInfo("Application Name", "OpenCart");
		extents.setSystemInfo("Environment", "QA");
		extents.setSystemInfo("Tester Name", "Akash");
		extents.setSystemInfo("OS", "Windows 11");
		extents.setSystemInfo("Browser Name", "Chrome");
	}
	
	public void  onTestSuccess(ITestResult result) {
		test=extents.createTest(result.getTestClass().getName()); //Create a new entery in the report 
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS,result.getName()+ "Test Case Executed Successfully");	//update the status 
		
	}
	
	public void onTestFailure(ITestResult result) {
		test=extents.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, "The Failed Test Case is :"+result.getName());
		test.log(Status.FAIL, "The Reson why the test case is failed:"+result.getThrowable());  //logs the error into the test report 
		
		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		}
		catch(IOException e1) {
			e1.printStackTrace();
			
		}
	}
	
	public void onTestSkipped(ITestResult result) {
		test=extents.createTest(result.getName());
		
		test.log(Status.SKIP, "The Skipped Test Cases are :"+result.getName());
		
	}
	public void onFinish(ITestContext context) {
		extents.flush();
		
	}

}
