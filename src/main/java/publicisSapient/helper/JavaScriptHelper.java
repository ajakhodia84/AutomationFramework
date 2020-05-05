package publicisSapient.helper;

import java.util.concurrent.Executor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptHelper {

	private WebDriver driver;
	public static Logger log = LogManager.getLogger(JavaScriptHelper.class);

	public JavaScriptHelper(WebDriver driver) {
		this.driver = driver;
		log.info("JavaScriptHelper has been initialized");
	}

	/**
	 * JavaScriptExecutor function for executing with single argument
	 * @param script
	 * @return
	 */
	public Object executeScript(String script) {

		JavascriptExecutor exe = (JavascriptExecutor) driver;
		return exe.executeScript(script);
	}

	/**
	 * JavascriptExecutor function for executing with multiple argument
	 * @param script
	 * @param args
	 * @return
	 */
	public Object executeScript(String script, Object... args) {

		JavascriptExecutor exe = (JavascriptExecutor) driver;
		return exe.executeScript(script, args);
	}

	/**
	 * JavascriptExecutor function for scrolling to element
	 * @param element
	 */
	public void scrollToElement(WebElement element) {

		log.info("Scroll to WebElement: " + element.toString());
		executeScript("window.scrollTo(arguments[0], arguments[1])", element.getLocation().x, element.getLocation().y);
	}

	/**
	 * JavascriptExecutor function for scrolling to element and click
	 * @param element
	 */
	public void scrollToElementAndClick(WebElement element) {
		scrollToElement(element);
		executeScript("arguments[0].click()", element);
		log.info(element.toString() + " element is clicked");
	}

	/**
	 * JavascriptExecutor function for scrolling into view
	 * @param element
	 */
	public void scrollIntoView(WebElement element) {		
		executeScript("arguments[0].scrollIntoView()", element);
		log.info(element.toString() + " is visible after scrolling");
	}

	/**
	 * JavascriptExecutor function for scrolling into view and click
	 * @param element
	 */
	public void scrollIntoViewAndClick(WebElement element) {
		scrollIntoView(element);
		executeScript("arguments[0].click()", element);
		log.info(element.toString() + " element is clicked");
	}

	/**
	 * JavascriptExecutor function for scrolling down vertically till page height
	 */
	public void scrollDownVertically(){
		executeScript("window.scrollTo(0, document.body.scrollHeight)");
		log.info("Scrolled down vertifally till end");
	}
	
	/**
	 * JavascriptExecutor function for scrolling up vertifally till page height
	 */
	public void scrollUpVertically(){
		executeScript("window.scrollTo(0, -document.body.scrollHeight)");
		log.info("Scrolled up vertifally till end");
	}
	
	/**
	 * JavascriptExecutor function for scrolling down by given pixel height
	 * @param pixel
	 */
	public void scrollDownByPixel(int pixel){
		executeScript("window.scrollBy(0,"+pixel+")");
		log.info("Scrolled down by pixel: "+pixel);
	}
	
	/**
	 * JavascriptExecutor function for scrolling up by given pixel height
	 * @param pixel
	 */
	public void scrollUpByPixel(int pixel){
		executeScript("window.scrollBy(0,-"+pixel+")");
		log.info("Scrolled up by pixel: "+pixel);
	}
	
	/**
	 * JavascriptExecutor function for zooming in by given percent
	 * @param percent
	 */
	public void zoomInByGivenPercent(int percent){
		executeScript("document.body.style.zoom='"+percent+"%'");
	}
}
