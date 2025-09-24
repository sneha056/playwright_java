package pages;

import com.microsoft.playwright.Page;

public class DataAnnotation_page {
    private Page page;

    public DataAnnotation_page(Page page) {
        this.page = page;
    }

    public void login(String email, String password) {
//    	page.fill("#userEmail", email);
//    	page.fill("#userPassword", password);
//    	page.click("#login_btn");
    	page.fill("#Email", email);
    	page.fill("#Password", password);
    	page.click("button[type='submit']");
    	page.waitForTimeout(4000);
    }
    public boolean isLoginSuccessful() {
//        return page.locator("img[class='logo-default']").isVisible();
    	return page.locator("#menuDashboard").isVisible();
    }
}
