 package com.qa.opencart.practice.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.practice.factory.DriverFactory;

import io.qameta.allure.Step;

public class Elementutil {
	private WebDriver driver;
	private Actions act;
	 javaScriptUtil jsUtil;
	
	// added first
	public Elementutil(WebDriver driver)
	{
		this.driver=driver;
		act=new Actions(driver);
		jsUtil=new javaScriptUtil(driver);
	}
	
	// added first
	@Step("Send the value:{1} to the Locators:{0}")
	public void dosendKeys(By Locators, String value)
	{
		getElement(Locators).sendKeys(value);
	}
	
	//3rd added.
		public  void dosendkeys(By Locators, CharSequence... value)// ... (3dots)is a mandatory when creating the generic method. 
		{
			getElement(Locators).sendKeys(value);
		}

	// added second
		@Step("performing the Click action on the Locator:{0}")
	public  void doClick(By Locators)
	{
		getElement(Locators).click();
	}
	// add a generic method for get text
	
	public String getElementText(By Locators)
	{
		return getElement(Locators).getText();
	}
	// 4th added
	
	public  boolean isElementDisplayed(By Locators)
	{
		try {
			return getElement(Locators).isDisplayed();
		} 
		
		catch (NoSuchElementException e) {
			System.out.println("Element is not displayed" + Locators);
			return false;
		}
		
	}
	
	// 5th added
	public  boolean isElementEnabled(By Locators)
	{
		try {
			return getElement(Locators).isEnabled();
		} catch (NoSuchElementException e) {
			
			System.out.println("please pass the with locator "+Locators);
			return false;
			
		}
	}
	// 6th added
	
	public  boolean isElementSelected(By Locators)
	{
		try {
			return getElement(Locators).isSelected();
			
		} catch (NoSuchElementException e) {
			System.out.println("Please pass the right locator "+Locators);
			return false;
		}
	}
	
	//7th added
	public  String doElementgetAttribute(By Locators, String attrvalue)
	{
		return getElement(Locators).getAttribute(attrvalue);
	}
	//8th added
	public  List<WebElement> getElements(By Locators)
	{
		return driver.findElements(Locators);
	}

	//8th added
	public  int getElementSize(By Locators)
	{
		return getElements(Locators).size();
	}
	
	//8th added
	public  List<String> getElementTextList(By Locators) {
		List<WebElement> Links=getElements(Locators);
		List<String> Links2=new ArrayList<String>();
		for(WebElement e:Links)
		{
			String Linktext=e.getText();
			if(Linktext.length()!=0)
			{
				Links2.add(Linktext);
			}
		}
		return Links2;
	}
	//8th added
	public  void PrintElementTextValue(By Locators)
	{
		List<String> TotalText=getElementTextList(Locators);
	
		for(String e:TotalText)
		{
			System.out.println(e);
		}
	}
	//9th added
	
	public  void doSearch(By searchFiels, String searchValue, By Sugessions,String matchValue) throws InterruptedException
	{
		WebElement ele=driver.findElement(searchFiels);
		ele.sendKeys(searchValue);
		
		Thread.sleep(3000);
		

		List<WebElement> suggtext=driver.findElements(Sugessions);
		
		for(WebElement e:suggtext)
		{
			String text=e.getText();
			System.out.println(text);
			
			if(text.contains(matchValue))
			{
				e.click();
				break;
			}
		}
		System.out.println("clicked on the element ");
	}
	
	// 10 added 
	
	public  boolean isElementPresent(By Locators)
	{

		if(getElementSize(Locators)==1)
		{
			return true;
		}
		return false;
	
	}
	// 10 added 
	public  boolean isElementPresent(By Locators, int ExpectedElementcount)
	{
		if(getElementSize(Locators)==ExpectedElementcount)
		{
			return true;
		}
		return false;
	}
	// 10 added 
	public  boolean isElementnotPresent(By Locators)
	{
		if(getElementSize(Locators)==0)
		{
			return true;
		}
		return false;
	}
	// 10 added 
	public  boolean isElementPresentMultipleTimes(By Locators)
	{
		if(getElementSize(Locators)>=1)
		{
			return true;
		}
		return false;
	}
	
	
	//================= Select Class generic methods ===================
	
	//11 added
	
