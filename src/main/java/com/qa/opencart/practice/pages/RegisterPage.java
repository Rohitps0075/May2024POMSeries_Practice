package com.qa.opencart.practice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.practice.constants.AppConstants;
import com.qa.opencart.practice.utils.Elementutil;

public class RegisterPage {

	private WebDriver driver;
	private Elementutil eleUtil;

	private By firstname = By.id("input-firstname");
	private By lastname = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");
	private By yesbtn = By.cssSelector("input[value='1'][name='newsletter']");
	private By nobtn = By.cssSelector("input[value='0']");
	private By privacypolicy = By.xpath("//input[@name='agree']");
	private By continuebtn = By.xpath("//input[@value='Continue']");
	private By logoutLink=By.linkText("Logout");
	private By registerLink=By.linkText("Register");
	
	private By sucessMessage=By.cssSelector("div#content h1");

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new Elementutil(driver);
	}

	public boolean getUserRegstration(String firstName, String lastName, String Email,String telePhone,String passWord, String subValue) {
		eleUtil.waitforVisiablityofElementLocated(firstname, AppConstants.DEFAULT_SHOT_TIME_OUT).sendKeys(firstName);
		eleUtil.dosendKeys(lastname, lastName);
		eleUtil.dosendKeys(email, Email);
		
		eleUtil.dosendkeys(telephone, telePhone);
		eleUtil.dosendKeys(password, passWord);
		eleUtil.dosendKeys(confirmpassword, passWord);
		
		if(subValue.equalsIgnoreCase("yes"))
		{
			eleUtil.doClick(yesbtn);
		}
		else
		{
			eleUtil.doClick(nobtn);
		}
		
		eleUtil.doClick(privacypolicy);
		eleUtil.doClick(continuebtn);
		
		String successmessage=eleUtil.getElement(sucessMessage).getText();
		System.out.println(successmessage);
		
		if(successmessage.contains(AppConstants.SUCESS_MESSAGE))
		{
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		}
		return false;

	}

}
