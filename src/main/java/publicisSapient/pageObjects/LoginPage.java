package publicisSapient.pageObjects;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import publicisSapient.helper.WaitHelper;
import publicisSapient.helper.testBase.TestBase;

public class LoginPage {

	private static Logger log = LogManager.getLogger(LandingPage.class);

	private WebDriver driver;
	WaitHelper waitHelper;

	@FindBy(css = "[id='user_email']")
	private WebElement email;
	@FindBy(css = "[id='user_password']")
	private WebElement password;
	@FindBy(css = "[type='submit']")
	private WebElement submit;
	@FindBy(css = "asdfs")
	private WebElement toFail;
	@FindBy (css=".alert-danger")
	private WebElement alertMessage;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		new TestBase().getNavigationScreen(driver);
		TestBase.logExtentReportInfo("Login Page Object Created");
	}

	public void login(String emailid, String passwordid, String userType) throws IOException {
		email.sendKeys(emailid);
		TestBase.logExtentReportInfo("Email Id passed is: "+emailid);
		password.sendKeys(passwordid);
		TestBase.logExtentReportInfo("passwordid Id passed is: "+passwordid);
		submit.click();;
		log.info(userType);
		TestBase.logExtentReportInfo("userType Id passed is: "+userType);
		if(userType.equalsIgnoreCase("pass")){
			assertTrue(true);
			TestBase.test.pass("Assert is passed");
			TestBase.logExtentReportPass("Assert is passed");
		}else{
			assertTrue(false);
			TestBase.test.fail("Assert is passed");
			TestBase.logExtentReportFail("Assert is passed");
		}
		
		if(alertMessage.isDisplayed()){
			TestBase.test.pass("Test Case is passed. Allert si displayed");
			TestBase.logExtentReportPass("Test Case is passed. Allert si displayed");
		}else{
			TestBase.test.fail("Test failed as not alert is displayed with name: "+alertMessage.toString());
			TestBase.logExtentReportFail("Test failed as not alert is displayed with name: "+alertMessage.toString());
		}
	}

	public void toFail(){
		log.info("Clicking on unavailable element to fail test case");		
		toFail.click();;
	}

}