	/**
	 * 
	 * @param Locators
	 * @param visiableText
	 * 
	 * this method will select the drop down option based on visiable text
	 */
	public  void selectDropdownbyVisiableText(By Locators, String visiableText)
	{
		//Select select=new Select(getElement(Locators)); replaced with  getSelect(Locators) method
		 getSelect(Locators).selectByVisibleText(visiableText);
	}
	//11 added
	/**
	 * 
	 * @param Locators
	 * @param index
	 * 
	 * this method will select the drop down option based on index value
	 */
	
	public  void selectDropdownbyIndex(By Locators, int index)
	{
		//Select select=new Select(getElement(Locators)); replaced with  getSelect(Locators) method
		 getSelect(Locators).selectByIndex(index);
				
	}
	//11 added
	/**
	 * 
	 * @param Locators
	 * @param value
	 * 
	 * this method will slect the drop down option based on value attribute value.
	 */
	public  void selectDropdownbyValue(By Locators, String value) { 
		// Select select=new Select(getElement(Locators)); replaced with  getSelect(Locators) method
		 getSelect(Locators).selectByValue(value);
	}
	//11 added
	/**
	 * 
	 * @param Locators
	 * @return
	 * 
	 * this method will return the no of options avilable in the drop down
	 */
	
	public  int selectDropdoewOptionCount(By Locators)
	{
		//Select select=new Select(getElement(Locators)); replaced with  getSelect(Locators) method
		return  getSelect(Locators).getOptions().size();
	}
// 12 added
	
	public  List<String> getDropdownOptionTextList(By Locators)
	{
		// Select select = new Select(getElement(Locators));  replaced with  getSelect(Locators) method
		
		List<WebElement> optList= getSelect(Locators).getOptions();
		
		List<String> list=new ArrayList<String>();
	System.out.println(optList.size());
	
	for(WebElement e: optList )
	{
		String text=e.getText();
		list.add(text);
		
	}
		return list;
		
		
	}
	
	// 13 added
	
	public  void slectDropdownValue(By Locators, String value)
	{
		List<WebElement> list=getElements(Locators);
		
		selectDropDown(list, value);//replacing the below code with is generic method-15
		
//		int count=list.size();
//	System.out.println(count);
//	
//	for(WebElement e: list)
//	{
//		String text=e.getText();
//		System.out.println(text);
//		
//		if(text.contains(value))
//		{
//			e.click();
//			break;
//		}
//	}
	}
	
	//14 added
	public  void selectDropDownvalueUsingSlect(By Locators, String value)
	{
		Select select=new Select(getElement(Locators));
				
		List<WebElement>list=select.getOptions();
		
		selectDropDown(list, value);//replacing the below code with is generic method-15
		
//		System.out.println(list.size());
//		for(WebElement e:list) {
//			String text=e.getText();
//			System.out.println(text);
//			if(text.contains(value))
//			{
//				e.click();
//				break;
//			}
//
//		}
		
	}
	
	//15 added
	private static void selectDropDown(List<WebElement> list, String value)
	{
		System.out.println(list.size());
		for(WebElement e:list) {
			String text=e.getText();
			System.out.println(text);
			if(text.contains(value))
			{
				e.click();
				break;
			}

		}
	}
	
	
	
	//16 added 
	// generic method for select class object
	// now replace all the methods which we created with the below method.
		public Select getSelect(By Locators)
		{
			return new Select(getElement(Locators));
		}
	
		
		//############# Actions Class #############
		
		public  void parentChildMenu(By parentMenu, By childMenu) throws InterruptedException
		{
			//Actions act=new Actions(driver); 
			//removing above line from here and declaring Actions act as private and under constructor create the object
			
			act.moveToElement(getElement(parentMenu)).perform();
			
			Thread.sleep(4000);
			
		//	getElement(childMenu).click(); replaced this line with doclick method
			doClick(childMenu);
		}
		
		
		public  void parentChildMenu(String parentMenu, String childMenu) throws InterruptedException
		{
			//Actions act=new Actions(driver);
			//removing above line from here and declaring Actions act as private and under constructor create the object
			act.moveToElement(getElement(By.xpath("//*[text()='"+parentMenu+"']"))).perform();
			
			Thread.sleep(4000);
			
			//getElement(By.xpath("//*[text()='"+childMenu+"']")).click();  replaced this line with doclick method
			doClick(By.xpath("//*[text()='"+childMenu+"']"));
			
		}
		public  void parentChildMenu(By Level_1_Menu, By Level_2_Menu, By Level_3_Menu, By Level_4_Menu) throws InterruptedException
		{
			//getElement(Level_1_Menu).click(); replaced this line with doclick method
			doClick(Level_1_Menu);
			Thread.sleep(4000);
			//Actions act=new Actions(driver);
			//removing above line from here and declaring Actions act as private and under constructor create the object
			
			act.moveToElement(getElement(Level_2_Menu)).perform();
			Thread.sleep(4000);
			
			act.moveToElement(getElement(Level_3_Menu)).perform();
			Thread.sleep(4000);
			//getElement(Level_4_Menu).click();replaced this line with doclick method
			doClick(Level_4_Menu);
		}
		
