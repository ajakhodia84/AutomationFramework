package publicisSapient.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class TestBase {

	public static WebDriver driver;
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
		driver.manage().window().maximize();
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
	
	public String getScreenshots(String testCaseName) throws IOException{		
		
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
		String path=projectPath+"\\screenshots\\" + testCaseName+" "+timestamp+".png";
		FileUtils.copyFile(srcFile, new File(path));
		return path;
		
	}
	

}
