package com.qgintest.webdriver.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.qgintest.testutilities.selenium.SyncUtil;

public class MercuryLoginPage {
	
	WebDriverWait wait;
	public WebDriver driver;
	
	@FindBy(name="userName") WebElement userNameField;
	
	@FindBy(name="password") WebElement passwordField;
	
	@FindBy(name="login") WebElement signInButton;
	
	//without page factory, below would be used
    //By fieldUserName = By.name(glblfieldUserName);
    //By fieldUserPass = By.name(glblfieldUserPass);
    //By buttonLogin = By.id(glblbuttonLogin);
	
	public MercuryLoginPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setUserName(){
		SyncUtil.waitForElementToBeVisible(driver, wait, userNameField, 10);
		userNameField.sendKeys("qgintest");
		
	}
	
	public void setUserPassword(){
		SyncUtil.waitForElementToBeVisible(driver, wait, passwordField, 10);
		passwordField.sendKeys("Test@123");
	}
	
	public void clickLogin(){
		SyncUtil.waitForElementToBeClickable(driver, wait, passwordField, 10);
		signInButton.click();
	}

}
