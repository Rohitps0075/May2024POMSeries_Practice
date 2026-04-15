package com.qa.opencart.practice.tests;

import java.util.Random;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.practice.base.Base;
import com.qa.opencart.practice.constants.AppConstants;
import com.qa.opencart.practice.utils.ExcelUtil;

public class RegisterPageTest extends Base {

	@BeforeClass
	public void registerSetUp() {
		registerPage = loginpage.navigateToRegisterPage();

	}

	public String getRandomEmail() {
		return "AutomationTest" + System.currentTimeMillis() + "@opencart.com";
	}

	
	
	@DataProvider
	public Object[][] excelData()
	{
		return ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
	}
	
	
	
	
	@Test(dataProvider = "excelData")
	public void UserRegisterTest(String firstname,String lastname,String telephone,String password,String subscrib)
	{
		registerPage.getUserRegstration(firstname, lastname, getRandomEmail(), telephone, password, subscrib);
	}

}
