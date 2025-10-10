package jenkins_playwright;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class GetBrowser {

    @Parameters("Browser") // This tells TestNG to take parameter from testng.xml
    @Test
    public void jenkins(String browserName) {

        System.out.println("Parameter value is: " + browserName);
        Playwright playwright = Playwright.create();
        Browser browser = null;

        // Launch browser based on the parameter
        if (browserName.equalsIgnoreCase("Chrome")) {
            browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000)
            );
        } 
        else if (browserName.equalsIgnoreCase("Firefox")) {
            browser = playwright.firefox().launch(
                new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000)
            );
        } 
        else if (browserName.equalsIgnoreCase("Webkit")) {
            browser = playwright.webkit().launch(
                new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000)
            );
        } 
        else {
            System.out.println("Invalid browser name. Please pass Chrome, Firefox, or Webkit.");
            playwright.close();
            return; // stop execution
        }

        // Open page
        System.out.println("Launching browser...");
        Page page = browser.newPage();
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        System.out.println("The page title is: " + page.title());

        // Close resources
        browser.close();
        playwright.close();
    }
}
