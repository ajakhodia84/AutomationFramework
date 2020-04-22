package publicisSapient.pageObjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import publicisSapient.resources.testBase;



public class LandingPage extends testBase{
	
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
		click(popupClose);
	}
	
	public void verifyNavBar(){
		Assert.assertTrue(navBar.isDisplayed());
	}
}
