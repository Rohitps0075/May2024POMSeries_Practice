package com.qa.opencart.practice.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class javaScriptUtil {
	private WebDriver driver;
	JavascriptExecutor js;
	
	public javaScriptUtil(WebDriver driver)
	{
		this.driver=driver;
		js=(JavascriptExecutor)driver;
	}
	
	public String getPageTitle()
	{
		return js.executeScript("return document.title").toString();
	}
	public String getPageURL()
	{
		return js.executeScript("return document.URL").toString();
	}

	public void getAlert(String Message)
	{
		js.executeScript("alert('"+Message+"')");
	}
	
	public  String getPageInnerText()
	{
		//JavascriptExecutor js=(JavascriptExecutor)driver;
		
		return js.executeScript("return document.documentElement.innerText").toString();
	}
	public  void goBackwithJS()
	{
		//JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("history.go(-1)");
	}
	
	public  void goForwardwithJS()
	{
		//JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("history.go(1)");
	}
	public  void refreshWithJS()
	{
		//JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("history.go(0)");
	}
	public  void scrollPageDown()
	{
		//JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	
	public  void scrollPageUp()
	{
		//JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(document.body.scrollHeight),0");
		
	}
	
	public  void scrollPageDown(String heightDown)
	{
		//JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,'"+heightDown+"')");
	}
//	public static void scrollPageUP(String heightUP)
//	{
//		JavascriptExecutor js=(JavascriptExecutor)driver;
//		js.executeScript("window.scrollTo('"+heightUP+"',0)");
//	}
	
	public  void scrollToSpecifcElement(WebElement element)
	{
		//JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)",element);
		
	}
	public  void drawborder(WebElement element)
	{
		//JavascriptExecutor js=(JavascriptExecutor)driver;
		
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}
	
	public  void flashElement(WebElement element)
	{
		String bgColor=element.getCssValue("backgroundColour");
		
		for(int i=0; i<10;i++)
		{
			changecolor("rgb(0,200,0)", element);
			changecolor(bgColor, element);
			
		}
	}
	
	private  void changecolor(String color, WebElement element)
	{
		//JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].style.backgroundColor='"+color+"'", element);
		try
		{
			Thread.sleep(20);
		}
		catch(InterruptedException e)
		{
			
		}
	}
}
