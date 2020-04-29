package publicisSapient.pageObjects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

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
	
	@FindBy (css="a[href*='sign_in']") private WebElement loginLink;
	@FindBy (xpath="//div[contains(@class,'close-button')]") private WebElement popupClose;
	@FindBy (css=".navbar-nav") private WebElement navBar;
	@FindBy (css="button[class='navbar-toggle']") private WebElement navButtonMobile;
	
	
	public LandingPage(WebDriver driver) {           
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		}
	

	public void clickLogin() throws IOException{
		String webElementName=loginLink.getText();
		click(loginLink);
		System.out.println(webElementName+" option is clicked");
	}

	public void popupClose() throws IOException{
		try{
			click(popupClose);
			log.info("Pop up displayed and closed");
		}catch(Exception e){
			log.info("Pop up not displayed and step skipped");
		}		
	}
	
	public void verifyNavBar() throws IOException{
		System.out.println(getPropertyValue("viewPort"));
		if(getPropertyValue("viewPort").contains("Mobile")){
			click(navButtonMobile);
		}
		Assert.assertTrue(navBar.isDisplayed());
	}
}