		public  void doActionsClick(By Locators)
		{
			//Actions act=new Actions(driver);
			//removing above line from here and declaring Actions act as private and under constructor create the object
			act.click(driver.findElement(Locators)).perform();
		}

		public  void doActionsSendkeys(By Locators,String value)
		{
			//Actions act=new Actions(driver);
			//removing above line from here and declaring Actions act as private and under constructor create the object
			act.sendKeys(getElement(Locators), value).perform();
		}
	
		public  void doRightClick(By Locators, By LocatorClick)
		{
			//Actions act=new Actions(driver);
			//removing above line from here and declaring Actions act as private and under constructor create the object
			act.contextClick(driver.findElement(Locators)).perform();
			
			doClick(LocatorClick);
		}
		
		public  void doActionsSendKeysWithPause(String value, By Locators ,long TimePeriod)
		{
			//Actions act=new Actions(driver);
			//removing above line from here and declaring Actions act as private and under constructor create the object
		char ch[]=value.toCharArray();
			for(char c:ch)
				{
				act.sendKeys(getElement(Locators), String.valueOf(c)).pause(TimePeriod).perform();
				}
			
			}
		
		public void checkElementHighlight(WebElement element)
		{
			
			if(Boolean.parseBoolean(DriverFactory.elementHighlight))
			{
				jsUtil.flashElement(element);
			}
			
			
			
			
		}
		
