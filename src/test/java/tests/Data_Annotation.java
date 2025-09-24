package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.baseClass;
import pages.DataAnnotation_page;

public class Data_Annotation extends baseClass { 

    @DataProvider(name = "loginTest")
    public Object[][] getData() {
        return new Object[][] {
            {"manish.singh@lng-consultancy.com", "Abcd1234#"}
            //{"manpreet.singh@lng-consultancy.com", "India@1234"}
        };
    }

    @Test(dataProvider = "loginTest")
    public void testLogin(String username, String password) {
    	//page.navigate("https://my.trade245.com/login");
    	page.navigate("https://qa-lngperforms.azurewebsites.net/Identity/Account/Login?ReturnUrl=%2FGoals%2FManageUserGoalsForAdmin");
    	DataAnnotation_page loginPage = new DataAnnotation_page(page);
        loginPage.login(username, password);
       
        
        //Assertion
        Assert.assertTrue(loginPage.isLoginSuccessful(),"Login failed for user: " + username);
    }
}
