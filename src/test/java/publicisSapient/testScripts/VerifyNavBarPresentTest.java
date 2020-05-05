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

public class VerifyNavBarPresentTest extends TestBase {

	public static Logger log = LogManager.getLogger(VerifyNavBarPresentTest.class);

	@BeforeTest
	public void getUrl() throws IOException {
		driver=null;
		driver = initializeDriver();
		driver.get(getPropertyValue("url"));
	}

	@Test
	public void verifyNavBarIsPresent() throws IOException {

		LandingPage landingPage = new LandingPage(driver);
		String expectedTitle = "QA Click Academy | Selenium,Jmeter,SoapUI,Appium,Database testing,QA Training Academy";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
		landingPage.popupClose();
		landingPage.verifyNavBar();
		System.out.println("VerifynavigationTestPassed");
		log.info("Nav bar verified");

	}

	@AfterTest
	public void closeBrowser() {
		log.info("Verification completed for class: "+VerifyNavBarPresentTest.class.getName());		
		driver.close();
		driver=null;
	}
}
