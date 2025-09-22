package Base;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;



public class baseClass {
	protected Playwright playwright;
	protected Browser browser;
	protected Page page;
	
	@BeforeClass
	public void setup() {
		
		//playwright setup
		System.out.println("launching browser");
		playwright =Playwright.create();
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000)); //setHeadless(false)=> Playwright will open a visible browser window so you can see what’s happening.
		page=browser.newPage();
	}
	
	@AfterClass
	public void teardown() {
		
		//Browser cleanup
		if(browser!=null) {
			browser.close();
		}
		if(playwright!=null) {
			playwright.close();
		}
	}
}
