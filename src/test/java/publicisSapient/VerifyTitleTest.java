package publicisSapient;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import publicisSapient.pageObjects.LandingPage;
import publicisSapient.pageObjects.LoginPage;
import publicisSapient.resources.TestBase;

public class VerifyTitleTest extends TestBase {

	public static Logger log = LogManager.getLogger(HomePageTest.class.getName());

	@BeforeMethod
	public void getUrl() throws IOException {
		driver = null;
		if (getPropertyValue("viewPort").contains("Desktop")) {
			driver = initializeDriver();
			log.info("Desktop Driver is Initialized");
		} else if (getPropertyValue("viewPort").contains("Mobile")) {
			driver = initializeAppiumDriver();
			log.info("Mobile Driver is Initialized");
		}
		driver.get(getPropertyValue("url"));
		// driver.get("www.facebook.com");
	}

	@Test
	public void VerifyTitleHeader() throws IOException {

		LandingPage landingPage = new LandingPage(driver);
		LoginPage loginPage = new LoginPage(driver);
		String expectedTitle = "QA Click Academy | Selenium,Jmeter,SoapUI,Appium,Database testing,QA Training Academy";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
		landingPage.popupClose();
		landingPage.clickLogin();
		loginPage.login("sasdf@gmail.com", "asdf", "waste");
		loginPage.toFail();
	}

	@AfterMethod
	public void closeBrowser() {
		driver.close();
		driver = null;
		System.out.println("Test Exected for given call file:");
	}
}
