package com.qgintest.webdriver.tests;

import org.testng.annotations.Test;

import com.qgintest.webdriver.utilities.DriverUtil;

import org.testng.annotations.BeforeMethod;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;

public class PracticeSiteTests {

	static WebDriver driver;

	@Test
	public void DragAndDropWebElementUsingXYcoordinates() throws Exception {

		driver.findElement(By.linkText("Draggable")).click();
		Thread.sleep(6000);

		driver.findElement(By.linkText("Signin")).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("qgintest");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Test@123");

		driver.findElement(By.linkText("SUBMIT")).click();

		Actions actions = new Actions(driver);
		WebElement elementToDrag = driver.findElement(By.id("draggable"));

		// drag and drop a webelement vertically by 100 pixels.
		actions.dragAndDropBy(elementToDrag, 0, 100).build().perform();
		Thread.sleep(1000);

		// drag and drop a webelement horizontally by 100 pixels.
		actions.dragAndDropBy(elementToDrag, 100, 0).build().perform();
		Thread.sleep(1000);

		// To drag and drop element by -100 pixel offset In horizontal and -100
		// pixel offset In Vertical direction.
		actions.dragAndDropBy(elementToDrag, -100, -100).build().perform();
		Thread.sleep(1000);
	}

	@Test
	public void ResizeElementAndGetNewWidthAndLength() throws Throwable {

		driver.findElement(By.linkText("Resizable")).click();
		Thread.sleep(3000);

		Actions actions = new Actions(driver);
		WebElement resizeableElement = driver.findElement(By.xpath("//*[@id='resizable']/div[3]"));

		// Get width of element.
		int ImageWidth = resizeableElement.getSize().getWidth();
		System.out.println("Image width Is " + ImageWidth + " pixels");

		// Get height of element.
		int ImageHeight = resizeableElement.getSize().getHeight();
		System.out.println("Image height Is " + ImageHeight + " pixels");

		// Used points class to get x and y coordinates of element.
		Point point = resizeableElement.getLocation();
		int xcord = point.getX();
		System.out.println("Element's Position from left side Is " + xcord + " pixels.");
		int ycord = point.getY();
		System.out.println("Element's Position from top side Is " + ycord + " pixels.");

		// drag and drop a webelement horizontally by 100 pixels.
		actions.clickAndHold(resizeableElement).moveByOffset(500, 200).release().build().perform();
		Thread.sleep(1000);

		// Used to get new points class to get x and y coordinates of element.
		point = resizeableElement.getLocation();
		xcord = point.getX();
		System.out.println("Element's NEW Position from left side Is " + xcord + " pixels.");
		ycord = point.getY();
		System.out.println("Element's NEW Position from top side Is " + ycord + " pixels.");

		System.out.println("Ending test " + new Object() {
		}.getClass().getEnclosingMethod().getName());
	}

	@Test
	public void SelectMultipleElementsUsingMouseAndKeyboardControlOperations() throws Exception {

		driver.findElement(By.linkText("Draggable")).click();
		Thread.sleep(3000);

		driver.findElement(By.linkText("Selectable")).click();
		Thread.sleep(3000);

		List<WebElement> list = driver.findElements(By.cssSelector("ol#selectable *"));
		Actions actions = new Actions(driver);

		actions.keyDown(Keys.CONTROL).click(list.get(0)).click(list.get(4)).keyUp(Keys.CONTROL).build().perform();
	}

	@Test
	 public void ReorderElementsOnAList() throws Exception{
		 
		driver.findElement(By.linkText("Draggable")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.linkText("Selectable")).click();
		Thread.sleep(3000);	
		
		WebElement item1 = driver.findElement(By.xpath("//ul[@id='sortable']"));
		WebElement item2 = driver.findElement(By.xpath("//ul[@id='sortable']/li[2]/span"));
		
		Actions actions = new Actions(driver);
		
		//reorder item 1 to item 2
		actions.dragAndDrop(item1, item2);		 
	 }

	 @Test
	 public void selectDatePicker() throws Exception{
		 
		 driver.findElement(By.linkText("Datepicker")).click();
		 Thread.sleep(3000);
		 
		 driver.findElement(By.linkText("Animations")).click();
		 Thread.sleep(3000);
		 
		 driver.findElement(By.id("datepicker2")).click();
		 driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']//td[7][@data-month='11']")).click();
		 Thread.sleep(3000);
	 }

