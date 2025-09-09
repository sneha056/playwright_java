 package utlis;

 /* to create reports if they are not created yet using extent report */
 
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	public static ExtentReports extent;
	public static ExtentReports getInstance() {
		if(extent ==null) {
			ExtentSparkReporter reporter = new ExtentSparkReporter("test-output/ExtentReport.html");
			extent= new ExtentReports();
			extent.attachReporter(reporter);
		}
		return extent;
	}
}
