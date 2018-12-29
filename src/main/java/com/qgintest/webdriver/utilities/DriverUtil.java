package com.qgintest.webdriver.utilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

import com.qgintest.testutilities.env.EnvUtil;
import com.qgintest.testutilities.file.FileUtil;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverUtil {

	public static WebDriver driver;
	String browserStype = null;
	
	String driverPath = null;
	String driverName = null;
	File file = null;
	

	CommonUtil commonUtil = new CommonUtil();
	EnvUtil envUtil = new EnvUtil();
	static FileUtil fileUtil = new FileUtil();

	public DriverUtil(String browserStype) {
		
		if (!browserStype.contentEquals("chrome") && !browserStype.contentEquals("firefox")) {
			try {
				throw new Exception(
						"Unsupported Browser Type for Framework. Current supported types include: \n\tchrome \tfirefox");
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
		
		driverPath = fileUtil.getPathFromResource("drivers") + File.separator + browserStype + File.separator;
		
		try {
			file = new File(FileUtil.returnFileUsingWildcard(driverPath, "*" + envUtil.getRunTimeOsSimplified() + "*"));
			driverName = file.getName();
			driverPath = "drivers" + File.separator + browserStype + File.separator;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.browserStype = browserStype;
		
		System.out.println("browser to be used: " + browserStype);

	}
	
	public void launchDriver(){
		switch(browserStype){
		case "chrome":
			launchChrome();
			break;
			
		case "firefox":
			launchFireFox();
			break;
			
		case "explorer":
			break;
			
		case "safari":
			break;
			
		case "opera":
			break;	
		}
	}

	public void launchChrome() {
		ChromeOptions options = new ChromeOptions();
		final String initDriver = "webdriver.chrome.driver";
		File file = commonUtil.setDriver(driverPath, driverName, "drivers");
		file.setExecutable(true);

		final String startDriver = file.toString();

		/*
		 * options.addArguments("--headless");
		 * options.addArguments("chrome.switches", "--disable-extensions");
		 * options.setExperimentalOption("useAutomationExtension", false);
		 * options.addArguments("start-maximized");
		 */

		System.setProperty(initDriver, startDriver);

		driver = new ChromeDriver(options);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	}
	
	public void launchFireFox(){
		
		final String initDriver = "webdriver.chrome.driver";
		
		File file = commonUtil.setDriver(driverPath, driverName, "drivers");
		file.setExecutable(true);

		final String startDriver = file.toString();
		
		System.setProperty(initDriver, startDriver);
		//System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");

		driver = new FirefoxDriver();
	}
	
	public static void launchSeleniumGridOrSauceLabsExperimental(){
		
		//run following command to start selenium Hub
		//1: java -jar selenium-server-standalone-3.0.1.jar -role hub
		//2: navigate to http://localhost:4444/grid/console on browser - make sure hub is up
		//3: advanced: create hub/node files and run commands
				//java -jar selenium-server-standalone-3.x.x.jar -role hub -hubConfig selGridHubConfig.json
				//java -Dwebdriver.chrome.driver="/Users/abeinatorair/Documents/workspace/selenium-webdriver-3-gradle/drivers/chromedriver-2.40" -Dwebdriver.gecko.driver="/Users/abeinatorair/Documents/workspace/selenium-webdriver-3-gradle/drivers/geckodriver" -jar selenium-server-standalone-3.13.0.jar -role node -nodeConfig nodeAbeMacConfig.json
		
				//IE -Dwebdriver.ie.driver="IEDriverServer.exe"
				//Safari
				//Chrome
				//Firefox
				
		
		//4. you should get a message saying node is registered on both consoles, make sure the path to the browser driver is correct on the node machine
	  /*  {      
	        "browserName": "internet explorer",
	        "platform": "WINDOWS",
	        "maxInstances": 1,
	        "seleniumProtocol": "WebDriver"
	      },
	      
          {
	      "browserName": "safari",
	      "technologyPreview": false,
	      "platform": "MAC",
	      "maxInstances": 1,
	      "seleniumProtocol": "WebDriver"
	    }*/

		
		String nodeUrl = "http://192.168.1.155:4444/wd/hub";
		
		
		String SAUCE_USER = "";
		String SAUCE_KEY = "";
		
		//String sauceUrl = "http://:" + SAUCE_USER + ":" + SAUCE_KEY + "@ondemand.saucelabs.com:80/wd/hub";
		
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setPlatform(Platform.MAC);
		cap.setBrowserName("chrome");
		
		try {
			//driver = new RemoteWebDriver(new URL(sauceUrl), cap); //for instances of VMs that have seperate nodes
			driver = new RemoteWebDriver(new URL(MessageFormat.format("http://{0}:{1}@ondemand.saucelabs.com:80/wd/hub", SAUCE_USER, SAUCE_KEY)), cap);  //Sauce labs cloud-based testing
			System.out.println("succesfully called remote grid node");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public static void tearDown(){
		if(!(driver == null)){
			driver.quit();
		}
	}
}
