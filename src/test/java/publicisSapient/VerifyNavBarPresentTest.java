package publicisSapient;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import publicisSapient.pageObjects.LandingPage;
import publicisSapient.pageObjects.LoginPage;
import publicisSapient.resources.testBase;

public class VerifyNavBarPresentTest extends testBase {
	
	public static Logger log=LogManager.getLogger(HomePageTest.class.getName());
	
	@BeforeTest
	public void getUrl() throws IOException{
		driver = initializeDriver();
		log.info("Driver is Initialized");
		driver.get(prop.getProperty("url"));
	}

	@Test
	public void basePageNavigation() throws IOException {

		LandingPage landingPage = new LandingPage(driver);
		LoginPage loginPage = new LoginPage(driver);		
		String expectedTitle = "QA Click Academy | Selenium,Jmeter,SoapUI,Appium,Database testing,QA Training Academy";
		String actualTitle = driver.getTitle();
		assertEquals(actualTitle, expectedTitle);
		landingPage.popupClose();
		landingPage.verifyNavBar();
		System.out.println("VerifynavigationTestPassed");
		log.info("Nav bar verified");
		

	}
}
