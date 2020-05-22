package publicisSapient.testScripts;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;


import publicisSapient.helper.browserConfigurations.config.ObjectReader;
import publicisSapient.helper.testBase.TestBase;
import publicisSapient.pageObjects.LandingPage;
import publicisSapient.pageObjects.LoginPage;

public class VerifyTitleTest extends TestBase {

	private static Logger log = LogManager.getLogger(VerifyTitleTest.class);
	LandingPage landingPage;
	LoginPage loginPage;
	
	@Test
	public void VerifyTitleHeader() throws IOException {
		test.assignAuthor("Anupam Jakhodia new");
		test.assignCategory("Smoke test device test");
		getApplicationUrl(ObjectReader.reader.getUrl());
		landingPage = new LandingPage(driver);
		loginPage = new LoginPage(driver);
		String expectedTitle = "QA Click Academy | Selenium,Jmeter,SoapUI,Appium,Database testing,QA Training Academy";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
		landingPage.popupClose();
		landingPage.clickLogin();
		loginPage.login("sasdf@gmail.com", "asdf", "waste");
		loginPage.toFail();
	}

}
