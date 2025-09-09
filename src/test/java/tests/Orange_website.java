package tests;
import org.testng.SkipException;
import org.testng.annotations.Test;



import Base.baseClass;
import pages.Login;

public class Orange_website extends baseClass {
    @Test
    public void home_page() throws InterruptedException  {
    	test.info("navigating to the page");
    	page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    	Login login =new Login(page);
    	
    	login.login_credentials("Admin", "admin123");
    	test.info("successful login");
    	test.info("test completed");
    	page.click("Recruitment");
    	test.info("navigating to Recruitment page ");
    
    }
    @Test
    public void home_page2() {
    	test.info("skipping this test case");
    	throw new SkipException("skipping this test case");
    }
    @Test
    public void home_page3() throws InterruptedException {
    	test.info("navigating to the page");
    	page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    	Login login =new Login(page);
    	
    	login.login_credentials("Admin", "admin123");
    	test.info("successful login");
    	test.info("test completed");
    }
}