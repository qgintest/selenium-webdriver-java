package com.qgintest.webdriver.tests;

import org.testng.annotations.Test;

import com.qgintest.webdriver.pages.MercuryLoginPage;
import com.qgintest.webdriver.utilities.DriverUtil;

import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;


import com.qgintest.testutilities.selenium.SyncUtil;
import com.qgintest.testutilities.selenium.Common;

public class MercuryToursTests {

	static WebDriver driver;
	static RemoteWebDriver remoteDriver = null;
	static DriverUtil browserUtil;

	MercuryLoginPage loginPage;

	@Test(enabled = true)
	public void testLogin() throws Exception{
		
		//DriverUtil.writeBrowserSessionToFile((RemoteWebDriver) driver);
		
		loginPage.setUserName();
		loginPage.setUserPassword();
		loginPage.clickLogin();
		Assert.assertEquals(SyncUtil.pageCheck(driver, true, true, "Find a Flight: Mercury Tours:", "Use Our Flight Finder"), true);
		Assert.assertEquals(SyncUtil.pageCheck(driver, true, false, "Find a Flight: Mercury Tours:"), true);

		loginPage.checkAllLinksAreWorking();
		
		loginPage.selectPassengersFromDropDown("2");
		loginPage.printListOfPassengersFromDropDown();
		
		
	}

	@Test(enabled = false)
	public void testTabsAndWindows() throws Exception{
		loginPage.manageTabsAndWindows();
	}
	
	@Test(enabled = false)
	public void screenShotTest(Method method){
		Common.takeScreenshot(driver, "screenshots" + File.separator + method.getName());
	}
	

	
	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {
	}

	@Parameters({ "Browser-Type", "UsePreviousSession" })
	@BeforeTest
	public void beforeTest(String browser, boolean usePreviousSession) {
		
		
		browserUtil = new DriverUtil(browser, usePreviousSession);
		browserUtil.launchDriver();
		driver = DriverUtil.driver;
//		if(usePreviousSession == true){
//			remoteDriver = DriverUtil.remoteDriver;
//		}else{
			
//		}
		//driver.navigate().to("http://www.google.com");
		driver.navigate().to("http://newtours.demoaut.com/");


		loginPage = new MercuryLoginPage(driver);
	}

	@AfterTest
	public void afterTest() {
		DriverUtil.tearDown();
	}

}
