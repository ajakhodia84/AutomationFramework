package publicisSapient.pageObjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import publicisSapient.resources.TestBase;

public class LoginPage extends TestBase{
	
	public WebDriver driver;
	
	@FindBy (css="[id='user_email']") private WebElement email;
	@FindBy (css="[id='user_password']") private WebElement password;
	@FindBy (css="[type='submit']") private WebElement submit;
	
	public LoginPage(WebDriver driver) {           
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		}
	

	public void login(String emailid, String passwordid, String userType ) throws IOException{
		email.sendKeys(emailid);
		password.sendKeys(passwordid);
		click(submit);
		System.out.println(userType);
	}

}
