package com.qa.opencart.practice.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.practice.constants.AppConstants;
import com.qa.opencart.practice.utils.Elementutil;

public class AccountsPage {

	private WebDriver driver;
	private Elementutil eleUtil;
	// private locators

	By logoutLink = By.linkText("Logout");
	By headers = By.xpath("//div[@id='content']/h2");
	By searchField = By.cssSelector("input[name='search']");
	By searchBtn = By.xpath("//button[@type='button' and @class='btn btn-default btn-lg']");

	// public constructor

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new Elementutil(driver);
	}

	// public actions /methods

	public String getAccountsPageTitle() {
		String title = eleUtil.waitForTitleAndReturn(AppConstants.Account_PAGE_TITLE,
				AppConstants.DEFAULT_MEDIUM_TIME_OUT);
		System.out.println(title);
		return title;

	}

	public boolean isAccPageLogOutLinkExist() {
		return eleUtil.isElementDisplayed(logoutLink);

	}

	public List<String> getAccPageHeaders()
	{
	List<WebElement>headersList=eleUtil.waitforElementVisiblity (headers, AppConstants.DEFAULT_SHOT_TIME_OUT);
	
	List<String> headerValueList = new ArrayList<String>();
	for(WebElement e: headersList)
	{
		String header=e.getText();
		headerValueList.add(header);
	}
	return headerValueList;
	
	}
	
	public ResultsPage doSearch(String searchKey)
	{
	WebElement searchEle= eleUtil.waitforVisiablityofElementLocated(searchField, AppConstants.DEFAULT_SHOT_TIME_OUT);
	searchEle.clear();
	 searchEle.sendKeys(searchKey);
	 eleUtil.doClick(searchBtn);
	 return new ResultsPage(driver);
	 
	}
}
