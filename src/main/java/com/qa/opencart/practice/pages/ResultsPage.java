package com.qa.opencart.practice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.practice.constants.AppConstants;
import com.qa.opencart.practice.utils.Elementutil;

public class ResultsPage {
	
	private WebDriver driver;
	private Elementutil eleUtil;
	
	
	private By searchHeader=By.cssSelector("div#content h2");
	
	private By results=By.cssSelector("div.product-thumb");

	public ResultsPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil=new Elementutil(driver);
	}
	
	
	public String getSearchHeader()
	{
		String headerValue=eleUtil.waitforVisiablityofElementLocated(searchHeader, AppConstants.DEFAULT_SHOT_TIME_OUT).getText();
		System.out.println(headerValue);
		return headerValue;
	}
	
	public int getSearchResultCount()
	{
		int resultsCount=eleUtil.waitforElementVisiblity(results, AppConstants.DEFAULT_SHOT_TIME_OUT).size();
		System.out.println("resultsCount is :- "+resultsCount);
		return resultsCount;
	
	}
	
	public ProductInfoPage selectProduct(String productName)
	{
		eleUtil.doClick(By.linkText(productName));
		return new ProductInfoPage(driver);
	}

}
