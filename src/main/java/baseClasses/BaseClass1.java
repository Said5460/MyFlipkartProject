package baseClasses;


	import java.io.IOException;
	import java.time.Duration;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
	

	public class BaseClass1 {
		
		static WebDriver driver;
        static ExtentHtmlReporter htmlRepoter;
		static ExtentReports reports;
		static ExtentTest extentTest;
		
		
		public static WebDriver getDriver(String browser) throws IOException {
			
			if(driver == null) {
				
				if(browser.equals("chrome")) {
					
					WebDriverManager.chromedriver().setup();
					
				//	System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\Browsers\\chromedriver.exe");
					driver = new ChromeDriver();
				}else {
					
					WebDriverManager.firefoxdriver().setup();
					
				//	System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\Browsers\\geckodriver.exe");
					
					driver = new FirefoxDriver();
				}
			
				
//				if(Util1.getConfigData("env").equals("qa")) {
//					driver.get("https://www.flipkartqa.com/");
//				}else if(Util1.getConfigData("env").equals("dev")) {
//					driver.get("https://www.flipkartdev.com/");
//				}
				
				driver.get("https://www.flipkart.com/");
				
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
						
			}	
			return driver;
		}
		
		public static void unloadDriver() {
			driver = null;
		}

		
		
		
		public static ExtentHtmlReporter getExtentHtmlRepoter() {
				
		if(htmlRepoter==null) {
			htmlRepoter=new ExtentHtmlReporter("ExtendsReports\\reports.html"); 
		}
		 return 	htmlRepoter;
			
		}
		
		
		
		public static ExtentReports getExtentsReports() {
			
		if(reports==null) {
			reports= new ExtentReports();
			reports.attachReporter(htmlRepoter);
			}
		return reports;
		}
		
		
		public static ExtentTest getExtentsTest(String name) {
			
			extentTest =reports.createTest(name);
		 return extentTest;
	
		
		
		}
		
		
		public static ExtentTest getAlreadyExistingTest() {
			 return extentTest;
			
			
		}
		
		
		
		
		
		
		
		
	}


