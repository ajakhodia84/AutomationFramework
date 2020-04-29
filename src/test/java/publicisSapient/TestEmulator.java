package publicisSapient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class TestEmulator {	
	
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		WebDriver driver;
		String projectPath = System.getProperty("user.dir");
		DesiredCapabilities cap = new DesiredCapabilities();
		Properties prop= new Properties();
		FileInputStream fis = new FileInputStream(

				projectPath + "\\src\\main\\java\\publicisSapient\\GAFDefaultdata\\GAFdefault.properties");

		prop.load(fis);

		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Demo");
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
		cap.setCapability("chromedriverExecutable", projectPath + "\\chromedriver239.exe");
		cap.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);			
		driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		driver.get(prop.getProperty("url"));
		driver.quit();
	}

}
