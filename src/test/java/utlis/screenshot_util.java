package utlis;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.microsoft.playwright.Page;

public class screenshot_util {
	
//when function fails only then take ss 
	public static String takeScreenshot(Page page, String testName) {

		//timestamp variable is created because if we donot specify this then screenshot is overridden with previous one and with timestamp according to time new ss is added
		String timestamp= new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String path ="test-output/screenshots/"+testName+"_"+timestamp+".png";
		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
		return path;
	}
}
