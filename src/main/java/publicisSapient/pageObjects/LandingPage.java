package publicisSapient.pageObjects;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import publicisSapient.HomePageTest;
import publicisSapient.resources.TestBase;



public class LandingPage extends TestBase{
	
	public static Logger log=LogManager.getLogger(HomePageTest.class.getName());
	
	public WebDriver driver;
	
	@FindBy (css="a[href*='sign_in']") WebElement loginLink;
	@FindBy (xpath="//div[contains(@class,'close-button')]") WebElement popupClose;
	@FindBy (css=".navbar-nav") WebElement navBar;
	
	public LandingPage(WebDriver driver) {           
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		}
	

	public void clickLogin() throws IOException{
		click(loginLink);
	}

	public void popupClose() throws IOException{
		try{
			click(popupClose);
			log.info("Pop up displayed and closed");
		}catch(Exception e){
			log.info("Pop up not displayed and step skipped");
		}		
	}
	
	public void verifyNavBar(){
		Assert.assertTrue(navBar.isDisplayed());
	}
}
