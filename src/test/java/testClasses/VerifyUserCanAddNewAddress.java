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
import java.util.Arrays;
import java.util.List;

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
import pomClassses.ProfilePage;
import utilClassess.Util1;

public class VerifyUserCanAddNewAddress {
static WebDriver driver;
	
	LogInPage lp;
	HomePage hp;
	ProfilePage pp;
	ExtentHtmlReporter htmlRepoter;
	ExtentReports reports;
	ExtentTest extentTest;
	String oldAddressCount;
	String newAddressCount;
	
	@BeforeClass
	@Parameters("browser")
	public void beforeClass(String browser) throws IOException {
		htmlRepoter=BaseClass1.getExtentHtmlRepoter();
		reports=BaseClass1.getExtentsReports();
		extentTest=BaseClass1.getExtentsTest("VerifyUserCanAddNewAddress");
		driver = BaseClass1.getDriver(browser);
	}
	
	@BeforeMethod
	public void beforeMethod() {
		lp = new LogInPage(driver);
		hp = new HomePage(driver);
		pp = new ProfilePage(driver);
	}
	
	
	@Test(priority = 4)
	public void verifyUserCanOpenProfilePage() {
		//hover on profileName
		hp.hoverProfileName();
		//click on my profile
		hp.clickOnMyProfile();
		//check page is opened
		boolean onPage = pp.checkUserOnProfilePage();
		AssertJUnit.assertTrue(onPage);	
		oldAddressCount = String.valueOf(pp.getAddressCount());
	}
	
	@DataProvider(name="addressData")
	public Object[][] getData() {
		Object[][] k = {{"Sohel", "8956235623","413512","Nanded Road", "B-22, A pune"}, {"Sneha","7845124512", "411023", "Warje", "A-32, B shivaji nagar, near english school"}};
		return k;
	}
	
	@Test(priority = 5, dataProvider="addressData")
	public void addNewAddress(String name, String phone, String pincode, String locality, String fullAddress) {
		pp.clickOnManageAddress();
		List<String> addressDetails = Arrays.asList(name, phone, pincode, locality, fullAddress);
		pp.addNewAddress(addressDetails);
		newAddressCount = String.valueOf(pp.getAddressCount());
		boolean isCountMatching = newAddressCount.equals(oldAddressCount);
		AssertJUnit.assertFalse(isCountMatching);	
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
		BaseClass1.unloadDriver();
		
	}

}
