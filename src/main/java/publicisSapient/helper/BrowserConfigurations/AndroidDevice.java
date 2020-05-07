package publicisSapient.helper.browserConfigurations;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import publicisSapient.helper.ResourceHelper;

public class AndroidDevice {
	
	public AppiumDriver<MobileElement> getAppiumDriver(){
		
		AppiumDriver<MobileElement> driver = null;
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Demo");
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
		cap.setCapability("chromedriverExecutable", ResourceHelper.getResourcePath("Drivers/chromedriver239.exe"));
		cap.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.Chrome);
		try {
			driver = new AppiumDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return driver;
	}

}
