package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	static ExtentReports extentReport;
	
	public static ExtentReports getReportObject() {
		
			//ExtentRerpots & ExtentSparkReports - These 2 class needs to be created for that we need to map the object.
			//EntentReport can be created in the same directory - "user.dir" gets to current project path.
			String path = System.getProperty("user.dir")+"\\Reports\\reports.html";
			ExtentSparkReporter reporter = new ExtentSparkReporter(path);
			//Object (reporter) is responsible for all configurations.
			reporter.config().setReportName("Automation Results");
			reporter.config().setDocumentTitle("Extent Report Test Results");
			
			extentReport = new ExtentReports();
			extentReport.attachReporter(reporter);
			extentReport.setSystemInfo("Tester", "Karthick Hariharan");
			return extentReport;
	}
	
}
