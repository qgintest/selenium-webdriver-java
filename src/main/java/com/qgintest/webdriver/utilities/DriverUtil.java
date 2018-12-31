package com.qgintest.webdriver.utilities;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.qgintest.testutilities.date.DateUtil;
import com.qgintest.testutilities.env.EnvUtil;
import com.qgintest.testutilities.file.FileUtil;
import com.qgintest.webdriver.app.TestRunner;
import com.qgintest.testutilities.io.WriteUtil;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import com.qgintest.testutilities.io.ReadUtil;

public class DriverUtil {

	public static WebDriver driver = null;
	public static RemoteWebDriver remoteDriver = null;
	String browserType = null;
	public static URL sessionURL = null;
	public static SessionId sessionID = null;
	static boolean sessionFlag = false;
	static RemoteWebDriver previousDriver = null;
	boolean usePreviousSessionFlag = false;
	
	static Properties prop = null;
	
	public static String glblDriverPath = null;
	
	static String driverPath = null;
	String driverName = null;
	File file = null;
	static File configFile = null;
	

	ThrowAwayUtil commonUtil = new ThrowAwayUtil();
	EnvUtil envUtil = new EnvUtil();
	static FileUtil fileUtil = new FileUtil();

	public DriverUtil(String browserType, boolean usePreviousSessionFlag) {
		
		//configFile = new File(fileUtil.getWorkingDir() + File.separator + "config" + File.separator + "config.properties");
		
//		try {
//			fileUtil.createFileWithParentFolders(fileUtil.getWorkingDir() + File.separator + "config", "config.properties");
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
		
		if (!browserType.contentEquals("chrome") && !browserType.contentEquals("firefox")) {
			try {
				throw new Exception(
						"Unsupported Browser Type for Framework. Current supported types include: \n\tchrome \tfirefox");
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
		
		driverPath = fileUtil.getPathFromResource("drivers") + File.separator + browserType + File.separator;
		
		
		try {
			file = new File(FileUtil.returnFileUsingWildcard(driverPath, "*" + envUtil.getRunTimeOsSimplified() + "*"));
			driverName = file.getName();
			driverPath = "drivers" + File.separator + browserType + File.separator;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.browserType = browserType;
		System.out.println("browser to be used: " + browserType);
		
		//this.usePreviousSessionFlag = usePreviousSessionFlag;
		//System.out.println("Previous session to be used? " + usePreviousSessionFlag);

	}
	
	public void launchDriver(){
		switch(browserType){
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
		@SuppressWarnings("unused")
		ChromeOptions options = new ChromeOptions();
		final String initDriver = "webdriver.chrome.driver";
		File file = setDriver(driverPath, driverName, "drivers");
		file.setExecutable(true);

		final String startDriver = file.toString();

		/*
		 * options.addArguments("--headless");
		 * options.addArguments("chrome.switches", "--disable-extensions");
		 * options.setExperimentalOption("useAutomationExtension", false);
		 * options.addArguments("start-maximized");
		 */

		System.setProperty(initDriver, startDriver);
		driver = new ChromeDriver();
		//ChromeDriver driver = new ChromeDriver(options);
		
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		
		//DriverUtil.driver = driver;
	}
	
	public void launchFireFox(){
		
		final String initDriver = "webdriver.chrome.driver";
		
		File file = setDriver(driverPath, driverName, "drivers");
		file.setExecutable(true);

		final String startDriver = file.toString();
		
		System.setProperty(initDriver, startDriver);

		driver = new FirefoxDriver();
	}
	
	public static void tearDown(){
		if(!(driver == null)){
			driver.quit();
		}
	}
	
	public File setDriver(String filepath, String filename, String outputDir) {

		File file = new File(fileUtil.getWorkingDir() + File.separator + outputDir + File.separator + filename + "_"
				+ DateUtil.returnTimestamp("yyyyMMdd.HHmmss"));

		try {
			try (InputStream in = TestRunner.class.getResourceAsStream("/" + filepath + filename)) {
				FileUtils.copyInputStreamToFile(in, file);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return file;
	}
	
	public static RemoteWebDriver useExistingBrowserSessionExperimental(){
		
		try{
			prop = ReadUtil.loadPropertiesFile(configFile.toString());
			sessionURL = new URL(prop.getProperty("sessionURL"));
		}catch(Exception  e){
			e.printStackTrace();
		}
		
		return null;
		//return ThrowAwayUtil.createDriverFromSession(prop.getProperty("sessionId"), sessionURL);

	}
	
	public static void writeBrowserSessionToFileExperimental(RemoteWebDriver driver){
		
		//Get sessionID and URL
		HttpCommandExecutor executor = (HttpCommandExecutor) driver.getCommandExecutor();
		sessionURL = executor.getAddressOfRemoteServer();
		sessionID = ((RemoteWebDriver) driver).getSessionId();
		
		WriteUtil.writeToFile(configFile.toString(), "sessionId=" + sessionID + "\nsessionURL=" + sessionURL + "\nexpectedPage=null");	
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

		
		@SuppressWarnings("unused")
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
}
