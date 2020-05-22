package publicisSapient.helper.testBase;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import publicisSapient.helper.ExcelHelper;
import publicisSapient.helper.JavaScriptHelper;
import publicisSapient.helper.ResourceHelper;
import publicisSapient.helper.WaitHelper;
import publicisSapient.helper.browserConfigurations.AndroidDevice;
import publicisSapient.helper.browserConfigurations.BrowserType;
import publicisSapient.helper.browserConfigurations.ChromeBrowser;
import publicisSapient.helper.browserConfigurations.ChromeBrowserheadless;
import publicisSapient.helper.browserConfigurations.FirefoxBrowser;
import publicisSapient.helper.browserConfigurations.FirefoxBrowserheadless;
import publicisSapient.helper.browserConfigurations.IEBrowser;
import publicisSapient.helper.browserConfigurations.config.ObjectReader;
import publicisSapient.helper.browserConfigurations.config.PropertyReader;
import publicisSapient.helper.utils.ExtentReportNG;

public class TestBase {

	public static ExtentReports extent;
	public static ExtentTest test;
	public static WebDriver driver;
	private static Logger log = LogManager.getLogger(TestBase.class);
	public static File reportDirectery;

	@BeforeSuite
	public void beforeSuite() throws Exception {
		extent = ExtentReportNG.getInstance();
	}

	@BeforeTest
	public void beforeTest() throws Exception {
		ObjectReader.reader = new PropertyReader();
		reportDirectery = new File(ResourceHelper.getResourcePath("screenshots"));
		setUpDriver(ObjectReader.reader.getBrowserType());
		test = extent.createTest(getClass().getSimpleName());
	}

	@BeforeMethod
	public void beforemethod(Method method) {
		test.log(Status.INFO, method.getName() + "**************test started***************");
		log.info("**************" + method.getName() + "Started***************");
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getThrowable());
			String imagePath = captureScreen(result.getName(), driver);
			test.addScreenCaptureFromPath(imagePath);
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getName() + " is pass");
			String imagePath = captureScreen(result.getName(), driver);
			test.addScreenCaptureFromPath(imagePath);
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, result.getThrowable());
		}
		log.info("**************" + result.getName() + "Finished***************");
		extent.flush();
	}

	@AfterTest
	public void afterTest() throws Exception {
		if (driver != null) {
			driver.quit();
		}
	}

	public WebDriver getBrowserObject(BrowserType btype) throws Exception {

		try {
			switch (btype) {
			case Chrome:
				// get object of ChromeBrowser class
				ChromeBrowser chrome = ChromeBrowser.class.newInstance();
				ChromeOptions option = chrome.getChromeOptions();
				return chrome.getChromeDriver(option);
			case Chromeheadless:
				// get object of ChromeBrowser class
				ChromeBrowserheadless chromeheadless = ChromeBrowserheadless.class.newInstance();
				ChromeOptions optionheadless = chromeheadless.getChromeOptions();
				return chromeheadless.getChromeDriver(optionheadless);	
			case Firefox:
				FirefoxBrowser firefox = FirefoxBrowser.class.newInstance();
				FirefoxOptions options = firefox.getFirefoxOptions();
				return firefox.getFirefoxDriver(options);
			case Firefoxheadless:
				FirefoxBrowserheadless firefoxheadless = FirefoxBrowserheadless.class.newInstance();
				FirefoxOptions optionsheadless = firefoxheadless.getFirefoxOptions();
				return firefoxheadless.getFirefoxDriver(optionsheadless);	
			case IE:
				IEBrowser ie = IEBrowser.class.newInstance();
				InternetExplorerOptions cap = ie.getIExplorerCapabilities();
				return ie.getIExplorerDriver(cap);
			case AndroidDevice:
				AndroidDevice android=AndroidDevice.class.newInstance();
				return android.getAppiumDriver();
			default:
				throw new Exception("Driver not Found: " + btype.name());
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}

	public void setUpDriver(BrowserType btype) throws Exception {
		driver = getBrowserObject(btype);
		log.info("Initialize Web driver: " + driver.hashCode());
		WaitHelper wait = new WaitHelper(driver);
		wait.setImplicitWait(ObjectReader.reader.getImplicitWait(), TimeUnit.SECONDS);
		wait.pageLoadTime(ObjectReader.reader.getPageLoadTime(), TimeUnit.SECONDS);
		if(!ObjectReader.reader.getBrowserType().toString().contains("Device")){
		//driver.manage().window().maximize();
		}
	}

	public String captureScreen(String fileName, WebDriver driver) {
		if (driver == null) {
			log.info("driver is null..");
			return null;
		}
		if (fileName == "") {
			fileName = "blank";
		}
		Reporter.log("captureScreen method called");
		File destFile = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		File screFile;
		if(ObjectReader.reader.getBrowserType().toString().contains("Device")){
		log.info("Screenshot started for mobile");	
		//AppiumDriver<MobileElement> appiumDriver= (AppiumDriver<MobileElement>) driver;
		//screFile = appiumDriver.getScreenshotAs(OutputType.FILE);
		screFile =new File(ResourceHelper.getResourcePath("screenshots/DummyForMobile.png"));
		log.info("Screenshot taken for mobile");
		}else{		
		screFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		}
		try {
			destFile = new File(reportDirectery + "/" + fileName + "_" + formater.format(calendar.getTime()) + ".png");
			// File.copy(screFile.toPath(), destFile.toPath());
			FileUtils.copyFile(screFile, destFile);
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'><img src='" + destFile.getAbsolutePath()
					+ "'height='100' width='100'/></a>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return destFile.toString();
	}

	public void getNavigationScreen(WebDriver driver) {
		log.info("capturing ui navigation screen for title..."+driver.getTitle());
		logExtentReportInfo("capturing ui navigation screen for title..."+driver.getTitle());
		new JavaScriptHelper(driver).zoomInByGivenPercent(60);
		String screen = captureScreen("", driver);
		new JavaScriptHelper(driver).zoomInByGivenPercent(100);
		try {
			test.addScreenCaptureFromPath(screen);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void logExtentReportInfo(String s1) {
		test.log(Status.INFO, s1);
	}
	
	public static void logExtentReportPass(String s1) {
		test.log(Status.PASS, s1);
	}
	
	public static void logExtentReportFail(String s1) {
		test.log(Status.PASS, s1);
	}
	
	public static void logExtentReportWarn(String s1) {
		test.log(Status.WARNING, s1);
	}

	public static void logExtentReportSkip(String s1) {
		test.log(Status.SKIP, s1);
	}
	
	public static void logExtentReportFatal(String s1) {
		test.log(Status.FATAL, s1);
	}
	
	public void getApplicationUrl(String url) {
		driver.get(url);
		logExtentReportInfo("navigating to ..." + url);
	}

	public Object[][] getExcelData(String excelName, String sheetName) {
		ExcelHelper excelHelper = new ExcelHelper();
		Object[][] data = excelHelper.getExcelData(excelName, sheetName);
		return data;
	}
	
	public void verifyPageTitle(WebDriver driver,String expPageTitle){
		
		String actPageTitle=driver.getTitle();
		if(actPageTitle.equals(expPageTitle)){
			log.info("Page title displayed correctly for page: "+expPageTitle);
			TestBase.logExtentReportPass("Page title displayed correctly for page: "+expPageTitle);
		}else{
			log.info("Page title not displayed correctly for page: "+expPageTitle+". Actual title displayed is: "+actPageTitle);
			TestBase.logExtentReportFail("Page title not displayed correctly for page: "+expPageTitle+". Actual title displayed is: "+actPageTitle);
		}
		
	}
}