	 @Test
	 public void menuWithSubMenu() throws Exception{ //Mouse hover operation
		 
		 driver.findElement(By.linkText("Menu")).click();
		 Thread.sleep(2000);
		 
		 driver.findElement(By.linkText("Menu With Sub Menu")).click();
		 Thread.sleep(2000);
		 
		 Actions action = new Actions(driver);
		 WebElement hoverElement = driver.findElement(By.cssSelector("#tabs-2 > div.inside_contain > #navigate > ul.top-level > li.ui-corner-left > a"));
		
		 action.moveToElement(hoverElement).perform();
		 Thread.sleep(1000);
		 WebElement subMenuElement = driver.findElement(By.linkText("Sub Menu Item 1"));
		 action.moveToElement(subMenuElement).click().perform();		 
	 }

	 @Test
	 public void sliderClickAndHoldAndDrag() throws Exception{ //Click and Hold Slider then drag it
		 		 
		 driver.findElement(By.linkText("Slider")).click();
		 Thread.sleep(2000);
		 
		 
		 Actions action = new Actions(driver);
		 WebElement sliderPoint = driver.findElement(By.xpath("//div[@id='slider-range-max']//span[@class='ui-slider-handle ui-state-default ui-corner-all']"));
		
		
		 action.clickAndHold(sliderPoint).perform();
	     action.dragAndDropBy(sliderPoint, 500, 0).build().perform();
	     
	 }

	 @Test
	 public void findVariousWebElementsUsingXpath() throws Exception{
				 
		 driver.findElement(By.linkText("Registration")).click();
		 Thread.sleep(2000);
		 
		 //////////////////////GENERAL NOTES//////////////////////////////////////////////////////////////////////////////////////////////////
		 //Xpath is a query language used to select nodes from an XML/HTML document.
		 //Xpath = can search elements backwards and forwards in the DOM hierarchy e.g., locate parent element using child element vice versa. 
		 //CSS = forward only
		 //atomic values - nodes with no children or parents. 
		 
		
		 //simple level
		 WebElement identifyElementUsingAbsoluteValue = driver.findElement(By.xpath("/html/body/div/div")); //Absolute values are used by single slashes
		 
		 WebElement identifyElementUsingRelativePath = driver.findElement(By.xpath("//input[1]")); //Relative path with double slashes. brackets indicate index number, also called a predicate.
		
		 WebElement identifyElementUsingAttributeValue = driver.findElement(By.xpath("//input[@id='name_3_firstname']")); //Xpath with attribute value
		
		 WebElement identifyElementUsingMultipleAttributeValues = driver.findElement(By.xpath("//input[@id='name_3_firstname'][@name='first_name']")); //Xpath with attribute value
		 
		 WebElement identifyElementUsingAndOperator = driver.findElement(By.xpath("//input[@id='name_3_firstname' and @name='first_name']")); //Xpath And Operator
		 
		 WebElement identifyElementUsingOrOperator = driver.findElement(By.xpath("//input[@id='name_3_firstname' or @name='first_name']")); //Xpath Or Operator

		 List<WebElement> identifyElementsUsingDefinedAttributes = driver.findElements(By.xpath("//input[@name]")); //Xpath looking for elements that have a specifically defined attribute

		 List<WebElement> identifyElementsUsingNotOperator = driver.findElements(By.xpath("//input[not(@name)]")); //Xpath looking for elements that do not have a certain attribute

		 
		 //Performing partial match on attribute values using Xpath functions are useful. This is useful with certain apps such as ASP.NET apps where IDs are 
		 //generated dynamically.
		 
		 //intermediate level
		 WebElement usingStartWithFunction = driver.findElement(By.xpath("//input[starts-with(@id, 'name')]")); //starts-with function
		 
		 //WebElement usingEndWithFunction = driver.findElement(By.xpath("//input[ends-with(@id, '_lastname')]")); //ends-with function **works only with xpath 2.0** Browsers mostly support 1.0
		 //http://stackoverflow.com/questions/22436789/xpath-ends-with-does-not-work
		 
		 WebElement usingContainsFunction = driver.findElement(By.xpath("//input[contains(@id, 'lastname')]")); //contains function
		 
		 WebElement matchingAnyattributeUsingAvalue = driver.findElement(By.xpath("//input[@*='name_3_lastname']")); //wildcard matching any attribute
		 
		 //WebElement matchingElementWithChildAndPredicate = driver.findElement(By.xpath("//div[@class='fieldset error']/input[1]")); //using parent child + predicate *not working*

		 WebElement selectingLastChildElementofParent = driver.findElement(By.xpath("//form/ul[@id='pie_register']/li[last()]")); //using last function

		 WebElement selectingSecondToLastChildElementofParent = driver.findElement(By.xpath("//form/ul[@id='pie_register']/li[last()-1]")); //selecting 2nd to last
		 
		 WebElement selectingNthToSpecifiedNumber = driver.findElement(By.xpath("//form/ul[@id='pie_register']/li[position()>4]")); //selecting row that is greater than the nth 

		 //Selecting unknown nodes
		 
		 //            /table/* - matches any element node. 
		 //            //td[@*] - select td elements that have any attribute.
		 //            //table/node()  - will select all child elements of a table
		 
		 
		
		 //notes on the union | operator
		 //div| /p |  -this will select all the p(paragraph) and span elements of the div element.
		 //p | //span  -this will select all the p and span elements in the document.
		 
		 WebElement selectAncestorElement = driver.findElement(By.xpath("//li[@class='fields pageFields_1 ']/ancestor::form")); //selecting the ancestor of the current element.

		 WebElement selectDesendentElement = driver.findElement(By.xpath("//li[@class='fields pageFields_1 ']/descendant::div")); //selecting the descendants of the current element.

		 WebElement selectEverythingAfterSomething = driver.findElement(By.xpath("//li[@class='fields pageFields_1 ']/following::div")); //selecting everything After a closing tag in the document
		 
		 WebElement selectSiblingsAfterCurrentNode = driver.findElement(By.xpath("//h2[text()='Registration Form']/following-sibling::*")); //siblings after current node

		 WebElement selectALlNodesBeforeCurrentNode = driver.findElement(By.xpath("//h2[text()='Registration Form']/preceding::*")); //nodes appear before current node

		 //WebElement selectSiblingsBeforeCurrentNode = driver.findElement(By.xpath("//form[@id='pie_regiser_form']/preceding-sibling::ul")); //siblings appear before the current node **not working**

		 WebElement identifyElementsWithContainTextFunction = driver.findElement(By.xpath("//*[contains(text(), 'Last Name')]")); //using contains text function
		 WebElement identifyElementsWithContainTextFunctionPart2 = driver.findElement(By.xpath("//*[contains(., 'Last Name')]")); //using contains without text function
		 WebElement identifyElementsWithExactText = driver.findElement(By.xpath("//*[.='Last Name']")); //using exact text		 
	 }
	 
