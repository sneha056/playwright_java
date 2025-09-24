 package utlis;

 /* to create reports if they are not created yet using extent report */

//The main reporting object that manages the test logs and results.
import com.aventstack.extentreports.ExtentReports;

//Helps in generating a nice HTML report (in your case, at "test-output/ExtentReport.html").
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	public static ExtentReports extent; //static variable holding a single instance of ExtentReports
	public static ExtentReports getInstance() {
		if(extent ==null) { //Checks if the report object is not already created
			ExtentSparkReporter reporter = new ExtentSparkReporter("test-output/ExtentReport.html");
			extent= new ExtentReports();
			extent.attachReporter(reporter); //Attaches the reporter so that results will be logged into the HTML file.
		}
		return extent; //If the report already exists, just return the existing one.If not, create it, attach reporter, and return it.

	}
}
