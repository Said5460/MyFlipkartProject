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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import baseClasses.BaseClass1;
import pomClassses.HomePage;
import pomClassses.LogInPage;
import utilClassess.Util1;

public class VerifyUserCanGetLowestPriceProduct {
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
		extentTest=BaseClass1.getExtentsTest("VerifyUserCanGetLowestPriceProduct");
		driver = BaseClass1.getDriver(browser);
	}
	
	@BeforeMethod
	public void beforeMethod() {
		lp = new LogInPage(driver);
		hp = new HomePage(driver);
	}
	
	
	@DataProvider(name="testData")
	public String[] getData() {
		String[] pName = {"realme", "apple"};
		return pName;
	}
	

	@Test(priority = 2)
	public void VerifyUserCanSearchProduct() throws InterruptedException {
		hp.searchProduct();
		hp = new HomePage(driver);
	}
	
	@Test(priority = 3)
	public void VerifyUserCanGetLowestPriceProductFromEachPage() {

		List<String> lowsetPriceFron5Page = new ArrayList<>();
		
		for(int i=1; i<=5; i++) {
			if(i != 1) {
				hp.switchToPage(i);
			}
		lowsetPriceFron5Page.add(hp.getLowestPriceOfProduct());
			
		}
		System.out.println(lowsetPriceFron5Page);
	}
	
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
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
