package pages;

import java.nio.file.Paths;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class try_testing_this_page {

	private Page page;
	// DECLARE VARIABLE PATH:
	private static String first_name = "input[name='fname']";
	private static String last_name = "input[name='lname']";
	private static String male_checkBox = "input[id='male']";
	private static String female_checkBox = "input[id='female']";
	private static String dropdown = "select[id='option']";
	private static String user_name = "input[name='uname']";
	private static String password = "input[name='pwd']";
	private static String submit_btn="input[type='submit']";

	public try_testing_this_page(Page page) {
		this.page = page;
	}

	public void input_field() {
		page.fill(first_name, "sneha");
		page.fill(last_name, "mahajan");
	}

	public void checkbox() {
		page.click(male_checkBox);
		//wait is performed that when male_checkbox is clicked then only female_checkbox has to be clicked
		page.waitForFunction("el => el.checked", male_checkBox, new Page.WaitForFunctionOptions().setTimeout(2000));
		page.click(female_checkBox);
	}

	public void dropdown_handle() throws InterruptedException {
		System.out.println("inside dropdown handling function");
		overall_scroll_operation();
		// select option are the html selector which are used to make drodown
		page.locator(dropdown).selectOption("option 3"); // here instead of option 3, variale cannot be used as above
															
	}

	public void select_date() {
		page.locator("//input[@type='date']").fill("2023-12-25");
	}

	public void range() {
		System.out.println("inside selecting range");
		// not a scrollable div actually it is a div
		page.locator("input[type='range']").fill("70");

	}

	public void choose_file() {
		System.out.println("inside choosing file");
		page.setInputFiles("#myfile", Paths.get("C:\\Users\\sneha\\OneDrive\\Pictures\\Screenshots\\abc.png"));
	}

	public void submit_btn() {
		page.click("button[class='btn btn-success']");
	}

	public void alert_btn_handle() {
		// Register handler BEFORE triggering alert
		page.onceDialog(dialog -> {
			System.out.println("Alert message: " + dialog.message());
			dialog.accept(); // clicks OK
			// dialog.dismiss(); // clicks Cancel (only for confirm boxes)
		});

		// Trigger the alert by clicking the button
		page.locator("//button[normalize-space()='Your Sample Alert Button!']").click();
	}
	
	public void doubleClick() {
		scroll_inside_div_below();
		page.waitForTimeout(2000);
		page.dblclick("//button[text()='Double-click me']");
		scroll_inside_div_above();
		page.locator("#demo", new Page.LocatorOptions().setHasText("Your Sample Double Click worked!"))
	    .waitFor(new Locator.WaitForOptions()
	        .setState(WaitForSelectorState.VISIBLE)
	        .setTimeout(5000));
		page.waitForTimeout(4000);
	}
	public void picture_drag_drop() {
		overall_scroll_operation();
		System.out.println("inside drag and drop");
		page.waitForTimeout(5000);

		page.dragAndDrop("#drag1", "#div1"); // source → target

		System.out.println("Drag and drop completed!");

		page.waitForTimeout(2000);
	}

	public void Sample_login() {
		overall_scroll_operation();
		page.waitForTimeout(2000);
		scroll_inside_div_below();
		page.waitForTimeout(2000);
		page.fill(user_name, "test");
		page.fill(password, "test");
		scroll_inside_div_below();
		page.click(submit_btn);
		page.waitForTimeout(2000);
	}

	public void overall_scroll_operation() {
		// to make scroll for 1000 pixels only
		page.evaluate("window.scrollBy(0, 500)");

		// Scroll to bottom of the page
		// page.evaluate("window.scrollTo(0, document.body.scrollHeight)");

		// Scroll to top of the page
		// page.evaluate("window.scrollTo(0, 0)");
	}

	public void scroll_inside_div_below() {
		page.evaluate("document.querySelector('.side.ex1').scrollTop = 500");
	}
	public void scroll_inside_div_above() {
		 page.evaluate("document.querySelector('.side.ex1').scrollTop = 0");
	}
}
