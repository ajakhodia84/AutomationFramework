package publicisSapient.pageObjects;

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

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		new TestBase().getNavigationScreen(driver);
		TestBase.logExtentReportInfo("Login Page Object Created");
	}

	public void login(String emailid, String passwordid, String userType) throws IOException {
		email.sendKeys(emailid);
		password.sendKeys(passwordid);
		submit.click();;
		log.info(userType);
	}

	public void toFail(){
		log.info("Clicking on unavailable element to fail test case");
		toFail.click();;
	}

}
