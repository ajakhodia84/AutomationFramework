package publicisSapient.testScripts;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import publicisSapient.helper.browserConfigurations.config.ObjectReader;
import publicisSapient.helper.testBase.TestBase;
import publicisSapient.pageObjects.LandingPage;
import publicisSapient.pageObjects.LoginPage;

public class HomePageTest extends TestBase {

	private static Logger log = LogManager.getLogger(HomePageTest.class);
	LandingPage landingPage;
	LoginPage loginPage;
	
	@Test(dataProvider = "getData")
	public void verifyLogin(String email, String password, String text) throws IOException {
		getApplicationUrl(ObjectReader.reader.getUrl());
		landingPage = new LandingPage(driver);
		loginPage = new LoginPage(driver);
		log.info("Navigated to homepage");
		String expectedTitle = "QA Click Academy | Selenium,Jmeter,SoapUI,Appium,Database testing,QA Training Academy";
		String actualTitle = driver.getTitle();
		assertEquals(actualTitle, expectedTitle);
		landingPage.popupClose();
		landingPage.clickLogin();
		loginPage.login(email, password, text);
		log.info("Credentials are passed");

	}

	@DataProvider
	public Object[][] getData() {
		Object[][] data = new Object[2][3];
		data[0][0] = "nonrestricted@gmail.com";
		data[0][1] = "12344";
		data[0][2] = "nonrestricted";

		data[1][0] = "restricted@gmail.com";
		data[1][1] = "232312344";
		data[1][2] = "restricted";
		return data;
	}

}
