package publicisSapient.helper.browserConfigurations.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import publicisSapient.helper.ResourceHelper;
import publicisSapient.helper.browserConfigurations.BrowserType;

public class PropertyReader implements ConfigReader {

	private static FileInputStream file;
	public static Properties OR;

	public PropertyReader() {
		try {
			String filePath = ResourceHelper
					.getResourcePath("src/main/java/publicisSapient/GAFDefaultdata/GAFdefault.properties");
			file = new FileInputStream(new File(filePath));
			OR = new Properties();
			OR.load(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getImplicitWait() {
		return Integer.parseInt(OR.getProperty("implicitwait"));
	}

	public int getExplicitWait() {
		return Integer.parseInt(OR.getProperty("explicitwait"));
	}

	public int getPageLoadTime() {
		return Integer.parseInt(OR.getProperty("pageloadtime"));
	}

	public BrowserType getBrowserType() {
		return BrowserType.valueOf(OR.getProperty("browserType"));
	}

	public String getUrl() {
		if (System.getProperty("url") != null) {
			return System.getProperty("url");
		}
		return OR.getProperty("applicationUrlurl");
	}

	public String getUserName() {
		if (System.getProperty("userName") != null) {
			return System.getProperty("userName");
		}
		return OR.getProperty("userName");
	}

	public String getPassword() {
		if (System.getProperty("password") != null) {
			return System.getProperty("password");
		}
		return OR.getProperty("password");
	}

}
