package com.qa.opencart.practice.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.practice.factory.DriverFactory;
import com.qa.opencart.practice.pages.AccountsPage;
import com.qa.opencart.practice.pages.LoginPage;
import com.qa.opencart.practice.pages.ProductInfoPage;
import com.qa.opencart.practice.pages.RegisterPage;
import com.qa.opencart.practice.pages.ResultsPage;

import io.qameta.allure.Step;

public class Base {

	DriverFactory df;
	WebDriver driver;
	
	protected LoginPage loginpage;// now the access defaults, so can't access outside the package so making it as protected so that i can access when there is parent and child relation
	protected Properties prop;// making the access specifier to protected so that i can access this in child class
	protected AccountsPage accPage;
	protected ResultsPage resultsPage;
	protected ProductInfoPage productInfoPage;
	protected RegisterPage registerPage;
	protected SoftAssert softAssert;
	
	
	@Step("Launching the Browseer:{0}")
	@Parameters({"browser"})
	@BeforeTest
	public void setup(@Optional("chrome") String browserName)
	{
		df=new DriverFactory();
		prop =df.intiprop();
		
		//check if browser is coming from xml , if yes use the xml value else use the value from config file
		
		if(browserName!=null)
		{
			prop.setProperty("browser", browserName);
		}
		
		
		 driver=df.initDriver(prop);// now i am passing the refernce of properties class so that initDriver can access what evere value wants, and also change the return of initDriver in driverfactory class.
		 loginpage=new LoginPage(driver);
		 softAssert=new SoftAssert();
	}
	
	@Step("Execution Completed-Closing the Browser")
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