	 @Test
	 public void findElementsUsingCSSSelector() throws Exception{
		
		System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
		
		driver.findElement(By.linkText("Registration")).click();
		Thread.sleep(2000);
		
		//CSS (Cascading Style Sheet) is a style sheet language used to describe the presentation semantics (looks and formatting) of a document.
		
		 WebElement identifyElementUsingAbsoluteValue = driver.findElement(By.cssSelector("html body div div")); //absolute paths have spaces between each element
		 WebElement identifyElementUsingAbsoluteValueWithSeperator = driver.findElement(By.cssSelector("html > body > div > div")); //direct parent to child relationship

		// WebElement identifyElementUsingClass = driver.findElement(By.cssSelector("input.attributeOfClass")); //Finding elements using class selector.
		 WebElement identifyElementUsingId = driver.findElement(By.cssSelector("input#name_3_firstname")); //use hash # to find elements with ID.
		 WebElement identifyElementUsingAttributeLocator = driver.findElement(By.cssSelector("input[name=first_name]")); //using name
		 WebElement identifyElementUsingMoreThanOneAttribute = driver.findElement(By.cssSelector("input[name=first_name][type='text']")); //multiple attributes
		 List<WebElement> identifyElementUsingNotBoolean = driver.findElements(By.cssSelector("input:not([name])")); //using not operator
		 List<WebElement> identifyElementUsingOr = driver.findElements(By.cssSelector("input, div")); //using or operator
		// List<WebElement> identifyElementUsingOrWithOrder = driver.findElements(By.cssSelector("input.first, div.last")); //using or with order
		 
		 //partial match on attribute values
		 WebElement identifyElementUsingStartWith = driver.findElement(By.cssSelector("input[id^='name_3']")); //attribute starts with a certain value.
		 WebElement identifyElementUsingEndingWith = driver.findElement(By.cssSelector("input[id$='firstname']")); //attribute ends with a certain value.
		 WebElement identifyElementUsingContainsAttributeName = driver.findElement(By.cssSelector("input[id*='firstname']")); //attribute contains with a certain value.
		 
		 
		 //CSS contains psuedo
		 //WebElement identifyElementUsingContainstextValue = driver.findElement(By.cssSelector("label:contains('First Name')")); //attribute contains with a certain value. decprecated for CSS3
		 
		 //WebElement identifyElementUsingInnerText = driver.findElement(By.cssSelector("label[innerText='First Name']")); //does not work for firefox
		 //WebElement identifyElementUsingInnerTextForFirefox = driver.findElement(By.cssSelector("label[textContent='First Name']")); //does not work for firefox
	 
		 //Advanced CSS selector examples///////////////////////////////
		 
		 //WebElement identifyElementUsingIdGetChild = driver.findElement(By.cssSelector("css=ul#pie_register > li")); //get specific child
		 WebElement identifyElementUsingnFIrstChild = driver.findElement(By.cssSelector("ul#pie_register :first-child")); //get first child
		 WebElement identifyElementUsingnLastChild = driver.findElement(By.cssSelector("ul#pie_register :last-child")); //get last child
		 WebElement identifyElementUsingnThChild = driver.findElement(By.cssSelector("ul#pie_register :nth-child(1)")); //get nth child
		 
		 WebElement identifyElementUsingnSibling = driver.findElement(By.cssSelector("ul#pie_register > li > div > label + input")); //get sibling of child using + operator
		 WebElement identifyElementUsingnSiblingSkipping = driver.findElement(By.cssSelector("ul#pie_register > li + * + li + * + li")); //skip certain siblings using *

		 //other useful CSS seletors
		 //   input:focus      -this will find locate any element that currently has the input focus.
		 //   input:hover
		 //   input:active
		 
		 //UI state pseudo-classes
		 //     input:enabled
		 //     input:disabled
		 //     input:checked
		 

		System.out.println("Ending test " + new Object(){}.getClass().getEnclosingMethod().getName());
	}

