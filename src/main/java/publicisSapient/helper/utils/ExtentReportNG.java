package publicisSapient.helper.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import publicisSapient.helper.ResourceHelper;

public class ExtentReportNG {

	public static ExtentReports extentReports;

	public static ExtentReports getInstance() {
		if (extentReports == null) {
			return extentReportGenerator();
		} else {
			return extentReports;
		}
	}

	public static ExtentReports extentReportGenerator() {

		String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
		// String
		// path=System.getProperty("user.dir")+"\\extentReports\\extentReport-"+timestamp+".html";
		String path = ResourceHelper.getResourcePath("extentReports/extentReport-" + timestamp + ".html");
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(path);
		extentSparkReporter.config().setReportName("Gore Automation Web Automation Reports");
		extentSparkReporter.config().setDocumentTitle("Gore Automation Test Results");

		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
		extentReports.setSystemInfo("Tester", "Anupam Jakhodia");
		return extentReports;
	}

}
