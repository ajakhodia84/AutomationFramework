package publicisSapient.pageObjects;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import publicisSapient.helper.WaitHelper;
import publicisSapient.helper.testBase.TestBase;

public class NavigationMenu {
	
	private static Logger log=LogManager.getLogger(LandingPage.class);
	
	private WebDriver driver;
	WaitHelper waitHelper;
	
	@FindBy(css=".navbar") private WebElement navBar;
	@FindBy(css="button.cc_navbar_toggle") private WebElement mobileNavBarToggle;
	@FindBy(css="ul.cc_navbar-nav li.dropdown") private List<WebElement> navBarOptions;
	@FindBy(css="ul.cc_dropdown-menu li") private List<WebElement> subNavBarOptions;
	
	public NavigationMenu(WebDriver driver) {           
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		new TestBase().getNavigationScreen(driver);
		TestBase.logExtentReportInfo("Navigation Bar Object Created");		
		}

	public void clickNavBarOptions(String navBarOptionName){
		for(WebElement menuItems:navBarOptions){
			
			if(menuItems.getText().contains(navBarOptionName)){
				menuItems.click();
				log.info("Navigation option '"+navBarOptionName +"' clicked");
				TestBase.logExtentReportInfo("Navigation option '"+navBarOptionName +"' clicked");
			}else{
				log.info("Navigation option :"+navBarOptionName+" is not available");
				TestBase.logExtentReportFail("Navigation option :"+navBarOptionName+" is not available");
			}
			
		}
	}
	
	public void clickSubNavBarOptions(String subNavBarOptionName){
		for(WebElement menuItems:subNavBarOptions){
			
			if(menuItems.getText().contains(subNavBarOptionName)){
				menuItems.click();
				log.info("Sub-Menu option '"+subNavBarOptionName +"' clicked");
				TestBase.logExtentReportInfo("Sub-Menu option '"+subNavBarOptionName +"' clicked");
			}else{
				log.info("Sub-Navigation option :"+subNavBarOptionName+" is not available");
				TestBase.logExtentReportFail("Sub-Navigation option :"+subNavBarOptionName+" is not available");
			}
			
		}
	}
}
