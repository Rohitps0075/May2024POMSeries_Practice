package com.qa.opencart.practice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.practice.constants.AppConstants;
import com.qa.opencart.practice.utils.Elementutil;

import io.qameta.allure.Step;

public class LoginPage {
	WebDriver driver;
	Elementutil eleUtil;
	
	//private by locators
	private By username=By.id("input-email");
	private By password=By.id("input-password");
	private By forgotpwdLink=By.linkText("Forgotten Password");
	private By loginBtn= By.xpath("//input[@value='Login']");
	private By logo=By.className("img-responsive");
	private By registerLink= By.linkText("Register");
	
	//public contractor
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new Elementutil(driver);
	}
	
	// public actions or methods
	
	public String getLoginPageTitle()
	{
	//	String title=driver.getTitle();
		String title=eleUtil.waitForTitleAndReturn(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_SHOT_TIME_OUT);
		System.out.println(title);
		return title;
	}
	
	public String getLoginPageURL()
	{
		//String url=driver.getCurrentUrl();
		String url=eleUtil.waitforURLConatinsAndReturn(AppConstants.LOGIN_PAGE_FRACTION_URL, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
		System.out.println(url);
		return url;
	}
	
	public boolean isForgotPwdLinkExists()
	{
		//return driver.findElement(forgotpwdLink).isDisplayed();// passing the private variable to public methods this concept is called encapsulation
		
		return eleUtil.isElementDisplayed(forgotpwdLink);
	}

	public boolean isLogoExists()
	{
		//return driver.findElement(logo).isDisplayed();
		return eleUtil.isElementDisplayed(logo);
	}
	
	@Step("this method will do the login to the application with username:{0} and Passowrd:{1}")
	public AccountsPage dologin(String userName, String Pwd)
	{
		
		eleUtil.waitforVisiablityofElementLocated(username, AppConstants.DEFAULT_SHOT_TIME_OUT).sendKeys(userName);
		eleUtil.dosendKeys(password, Pwd);;
		eleUtil.doClick(loginBtn);
		
		
//		driver.findElement(username).sendKeys(userName);
//		driver.findElement(password).sendKeys(Pwd);
//		driver.findElement(loginBtn).click();
		
		String accPageTitle=driver.getTitle();
		System.out.println(accPageTitle);
		//return accPageTitle;
		return new AccountsPage(driver);// when we click on an link/button/or any webelement and because of this action if we land on a new page then we have to
		// return the object of landing page class.
		// here we are landing accounts page so i am returing the accounts page.	
	}
	
	
	public RegisterPage navigateToRegisterPage()
	{
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
	
	
	
	
	
	
	
	
}
