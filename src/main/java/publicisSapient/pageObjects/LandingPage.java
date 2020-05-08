package publicisSapient.pageObjects;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import publicisSapient.helper.WaitHelper;
import publicisSapient.helper.testBase.TestBase;



public class LandingPage{
	
	private static Logger log=LogManager.getLogger(LandingPage.class);
	
	private WebDriver driver;
	WaitHelper waitHelper;
	
	@FindBy (css="a[href*='sign_in']") private WebElement loginLink;
	@FindBy (xpath="//div[contains(@class,'close-button')]") private WebElement popupClose;
	@FindBy (css=".navbar-nav") private WebElement navBar;
	@FindBy (css="button[class='navbar-toggle']") private WebElement navButtonMobile;
	
	
	public LandingPage(WebDriver driver) {           
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		new TestBase().getNavigationScreen(driver);
		TestBase.logExtentReportInfo("Landing Page Object Created");		
		}
	

	public void clickLogin() throws IOException{
		String webElementName=loginLink.getText();
		loginLink.click();;
		log.info(webElementName+" option is clicked");
	}

	public void popupClose() throws IOException{
		try{
			popupClose.click();;
			log.info("Pop up displayed and closed");
		}catch(Exception e){
			log.info("Pop up not displayed and step skipped");
		}		
	}
	
	public void clickMobNavBar(String browserType){
		if(browserType.contains("Device")){
			navButtonMobile.click();
		}
	}
	
	public void verifyNavBar() throws IOException{

		Assert.assertTrue(navBar.isDisplayed());
	}
}
