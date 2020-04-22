package publicisSapient.resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class testBase {

	public WebDriver driver;
	public String projectPath=System.getProperty("user.dir");
	public Properties prop;
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	

	public WebDriver initializeDriver() throws IOException {

		prop = new Properties();
		FileInputStream fis = new FileInputStream(

				projectPath
						+ "\\src\\main\\java\\publicisSapient\\GAFDefaultdata\\GAFdefault.properties");

		prop.load(fis);
		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\geckodriver.exe");
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", projectPath + "\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();

		}

		int timeOutSeconds = Integer.parseInt(prop.getProperty("timeOutSeconds"));
		driver.manage().timeouts().implicitlyWait(timeOutSeconds, TimeUnit.SECONDS);
		return driver;
	}
	
	public void click(WebElement element){
		try{
			element.click();
		}catch(Exception e){
			executor.executeScript("arguments[0].click();", element);
		}
	}

}
