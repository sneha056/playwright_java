package tests;

import org.testng.annotations.Test;


import pages.directory;
import Base.baseClass;
import pages.Login;

public class Orange_website extends baseClass {
	@Test(priority = 1)
    public void home_page() throws InterruptedException  {
    	
    	page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    	
    	//object creation
    	Login login =new Login(page);
    	
    	login.login_credentials("Admin", "admin123");
    	directory direct = new directory(page);
    	direct.directory_form();
    }
    
//	@Test(priority = 2, dependsOnMethods = {"home_page"})
//    public void directory_page() {
//    	directory direct = new directory(page);
//    	direct.directory_form();
//    }
}