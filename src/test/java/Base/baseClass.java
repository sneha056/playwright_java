package Base;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import utlis.ExtentManager;
import utlis.screenshot_util;

public class baseClass {
	protected Playwright playwright;
	protected Browser browser;
	protected Page page;
	
	protected ExtentReports extent;
	protected ExtentTest test;
	
	@BeforeMethod
	public void setup(Method method) {
		
		//Reporting
		extent =ExtentManager.getInstance();
		test= extent.createTest(method.getName()); //This creates a new test entry inside the report for each test method that is running.
		
		//playwright setup
		System.out.println("launching browser");
		playwright =Playwright.create();
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000)); //setHeadless(false)=> Playwright will open a visible browser window so you can see what’s happening.
		page=browser.newPage();
	}
	@AfterMethod
	//ITestResult is a TestNG interface that stores the result of a test run.
	public void teardown(ITestResult result) {
		
		//Reporting logic
		if(result.getStatus()==ITestResult.FAILURE) {
			test.fail(result.getThrowable());
			
			//to take screenshot  and also below screenshot_util is the name of utils class that we have created
			String screenshotPath=screenshot_util.takeScreenshot(page, result.getName());
			test.addScreenCaptureFromPath(screenshotPath);
			
		}else if(result.getStatus()==ITestResult.SUCCESS) {
			test.pass("Passssss");
		}else {
			test.skip("test skipped");
		}
		extent.flush();
		
		//Browser cleanup
		if(browser!=null) {
			browser.close();
		}
		if(playwright!=null) {
			playwright.close();
		}
	}
}
