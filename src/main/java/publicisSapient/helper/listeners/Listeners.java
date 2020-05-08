package publicisSapient.helper.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class Listeners implements ITestListener {

	private static Logger log = LogManager.getLogger(Listeners.class);
	public static ExtentReports extent;
	public static ExtentTest test;

	public void onTestStart(ITestResult testname) {
		// test.log(Status.INFO, arg0.getName()+" started..");
		Reporter.log(testname.getMethod().getMethodName() + " Test Started..");
		log.info(testname.getMethod().getMethodName() + " Test Started..");
	}

	public void onTestSuccess(ITestResult testName) {
		// test.log(Status.INFO, arg0.getName()+" Passed..");
		Reporter.log(testName.getMethod().getMethodName() + " Test Passed..");
		log.info(testName.getMethod().getMethodName() + " Test Passed..");
	}

	public void onTestFailure(ITestResult testName) {
		// test.log(Status.FAIL, arg0.getThrowable());
		Reporter.log(testName.getMethod().getMethodName() + " Test Failed.." + testName.getThrowable());
		log.error(testName.getMethod().getMethodName() + " Test Failed.." + testName.getThrowable());

	}

	public void onTestSkipped(ITestResult testName) {
		// test.log(Status.SKIP, arg0.getThrowable());
		Reporter.log(testName.getMethod().getMethodName() + " Test Skipped.." + testName.getThrowable());
		log.warn(testName.getMethod().getMethodName() + " Test Skipped.." + testName.getThrowable());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult testName) {
		// TODO Auto-generated method stub
	}

	public void onStart(ITestContext context) {
		// extent = ExtentManager.getInstance();
		// test = extent.createTest(arg0.getName());
		// test = extent.createTest(arg0.getCurrentXmlTest().getName());
		Reporter.log(context.getCurrentXmlTest().getName() + " Class Started..");
		log.info(context.getCurrentXmlTest().getName() + " Class Started..");
	}

	public void onFinish(ITestContext context) {
		// extent.flush();
		Reporter.log(context.getName() + " Test Finished..");
		log.info(context.getName() + " Test Finished..");

	}

}
