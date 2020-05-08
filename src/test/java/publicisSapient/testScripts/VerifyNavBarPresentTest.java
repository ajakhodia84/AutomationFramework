package publicisSapient.testScripts;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import publicisSapient.helper.browserConfigurations.config.ObjectReader;
import publicisSapient.helper.testBase.TestBase;
import publicisSapient.pageObjects.LandingPage;

public class VerifyNavBarPresentTest extends TestBase {

	private static Logger log = LogManager.getLogger(VerifyNavBarPresentTest.class);
	LandingPage landingPage;
	
	@Test
	public void verifyNavBarIsPresent() throws IOException {
		getApplicationUrl(ObjectReader.reader.getUrl());
		landingPage = new LandingPage(driver);
		String expectedTitle = "QA Click Academy | Selenium,Jmeter,SoapUI,Appium,Database testing,QA Training Academy";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
		landingPage.popupClose();
		landingPage.clickMobNavBar(ObjectReader.reader.getBrowserType().toString());
		landingPage.verifyNavBar();
		System.out.println("VerifynavigationTestPassed");
		log.info("Nav bar verified");

	}
}
