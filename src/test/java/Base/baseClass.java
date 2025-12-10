package Base;

import java.lang.reflect.Method;
import java.nio.file.Paths;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.*;

import utlis.ExtentManager;
import utlis.screenshot_util;

public class baseClass {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeMethod
    public void setup(Method method) {

        // Reporting
        extent = ExtentManager.getInstance();
        test = extent.createTest(method.getName());

        // Playwright Setup
        System.out.println("launching browser");
        playwright = Playwright.create();

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000));

        // IMPORTANT: Create context with video enabled
        context = browser.newContext(new Browser.NewContextOptions()
                .setRecordVideoDir(Paths.get("videos/"))
                .setRecordVideoSize(1280, 720));

        // Page inside context (NOW video records)
        page = context.newPage();
    }

    @AfterMethod
    public void teardown(ITestResult result) {

        // Reporting logic
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());

            String screenshotPath = screenshot_util.takeScreenshot(page, result.getName());
            test.addScreenCaptureFromPath(screenshotPath);

        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Passssss");
        } else {
            test.skip("test skipped");
        }

        extent.flush();

        // IMPORTANT: Close context first to save video
        if (context != null) {
            context.close();   // <-- This finalizes the video files
        }

        if (browser != null) {
            browser.close();
        }

        if (playwright != null) {
            playwright.close();
        }
    }
}
