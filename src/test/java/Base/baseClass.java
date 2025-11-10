package Base;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import com.microsoft.playwright.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class BaseClass {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;
    protected String browserName;
    protected String className;

    // LambdaTest credentials
    String user = "snehamahajan056";
    String accessKey = "LT_XzwbGU64LcDUDnx8usmN43MaixDJBGOWJQFs7f6BTegHAmi";

    @Parameters("browserName")
    @BeforeClass
    public void setup(String browserName) throws UnsupportedEncodingException {
        this.browserName = browserName;
        this.className = this.getClass().getSimpleName();

        System.out.println("Launching on LambdaTest Cloud: " + browserName + " (class: " + className + ")");
        playwright = Playwright.create();

        try {
            // Define capabilities
            JsonObject capabilities = new JsonObject();
            JsonObject ltOptions = new JsonObject();

            
            // Chrome = "Chrome", Firefox = "pw-firefox" 
            String ltBrowserName;
            String ltBrowserVersion = "latest";
            
            switch (browserName.toLowerCase()) {
                case "firefox":
                    ltBrowserName = "pw-firefox";  // Playwright bundled Firefox
                    break;
                case "chrome":
                case "chromium":
                default:
                    ltBrowserName = "Chrome";  // Real Chrome browser (faster than pw-chromium)
                    break;
            }

            capabilities.addProperty("browserName", ltBrowserName);
            capabilities.addProperty("browserVersion", ltBrowserVersion);

            // Platform and test configuration
            ltOptions.addProperty("platform", "Windows 10");
            ltOptions.addProperty("build", "Playwright-Java-LambdaTest");
            ltOptions.addProperty("name", className + "_" + browserName);
            ltOptions.addProperty("project", "LambdaTest Assignment");
            ltOptions.addProperty("user", user);
            ltOptions.addProperty("accessKey", accessKey);
            
            // Enable features
            ltOptions.addProperty("video", true);
            ltOptions.addProperty("network", true);
            ltOptions.addProperty("console", true);
            ltOptions.addProperty("visual", true);
            
            // Performance optimization
            ltOptions.addProperty("resolution", "1920x1080");
            ltOptions.addProperty("idleTimeout", 300);  // 5 minutes max idle time

            capabilities.add("LT:Options", ltOptions);

            // Convert to JSON and URL encode
            Gson gson = new Gson();
            String capsJson = gson.toJson(capabilities);
            String encodedCaps = URLEncoder.encode(capsJson, StandardCharsets.UTF_8.toString());

            // Create CDP URL without credentials
            String cdpUrl = "wss://cdp.lambdatest.com/playwright?capabilities=" + encodedCaps;

            System.out.println("🔗 Connecting to LambdaTest with " + ltBrowserName + "...");

            // ALWAYS use chromium.connect() for LambdaTest (even for Firefox)
            // This is because LambdaTest uses CDP which requires Chromium driver
            browser = playwright.chromium().connect(cdpUrl, new BrowserType.ConnectOptions()
                    .setTimeout(90000));  // 90 second connection timeout

            // Create context with proper timeouts
            context = browser.newContext(new Browser.NewContextOptions()
                    .setViewportSize(1920, 1080));
            
            // Set reasonable timeouts
            context.setDefaultTimeout(30000);  // 30 seconds for actions
            context.setDefaultNavigationTimeout(60000);  // 60 seconds for page loads
            
            page = context.newPage();
            
            // Navigate with explicit timeout
            page.navigate("https://www.lambdatest.com/selenium-playground/", 
                new Page.NavigateOptions().setTimeout(60000));

            System.out.println("Connected to LambdaTest successfully on " + ltBrowserName);

        } catch (Exception e) {
            System.err.println("Error connecting to LambdaTest: " + e.getMessage());
            e.printStackTrace();
            
            // Clean up on failure
            cleanup();
            throw new RuntimeException("Failed to connect to LambdaTest", e);
        }
    }

    @AfterClass
    public void teardown() {
        try {
            // Take final screenshot
            if (page != null && !page.isClosed()) {
                String screenshotPath = "screenshots/" + className + "_" + browserName + "_"
                        + System.currentTimeMillis() + ".png";
                page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath)));
                System.out.println("Screenshot saved: " + screenshotPath);
            }
        } catch (Exception e) {
            System.err.println("Failed to take screenshot: " + e.getMessage());
        } finally {
            cleanup();
        }
    }

    private void cleanup() {
        try {
            if (context != null) {
                context.close();
                System.out.println(" Context closed");
            }
        } catch (Exception e) {
            System.err.println("Error closing context: " + e.getMessage());
        }

        try {
            if (browser != null) {
                browser.close();
                System.out.println("Browser closed");
            }
        } catch (Exception e) {
            System.err.println("Error closing browser: " + e.getMessage());
        }

        try {
            if (playwright != null) {
                playwright.close();
                System.out.println("Playwright closed");
            }
        } catch (Exception e) {
            System.err.println("Error closing playwright: " + e.getMessage());
        }

        System.out.println("Teardown complete.");
    }
}
