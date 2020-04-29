package publicisSapient;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import publicisSapient.pageObjects.LandingPage;
import publicisSapient.pageObjects.LoginPage;
import publicisSapient.resources.TestBase;

public class HomePageTest extends TestBase {
	
	public static Logger log=LogManager.getLogger(HomePageTest.class.getName());
	
	@BeforeMethod
	public void getUrl() throws IOException{
		driver = null;
		if (getPropertyValue("viewPort").contains("Desktop")) {
			driver = initializeDriver();
			log.info("Desktop Driver is Initialized");
		} else if (getPropertyValue("viewPort").contains("Mobile")) {
			driver = initializeAppiumDriver();
			log.info("Mobile Driver is Initialized");
		}
		driver.get(getPropertyValue("url"));
	}

	@Test(dataProvider="getData")
	public void verifyLogin(String email, String password, String text) throws IOException {
		
		LandingPage landingPage = new LandingPage(driver);
		LoginPage loginPage = new LoginPage(driver);
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
	public Object[][] getData(){
		Object[][] data = new Object[2][3];
		data[0][0]="nonrestricted@gmail.com";
		data[0][1]="12344";
		data[0][2]="nonrestricted";
		
		data[1][0]="restricted@gmail.com";
		data[1][1]="232312344";
		data[1][2]="restricted";
		return data;
	}
	
	@AfterMethod
	public void closeBrowser()
	{
		driver.close();
		driver=null;
	}

}
