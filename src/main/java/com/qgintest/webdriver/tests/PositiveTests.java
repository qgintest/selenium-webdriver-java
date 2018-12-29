package com.qgintest.webdriver.tests;

import org.testng.annotations.Test;

import com.qgintest.webdriver.pages.MercuryLoginPage;
import com.qgintest.webdriver.utilities.DriverUtil;

import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;

public class PositiveTests {
	
	 static WebDriver driver;

	 MercuryLoginPage loginPage;
	
  
  @Test
  public void testLogin() {
	  loginPage.setUserName();
	  loginPage.setUserPassword();
	  loginPage.clickLogin();
  }
  @BeforeMethod
  public void beforeMethod() {
  }

  @AfterMethod
  public void afterMethod() {
  }

  @Parameters({ "Browser-Type"})
  @BeforeTest
  public void beforeTest(String browser) {
	  DriverUtil browserUtil = new DriverUtil(browser);
	  browserUtil.launchDriver();
	  
	  driver = DriverUtil.driver;
	  driver.navigate().to("http://newtours.demoaut.com/");
	  
	  loginPage = new MercuryLoginPage(driver);
  }

  @AfterTest
  public void afterTest() {
	  DriverUtil.tearDown();
  }

}
