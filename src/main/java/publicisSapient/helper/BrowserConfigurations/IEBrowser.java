package publicisSapient.helper.BrowserConfigurations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.ElementScrollBehavior;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import publicisSapient.helper.ResourceHelper;

public class IEBrowser {
	
	public InternetExplorerOptions getIExplorerCapabilities() {
		
		DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
		
		cap.setCapability(InternetExplorerDriver.ELEMENT_SCROLL_BEHAVIOR,ElementScrollBehavior.BOTTOM);
		cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		cap.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		cap.setJavascriptEnabled(true);
		
		InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions(cap);
		
		return internetExplorerOptions;
	}
	
	public WebDriver getIExplorerDriver(InternetExplorerOptions cap) {
		if (System.getProperty("os.name").contains("Mac")) {
			System.setProperty("webdriver.ie.driver",ResourceHelper.getResourcePath("Drivers/IEDriverServer"));
			return new InternetExplorerDriver(cap);
		} else if (System.getProperty("os.name").contains("Window")) {
			System.setProperty("webdriver.ie.driver", ResourceHelper.getResourcePath("Drivers/IEDriverServer.exe"));
			return new InternetExplorerDriver(cap);
		} else if (System.getProperty("os.name").contains("Linux")) {
			System.setProperty("webdriver.ie.driver", "/usr/bin/IEDriverServer");
			return new InternetExplorerDriver(cap);
		}
		return null;
	}

}
