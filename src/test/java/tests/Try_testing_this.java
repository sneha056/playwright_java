package tests;

import org.testng.annotations.Test;

import Base.baseClass;
import pages.try_testing_this_page;


public class Try_testing_this extends baseClass{
	@Test
	public void testing_elements() throws InterruptedException {
		page.navigate("https://trytestingthis.netlify.app/");
		try_testing_this_page testing = new try_testing_this_page(page);
		//testing.input_field();8888
		//testing.checkbox();
//		testing.dropdown_handle();
//		testing.select_date();
//		testing.range();
//		testing.choose_file();
//		testing.submit_btn();
		//testing.alert_btn_handle();
		//testing.picture_drag_drop();
		testing.Sample_login();
	}
}
