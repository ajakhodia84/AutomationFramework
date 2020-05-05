package publicisSapient.testScripts;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import publicisSapient.helper.TestBase;
import publicisSapient.pageObjects.LandingPage;
import publicisSapient.pageObjects.LoginPage;

public class VerifyTitleTest extends TestBase {

	public static Logger log = LogManager.getLogger(VerifyTitleTest.class);

	@BeforeTest
	public void getUrl() throws IOException {
		driver=null;
		driver = initializeDriver();
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

	@AfterTest
	public void closeBrowser() {		
		log.info("Verification completed for class: "+VerifyTitleTest.class.getName());
		driver.close();
		driver=null;
	}
}
