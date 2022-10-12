package listners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseClasses.BaseClass1;


public class ListnerClass1 implements ITestListener{
	ExtentTest extentTest;
	
	
	
	public void onTestStart(ITestResult result) {
		extentTest=BaseClass1.getAlreadyExistingTest();
		extentTest.log(Status.INFO, "Test: started"+result.getName());
		
		  }

		  
		public void onTestSuccess(ITestResult result) {
			
			extentTest.log(Status.PASS, "Test: Pass"+result.getName());
			
			
		  }

		 
		public void onTestFailure(ITestResult result) {
			
			extentTest.log(Status.FAIL, "Test: Failed"+result.getName());
			
			
		  }

		  
		public void onTestSkipped(ITestResult result) {
		   
			
			extentTest.log(Status.SKIP, "Test: Skiped"+result.getName());
			
		  }
	
	
		
}