				// added first
		public WebElement getElement(By Locators)
			{
				WebElement element= driver.findElement(Locators);
				checkElementHighlight(element);
				return element;
					}

	
	//*********************** Explictly wait ***************************
		/**
		 * this will find the element with some wait.(presance of element located)
		 * @param Locators
		 * @param timeouts
		 * @return
		 */
		public  WebElement waitForElementPresence(By Locators, int timeouts)
		{
			
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeouts));
			WebElement element= wait.until(ExpectedConditions.presenceOfElementLocated(Locators));
			checkElementHighlight(element);
			return element;
					
		}
	
		/**
		 * this will find the element with some wait(visibiltyofElementLocated)
		 * @param Locators
		 * @param timeouts
		 * @return
		 */
		@Step("wait for the Locators:{0} for timeout :{1}")
		public  WebElement  waitforVisiablityofElementLocated(By Locators, int timeouts)
		{
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeouts));
			WebElement element= wait.until(ExpectedConditions.visibilityOfElementLocated(Locators));
			checkElementHighlight(element);
			return element;
			
		}
		
		/**
		 * do click is an overloaded method.
		 * @param Locators
		 * @param timeouts
		 */
		public  void doClick(By Locators, int timeouts)
		{
			waitForElementPresence(Locators, timeouts).click();
		}
	
		/**
		 * sendkeys with wait and this is an overloaded method
		 * @param Locators
		 * @param timeouts
		 * @param value
		 */
		public  void doSendkeys(By Locators, int timeouts, String value)
		{
			waitforVisiablityofElementLocated(Locators, timeouts).sendKeys(value);
		}
		
		
		/**
		 * to this method we can pass the our own interval time
		 * @param timeout
		 * @param intervalTime
		 * @param Locators
		 * @return
		 */
		public  WebElement getElementVisible(int timeout, int intervalTime, By Locators)
		{
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofSeconds(2));
			WebElement element= wait.until(ExpectedConditions.visibilityOfElementLocated(Locators));
			checkElementHighlight(element);
			return element;
		}
	
		/**
		 * this methods will check if the element is visiable and enabled.
		 * @param Locator
		 * @param timeout
		 */
		public  void waitForElementAndClick(By Locator, int timeout)
		{
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.elementToBeClickable(Locator)).click();
		}
		
		
		/**
		 * below methods help to find the Elements using visibility 
		 * this method will check, all the elements are present on the page
		 * @param Locator
		 * @param timeout
		 * @return
		 */
		
		public  List<WebElement> waitforElementVisiblity(By Locator,int timeout)
		{
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Locator));
		}
		
	
		/**
		 * below methods help to find the Elements using presence 
		 * this method will check at least one of the element is present on the page
		 * @param Locator
		 * @param timeout
		 * @return
		 */
		public  List<WebElement> waitforElementsPresence(By Locator,int timeout)
		{
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
			return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(Locator));
		}
		
		/**
		 * this will wait for the page title and we have handled in try catch block because it may give time out exception
		 * for this method we need to provide the complete expected title
		 * @param expectedTitle
		 * @param timeout
		 * @return
		 */
		public  Boolean waitForTitle(String expectedTitle,int timeout)
		{
			WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeout));
			
			try
			{
				return wait.until(ExpectedConditions.titleIs(expectedTitle));
			}
			catch(TimeoutException e)
			{
				System.out.println("Title is not Matched");
				return false;
			}	
		}
		/**
		 * this method is to print the title of the page
		 * for this method we need to provide the complete expected title
		 * @param expectedTitle
		 * @param timeout
		 * @return
		 */
		public  String getPageTitle(String expectedTitle, int timeout) {

			if(waitForTitle(expectedTitle, timeout))
			{
				return driver.getTitle();
			}
			else
			{
				return "-1";
			}	
		}
		
		/**
		 * this mwthod will wait for the title
		 * for this method we need to provide the fractional string ot title no need to complete title
		 * @param fractionTitle
		 * @param timeout
		 * @return
		 */
		
		// generic method for get title
		public  boolean waitForTitleContains(String fractionTitle, int timeout)
		{
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
			
			try
			{
				return wait.until(ExpectedConditions.titleContains(fractionTitle));
			}
			
			catch(TimeoutException e)
			{
				System.out.println("Title is not Matched");
				return false;
			}
		}
		/**
		 * this method will return the title of the page
		 * for this method we need to provide the fractional string ot title no need to complete title
		 * @param fractionTitle
		 * @param timeout
		 * @return
		 */
		public  String getTitleContains(String fractionTitle, int timeout)
		{
			if(waitForTitleContains(fractionTitle, timeout))
			{
				return driver.getTitle();
			}
			else
			{
				return "-1";
			}
		}
		
		// generic method for get URL
		/**
		 * this method will check URL or wait for the URL
		 * need to provide the fractional url
		 * @param fractionURL
		 * @param timeout
		 * @return
		 */
		
		public  Boolean waitforURLConatins(String fractionURL, int timeout)
		{
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
			
			try
			{
				return wait.until(ExpectedConditions.urlContains(fractionURL));
			}
			catch(TimeoutException e)
			{
				System.out.println("URL is Not Matched");
				return false;
			}
			
		}
		/**
		 * used to print the url of the page with wait.
		 * @param fractionURL
		 * @param timeout
		 * @return
		 */
		
		public  String getPageURL(String fractionURL, int timeout) {
			
			if(waitforURLConatins(fractionURL, timeout))
			{
				return driver.getCurrentUrl();
			}
			else
			{
				return "-1";
			}
		}
		
		
		
		/**
		 * Simplified version of fetching the title of the page with waits
		 * @param expectedTitle
		 * @param timeout
		 * @return
		 */
		
		public  String waitForTitleAndReturn(String expectedTitle,int timeout)
		{
			WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeout));
			
			try
			{
				 wait.until(ExpectedConditions.titleIs(expectedTitle));
				 return driver.getTitle();
			}
			catch(TimeoutException e)
			{
				System.out.println("Title is not Matched");
				return "-1";
			}	
		}
		
		/**
		 * Simplified version of fetching the title(conatins) of the page with waits
		 * @param fractionTitle
		 * @param timeout
		 * @return
		 */
		// simplified versrion of the above methods
		public  String waitForTitleContainsAndReturn(String fractionTitle, int timeout)
		{
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
			
			try
			{
				 wait.until(ExpectedConditions.titleContains(fractionTitle));
				return driver.getTitle();
			}
			
			catch(TimeoutException e)
			{
				System.out.println("Title is not Matched");
				return "-1";
			}
		}
		/**
		 * Simplified version of fetching the URL of the page with waits
		 * @param fractionURL
		 * @param timeout
		 * @return
		 */
		public  String waitforURLConatinsAndReturn(String fractionURL, int timeout)
		{
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
			
			try
			{
				 wait.until(ExpectedConditions.urlContains(fractionURL));
				 return driver.getCurrentUrl();
			}
			catch(TimeoutException e)
			{
				System.out.println("URL is Not Matched");
				return "-1";
			}
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
