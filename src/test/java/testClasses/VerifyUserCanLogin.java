package testClasses;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import org.testng.AssertJUnit;
import org.testng.ITestResult;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import baseClasses.BaseClass1;
import pomClassses.HomePage;
import pomClassses.LogInPage;
import utilClassess.Util1;

public class VerifyUserCanLogin {
static WebDriver driver;
	
	LogInPage lp;
	HomePage hp;
	ExtentHtmlReporter htmlRepoter;
	ExtentReports reports;
	ExtentTest extentTest;
	
	@BeforeClass
	@Parameters("browser")
	
	public void beforeClass(String browser) throws IOException {
		htmlRepoter=BaseClass1.getExtentHtmlRepoter();
		reports=BaseClass1.getExtentsReports();
		extentTest=BaseClass1.getExtentsTest("VerifyUserCanLogin");
	
		driver = BaseClass1.getDriver(browser);

	}
	
	@BeforeMethod
	public void beforeMethod() {
		lp = new LogInPage(driver);
		hp = new HomePage(driver);
	}
	
	
	@Test
	public void VerifyUserCanLogIn() throws InterruptedException, IOException {
		lp.enterEmailID();
		lp.enterPassword();
		lp.clickOnSubmitBtn();
		
	

	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException{
		
		if(result.getStatus()==ITestResult.SUCCESS) {
		String path	=Util1.getScreenshot(driver, result.getName());
			extentTest.log(Status.PASS, "Test:"+result.getName(),MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}
		
			
		else if(result.getStatus()==ITestResult.FAILURE) {
		
			
			extentTest.log(Status.FAIL, "Test:"+result.getName());
		}
		
			  
			else if(result.getStatus()==ITestResult.SKIP) {
			
			extentTest.log(Status.SKIP, "Test:"+result.getName());
        }
		
		
		
		
		
		
		
		
		
	}
	
	
	@AfterClass
	public void afterClass() {
		reports.flush();
	}

}


