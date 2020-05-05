package publicisSapient.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.gargoylesoftware.htmlunit.BrowserVersion;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class TestBase {
	
	public static Logger log=LogManager.getLogger(TestBase.class);

	public static WebDriver driver = null;
	public static AppiumDriver<MobileElement> appiumDriver = null;
	public String projectPath = System.getProperty("user.dir");

	public WebDriver initializeDriver() throws IOException {

		String browserName = getPropertyValue("browser");
		String viewPortName = getPropertyValue("viewPort");

		if (viewPortName.contains("Desktop")) {
			driver = browserInitiation(browserName);
		} else if (viewPortName.contains("Mobile")) {
			driver = initializeAppiumDriver();
		}

		int timeOutSeconds = Integer.parseInt(getPropertyValue("timeOutSeconds"));
		// driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(timeOutSeconds, TimeUnit.SECONDS);
		return driver;
	}

	public WebDriver initializeAppiumDriver() throws MalformedURLException {

		WebDriver driver = null;
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Demo");
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
		cap.setCapability("chromedriverExecutable", projectPath + "\\Drivers\\chromedriver239.exe");
		cap.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
		driver = new AppiumDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);

		return driver;
	}

	public String getPropertyValue(String arg) throws IOException {
		Properties prop;
		prop = new Properties();
		FileInputStream fis = new FileInputStream(

				projectPath + "\\src\\main\\java\\publicisSapient\\GAFDefaultdata\\GAFdefault.properties");

		prop.load(fis);
		String value = prop.getProperty(arg);
		return value;

	}

	public WebDriver browserInitiation(String browserName) {
		WebDriver driver = null;
		if (browserName.contains("Chrome")) {

			ChromeOptions chromeOptions = new ChromeOptions();
			if (browserName.contains("headless")) {
				chromeOptions.addArguments("--headless");
			}

			System.setProperty("webdriver.chrome.driver", projectPath + "\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver(chromeOptions);
		} else if (browserName.contains("Firefox")) {

			FirefoxOptions fireFoxOptions = new FirefoxOptions();
			if (browserName.contains("headless")) {
				fireFoxOptions.addArguments("--headless");
			}

			System.setProperty("webdriver.gecko.driver", projectPath + "\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver(fireFoxOptions);

		} else if (browserName.contains("IE")) {
			System.setProperty("webdriver.ie.driver", projectPath + "\\Drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();

		}

		return driver;
	}

	public void click(WebElement element) {
		VerificationHelper verificationHelper=new VerificationHelper(driver);
		if(verificationHelper.isDisplayed(element)){
		try {
			element.click();
			log.info(element.toString()+" is displayed and clicked with Click Method");
		} catch (Exception e) {
			JavaScriptHelper javaScriptHelper = new JavaScriptHelper(driver);
			javaScriptHelper.scrollIntoViewAndClick(element);
			log.info(element.toString()+" is displayed and clicked with JavaScriptExecutor Method");
		}
		}else{
			log.info(element.toString()+" is not displayed on page: "+driver.getTitle()+" hence cannot be clicked");
		}
	}

	public String getScreenshotsDesktop(String testCaseName) throws IOException {
		File srcFile = null;
		if (getPropertyValue("viewPort").contains("Desktop")) {
			srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			log.info("Screenshot taken for desktop");
		} else if (getPropertyValue("viewPort").contains("Mobile")) {
			srcFile = getMobileScreenshot();
		}

		String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
		String path = projectPath + "\\screenshots\\" + testCaseName + " " + timestamp + ".png";
		FileUtils.copyFile(srcFile, new File(path));
		return path;

	}

	public File getMobileScreenshot() {
		File srcFile;

		appiumDriver = (AppiumDriver<MobileElement>) driver;
		log.info("Appium driver initialized for Mobile");
		srcFile = appiumDriver.getScreenshotAs(OutputType.FILE);
		// srcFile = appiumDriver.getScreenshotAs(OutputType.FILE);
		log.info("Screenshot taken for Mobile");

		return srcFile;
	}

}
