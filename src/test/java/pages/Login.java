package pages;


import com.microsoft.playwright.Page;



public class Login {
	
	private Page page;
	private static String username_path="input[name='username']";
	private static String password_path="input[name='password']";
	private static String SubmitBbutton_path="button[type='submit']";
	
    public Login(Page page) {
    	this.page=page;
    }
   
    
    public void login_credentials(String uername, String password) throws InterruptedException {
        System.out.println("the page title is: " + page.title());
       page.fill(username_path, uername);
       page.fill(password_path, password);
       page.click(SubmitBbutton_path);
       
       // handle cookie popup
       if (page.isVisible("button:has-text('Ok')")) {
           page.click("button:has-text('Ok')");
       }
        
        // Or take a screenshot to verify
        //page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshot.png")));
    }
   
}