package com.qgintest.webdriver.pages;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.qgintest.testutilities.selenium.SyncUtil;

public class MercuryLoginPage {

	WebDriverWait wait;
	public WebDriver driver;

	Select oSelect;

	@FindBy(name = "userName")
	WebElement userNameField;

	@FindBy(name = "password")
	WebElement passwordField;

	@FindBy(name = "login")
	WebElement signInButton;

	@FindBy(xpath = "//select[@name='passCount']")
	WebElement passDropDown;

	// without page factory, below would be used
	// By fieldUserName = By.name(glblfieldUserName);
	// By fieldUserPass = By.name(glblfieldUserPass);
	// By buttonLogin = By.id(glblbuttonLogin);

	public MercuryLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void setUserName() {
		SyncUtil.waitForElementToBeVisible(driver, wait, userNameField, 10);
		userNameField.sendKeys("qgintest");

	}

	public void setUserPassword() {
		SyncUtil.waitForElementToBeVisible(driver, wait, passwordField, 10);
		passwordField.sendKeys("Test@123");
	}

	public void clickLogin() {
		SyncUtil.waitForElementToBeClickable(driver, wait, passwordField, 10);
		signInButton.click();
	}

	public void selectPassengersFromDropDown(String pass) {
		SyncUtil.waitForElementToBeClickable(driver, wait, passDropDown, 10);
		oSelect = new Select(passDropDown);
		oSelect.selectByValue(pass);
	}

	public void printListOfPassengersFromDropDown() {
		oSelect = new Select(passDropDown);

		List<WebElement> passengers = oSelect.getOptions();

		for (WebElement passenger : passengers) {
			System.out.println("Passenger: " + passenger.getText());
		}
	}

	public void checkAllLinksAreWorking() throws Exception {
				
		List<WebElement> linksize = driver.findElements(By.tagName("a"));
		
		Actions actions = new Actions(driver);
		
		System.out.println("Total links on page: " + linksize.size());
		
		for(WebElement link: linksize){
			System.out.println("verifying link: " + link.getText());
			
			//if needed, navigate to each link to ensure links are not broken
//			driver.navigate().to(link.getAttribute("href"));
//			Thread.sleep(3000);
//			driver.navigate().back();
//			Thread.sleep(3000);
			
			//Clicking on links in new tab then closing tab
//			actions.keyDown(Keys.COMMAND)
//			.click(link)
//			.keyUp(Keys.COMMAND)
//			.build()
//			.perform();
			
//			Thread.sleep(3000);
//			driver.navigate().back();
//			Thread.sleep(3000);
			
		}
	}

	public void manageTabsAndWindows() throws Exception{
		
		//will create new tab
		//driver.findElement(By.cssSelector("body")).sendKeys(Keys.COMMAND +"t"); //works on MAC
		//driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t"); //works on Windows
		
		//open 5 tabs 
		for(int i = 0; i <= 4; i++){
			((JavascriptExecutor) driver).executeScript("window.open();");
		}

		//close all tabs open including parent tab
		for (String winHandle : driver.getWindowHandles()) {
		    driver.switchTo().window(winHandle);
		    System.out.println("Found handle: " + winHandle);
		    driver.close();
		}

		
	}
}
