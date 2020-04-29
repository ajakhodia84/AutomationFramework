package publicisSapient.listeners;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import publicisSapient.HomePageTest;
import publicisSapient.resources.ExtentReportNG;
import publicisSapient.resources.TestBase;

public class listeners implements ITestListener {

	ExtentReports extent = ExtentReportNG.extentReportGenerator();
	ExtentTest test;
	private static ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<ExtentTest>();

	public static Logger log = LogManager.getLogger(HomePageTest.class.getName());
	TestBase testBase = new TestBase();

	public void onTestStart(ITestResult testname) {
		test = extent.createTest(testname.getMethod().getMethodName());
		threadLocal.set(test);
	}

	public void onTestSuccess(ITestResult testName) {
		threadLocal.get().log(Status.PASS, "Successfully validated: " + testName.getMethod().getMethodName());

	}

	public void onTestFailure(ITestResult testName) {

		String path;
		/*
		 * WebDriver driver = null; System.out.println("wedriver is null");
		 */
		threadLocal.get().fail(testName.getThrowable());
		/*
		 * System.out.println("thread local"); Object testMethodObject =
		 * testName.getInstance();
		 * System.out.println(testName.getInstance().toString()); Class clazz =
		 * testName.getTestClass().getRealClass();
		 * System.out.println(testName.getTestClass().getRealClass().toString())
		 * ; try { driver = (WebDriver)
		 * clazz.getDeclaredField("driver").get(testMethodObject);
		 * System.out.println("driver is initialized"); } catch (Exception e1) {
		 * System.out.println("driver is not initialized1");
		 * log.error("driver is not initialized"); }
		 */

		threadLocal.get().log(Status.FATAL,
				"Test Case with Name: " + testName.getMethod().getMethodName() + " is failed with FATAL status");
		try {
			if (testBase.getPropertyValue("viewPort").contains("Desktop")) {
				path = testBase.getScreenshotsDesktop(testName.getName());
				threadLocal.get().addScreenCaptureFromPath(path, testName.getMethod().getMethodName());
			} else if (testBase.getPropertyValue("viewPort").contains("Mobile")) {
				log.info("Framework is not able to capture screenshot for mobile");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("Application failed to take screenshot");
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult testName) {
		String path;
		/* WebDriver driver = null; */
		threadLocal.get().fail(testName.getThrowable());
		/*
		 * Object testMethodObject = testName.getInstance(); Class clazz =
		 * testName.getTestClass().getRealClass();
		 * 
		 * try { driver = (WebDriver)
		 * clazz.getDeclaredField("driver").get(testMethodObject); } catch
		 * (Exception e1) {
		 * 
		 * }
		 */
		threadLocal.get().log(Status.SKIP,
				"Test case with name: " + testName.getMethod().getMethodName() + " is skipped");
		try {
			if (testBase.getPropertyValue("viewPort").contains("Desktop")) {
				path = testBase.getScreenshotsDesktop(testName.getName());
				threadLocal.get().addScreenCaptureFromPath(path, testName.getMethod().getMethodName());
			} else if (testBase.getPropertyValue("viewPort").contains("Mobile")) {
				log.info("Framework is not able to capture screenshot for mobile");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("Application failed to take screenshot");
			e.printStackTrace();
		}
		testBase.driver.close();

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult testName) {
		threadLocal.get().fail(testName.getThrowable());
		String path=null;
		/*
		 * WebDriver driver = null;
		 * threadLocal.get().fail(testName.getThrowable()); Object
		 * testMethodObject = testName.getInstance(); Class clazz =
		 * testName.getTestClass().getRealClass();
		 * 
		 * try { driver = (WebDriver)
		 * clazz.getDeclaredField("driver").get(testMethodObject); } catch
		 * (Exception e1) {
		 * 
		 * }
		 */
		threadLocal.get().log(Status.FAIL, "Test case with name: " + testName.getMethod().getMethodName()
				+ " is failed within success percentage");
		try {
			if (testBase.getPropertyValue("viewPort").contains("Desktop")) {
				path = testBase.getScreenshotsDesktop(testName.getName());
				
			} else if (testBase.getPropertyValue("viewPort").contains("Mobile")) {
				path = testBase.getScreenshotsMobile(testName.getName());
				
				log.info("Framework is not able to capture screenshot for mobile");
			}
			threadLocal.get().addScreenCaptureFromPath(path, testName.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("Application failed to take screenshot");
			e.printStackTrace();
		}

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		extent.flush();

	}

}
