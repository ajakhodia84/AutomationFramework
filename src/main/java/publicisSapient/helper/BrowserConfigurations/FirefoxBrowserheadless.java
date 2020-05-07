package publicisSapient.helper.browserConfigurations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import publicisSapient.helper.ResourceHelper;

public class FirefoxBrowserheadless {
	
	public FirefoxOptions getFirefoxOptions(){
		
		DesiredCapabilities ffCap=DesiredCapabilities.firefox();
		
		FirefoxProfile profile=new FirefoxProfile();
		profile.setAcceptUntrustedCertificates(true);
		profile.setAssumeUntrustedCertificateIssuer(true);
		
		ffCap.setCapability(FirefoxDriver.PROFILE, profile);
		ffCap.setCapability("marionette", true);
		
		FirefoxOptions firefoxOptions = new FirefoxOptions(ffCap);	
		firefoxOptions.addArguments("--headless", "window-size=1024,768", "--no-sandbox");

		return firefoxOptions;		
	}
	
	public WebDriver getFirefoxDriver(FirefoxOptions cap) {

		if (System.getProperty("os.name").contains("Mac")) {
			System.setProperty("webdriver.gecko.driver",ResourceHelper.getResourcePath("Drivers/geckodriver"));
			return new FirefoxDriver(cap);
		} else if (System.getProperty("os.name").contains("Window")) {
			System.setProperty("webdriver.gecko.driver",ResourceHelper.getResourcePath("Drivers/geckodriver.exe"));
			return new FirefoxDriver(cap);
		} else if (System.getProperty("os.name").contains("Linux")) {
			System.setProperty("webdriver.gecko.driver", "/usr/bin/geckodriver");
			return new FirefoxDriver(cap);
		}
		return null;
	}

}
