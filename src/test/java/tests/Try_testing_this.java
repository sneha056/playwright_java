package tests;

import org.testng.annotations.Test;


import Base.baseClass;
import pages.try_testing_this_page;


public class Try_testing_this extends baseClass{
	@Test
	public void testing_elements() throws InterruptedException {
		//wait for 3 sec to load page rather than test case is failed
		//page.navigate("https://trytestingthis.netlify.app/", new Page.NavigateOptions().setTimeout(3000));
		System.out.println();
		try_testing_this_page testing = new try_testing_this_page(page);
		//testing.input_field();8888
		//testing.checkbox();
//		testing.dropdown_handle();
//		testing.select_date();
//		testing.range();
//		testing.choose_file();
//		testing.submit_btn();
		//testing.alert_btn_handle();
		//testing.doubleClick();
		testing.download_pdf();
		//testing.take_screenshot();
		//testing.picture_drag_drop();
		//testing.Sample_login();
	}
}
