package pages;

import com.microsoft.playwright.Page;

public class directory {
    private Page page;
    private static String Employee_Name = "input[placeholder='Type for hints...']";
    private static String Job_title = "div[class='oxd-select-text oxd-select-text--active']";

    public directory(Page page) {
        this.page = page;
    }

    public void directory_form() {
        // Navigate to directory page
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/directory/viewDirectory");

        // Fill Employee Name
        page.fill(Employee_Name, "Aman");

        // Click Job Title dropdown
        page.locator(Job_title).selectOption("Account Assistant");//doesn't work because selectOption is not used as html 
    }
}
