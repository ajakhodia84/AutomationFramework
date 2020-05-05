package publicisSapient.helper.BrowserConfigurations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import publicisSapient.helper.ResourceHelper;

public class ChromeBrowser {
	
	public ChromeOptions getChromeOptions(){
		ChromeOptions chromeOptions=new ChromeOptions();
		chromeOptions.addArguments("--test-type");
		chromeOptions.addArguments("--disable-popup-blocking");
		
		DesiredCapabilities capChrome=DesiredCapabilities.chrome();
		capChrome.setJavascriptEnabled(true);
		
		chromeOptions.setCapability(ChromeOptions.CAPABILITY, capChrome);
		
		//For Linux
		if(System.getProperty("os.name").contains("Linux")){
			chromeOptions.addArguments("--headless", "window-size=1024,768", "--no-sandbox");
		}
		return chromeOptions;
	}
	
	public WebDriver getChromeDriver(ChromeOptions cap){
		
		if (System.getProperty("os.name").contains("Mac")){
			System.setProperty("webdriver.chrome.driver", ResourceHelper.getResourcePath("Drivers/chromedriver"));
			return new ChromeDriver(cap);
		}
		else if(System.getProperty("os.name").contains("Window")){
			System.setProperty("webdriver.chrome.driver", ResourceHelper.getResourcePath("Drivers/chromedriver.exe"));
			return new ChromeDriver(cap);
		}
		else if(System.getProperty("os.name").contains("Linux")){
			System.setProperty("webdriver.chrome.driver", "/usr/bin/chrome");
			return new ChromeDriver(cap);
		}
		return null;
	}

}
