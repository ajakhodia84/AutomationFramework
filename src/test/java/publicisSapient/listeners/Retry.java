package publicisSapient.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	private int retryCount = 0;
	private int maxRetryCount = 2;

	public static Logger log = LogManager.getLogger(Retry.class);

	public boolean retry(ITestResult testName) {
		if (retryCount < maxRetryCount) {
			log.info("Retrying test " + testName.getName() + " with status " + resultStatusName(testName.getStatus()) + " for the " + (retryCount + 1)
					+ " times");
			return true;
		}

		return false;
	}

	public String resultStatusName(int status){
		String resultName=null;
		
		if(status==1){
			resultName="SUCCESS";
		}
		
		if(status==2){
			resultName="FAILURE";
		}
		
		if(status==3){
			resultName="SKIP";
		}
		
		return resultName;
	}
}
