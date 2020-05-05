package publicisSapient.helper;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import org.apache.logging.log4j.Logger;

public class VerificationHelper {
	
	private WebDriver driver;
	public static Logger log=LogManager.getLogger(VerificationHelper.class);
	
	public VerificationHelper(WebDriver driver){
		this.driver=driver;
	}

	public boolean isDisplayed(WebElement element){
		try{
			element.isDisplayed();
			log.info(element.getText()+" element is Displayed.."+element.getText());			
			return true;
		}
		catch(Exception e){
			log.error(element.toString()+" element is not Displayed..", e.getCause());			
			return false;
		}
	}
	
	public boolean isNotDisplayed(WebElement element){
		try{
			element.isDisplayed();
			log.info(element.getText()+" element is present.."+element.getText());			
			return false;
		}
		catch(Exception e){
			log.error(element.toString()+" element is not present..");
			return true;
		}
	}
	
	public String readValueFromElement(WebElement element){
		if(null == element){
			log.info(element.toString()+" WebElement is null..");
			return null;
		}
		boolean status = isDisplayed(element);
		if(status){
			log.info(element.toString()+" element text is .."+element.getText());
			return element.getText();
		}
		else{
			return null;
		}
	}
	public String getText(WebElement element){
		if(null == element){
			log.info(element.toString()+" WebElement is null..");
			return null;
		}
		boolean status = isDisplayed(element);
		if(status){
			log.info(element.toString()+" element text is .."+element.getText());
			return element.getText();
		}
		else{
			return null;
		}
	}
	
	public String getTitle(){
		log.info("page title is: "+driver.getTitle());
		return driver.getTitle();
	}
}