	 @Test
	 public void SelectMultipleElementsWithClickAndHold() throws Exception{
		 
		System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
		     
		driver.findElement(By.linkText("Draggable")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.linkText("Selectable")).click();
		Thread.sleep(3000);	
		
		List<WebElement> list = driver.findElements(By.cssSelector("ol#selectable *"));
			 Actions actions = new Actions(driver);
			 
			 actions.clickAndHold(list.get(0))
			.clickAndHold(list.get(4))
			.release()
			.build()
			.perform();
			
	     System.out.println("Ending test " + new Object(){}.getClass().getEnclosingMethod().getName());
		 
	 }

	 @Test
	 public void DragAndVerifyTriggeredEvents() throws Throwable{ 

		 System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
	     
		 driver.findElement(By.linkText("Draggable")).click();
		 Thread.sleep(3000);
		 
		 driver.findElement(By.linkText("Events")).click();
		 Thread.sleep(3000);
		 
		 String startInvoked = driver.findElement(By.xpath("//li[@id='event-start']/span[2][@class='count']")).getText();
		 String dragInvoked = driver.findElement(By.xpath("//li[@id='event-drag']/span[2][@class='count']")).getText();
		 String stopInvoked = driver.findElement(By.xpath("//li[@id='event-stop']/span[2][@class='count']")).getText();
		 
	     System.out.println("Before Event Start is: " + startInvoked);
	     System.out.println("Before Event drop is: " + dragInvoked);
	     System.out.println("Before Event stop is: " + stopInvoked);

		 Actions actions = new Actions(driver);
	     WebElement triggerEvent = driver.findElement(By.id("dragevent"));

	     //drag and drop a webelement horizontally by 100 pixels.
	     actions.dragAndDropBy(triggerEvent, 100, 100).build().perform();
	     Thread.sleep(1000);
	     
	    startInvoked = driver.findElement(By.xpath("//li[@id='event-start']/span[2][@class='count']")).getText();
		dragInvoked = driver.findElement(By.xpath("//li[@id='event-drag']/span[2][@class='count']")).getText();
		stopInvoked = driver.findElement(By.xpath("//li[@id='event-stop']/span[2][@class='count']")).getText();

		System.out.println("After Event Start is: " + startInvoked);
	    System.out.println("After Event drop is: " + dragInvoked);
	    System.out.println("After Event stop is: " + stopInvoked);
	     
	     System.out.println("Ending test " + new Object(){}.getClass().getEnclosingMethod().getName());
	 }
	 
	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {
	}

	@Parameters({ "Browser-Type" })
	@BeforeTest
	public void beforeTest(String browser) {
		DriverUtil browserUtil = new DriverUtil(browser, true);
		browserUtil.launchDriver();

		driver = DriverUtil.driver;

		// username: fhrhg58687%^&*
		// password: fhrhg58687%^&*
		driver.navigate().to("http://www.way2automation.com/demo.html");
	}

	@AfterTest
	public void afterTest() {
	}

}
