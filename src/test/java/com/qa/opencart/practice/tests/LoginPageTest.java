package com.qa.opencart.practice.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.practice.base.Base;
import com.qa.opencart.practice.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC-1254 : this epic is related to the login page")
@Feature("Feature-2354 : this feature is related to login page validations")
@Story("User Story: 352 Login Page Tes")
@Link(name = "Login Page", value = "https://naveenautomationlabs.com/opencart/index.php?route=account/login")
@Owner("Rohit Shepur")

public class LoginPageTest extends Base {

	@Severity(SeverityLevel.MINOR)
	@Feature("feature-2353 Login Page Test")
	@Description("Login Page Title Test")
	@Issue("Bug-2005 : this below test is logged in the Bug 2005")
	@Test
	public void LoginPageTitleTest() {
		String acctualTitle = loginpage.getLoginPageTitle();

		Assert.assertEquals(acctualTitle, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Severity(SeverityLevel.NORMAL)
	@Feature("feature-2354 Login Page Test")
	@Description("Login Page URL Test")
	@Test
	public void LoginPageURLTest() {
		String acctualURL = loginpage.getLoginPageURL();
		Assert.assertTrue(acctualURL.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
	}

	@Severity(SeverityLevel.CRITICAL)
	@Feature("feature-2355 Login Page Test")
	@Description("Login Page Forgot Password Link Test")
	@Test
	public void ForgotPwdLinkExistsTest() {
		Assert.assertTrue(loginpage.isForgotPwdLinkExists());
	}

	@Severity(SeverityLevel.MINOR)
	@Feature("feature-2356 Login Page Test")
	@Description("Login Page Logo Exists Test")
	@Test
	public void LogoExistsTest() {
		Assert.assertTrue(loginpage.isLogoExists());
	}

	@Severity(SeverityLevel.BLOCKER)
	@Feature("feature-2357 Login Page Test")
	@Description("Login Page Do Login Test")

	@Test(priority = Integer.MAX_VALUE) // assiging maximum priority so that this test case will execute at last because
										// the default value of priority is zero.
	// since @Test will follow execution in alphabetical order, if in case the below
	// method executed means all the above test cases will get failed so i making
	// priority fo this test method
	public void LoginTest() {
		accPage = loginpage.dologin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccountsPageTitle(), AppConstants.Account_PAGE_TITLE);

	}

	// till now we passed if hardcoded values let's pass them from properties file.
	// lets replace the constant values
}
