package com.qa.opencart.practice.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import com.qa.opencart.practice.errors.AppErrors;
import com.qa.opencart.practice.exceptions.BrowserException;
import com.qa.opencart.practice.exceptions.FrameWorkException;

import io.qameta.allure.Step;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	public static String elementHighlight;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	@Step("Initlizeling the Properties:{0}")
	public WebDriver initDriver(Properties prop) {

		String Browser = prop.getProperty("browser");
		System.out.println(Browser);

		elementHighlight = prop.getProperty("highlight");

		OptionsManager optionManager = new OptionsManager(prop);
		

		switch (Browser.trim().toLowerCase()) {

		case "chrome":
			// driver=new ChromeDriver(optionManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionManager.getChromeOptions()));
			break;

		case "edge":
			// driver=new EdgeDriver(optionManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionManager.getEdgeOptions()));
			break;

		case "firefox":
			// driver=new FirefoxDriver(optionManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionManager.getFirefoxOptions()));
			break;

		default:
			System.out.println(AppErrors.INVALID_BROWSER_MSG);
			throw new BrowserException("=====" + AppErrors.INVALID_BROWSER_MSG + "=====");
		}

		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));// updating the hardcoded url to non hardcoded
		return getDriver();
	}

	// get method, which will generate the thred locals
	public static WebDriver getDriver() {
		return tlDriver.get();// this method will return the driver with thread local
	}

	public Properties intiprop() {
		prop = new Properties();
		FileInputStream ip = null;

		String envName = System.getProperty("env");
		System.out.println("running test on env====>" + envName);

		try {
			if (envName == null) {
				System.out.println("env is null hence russing the test in QA");
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			}

			else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "stages":
					ip = new FileInputStream("./src/test/resources/config/stages.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;
				default:
					System.out.println("Please pass the right env===>" + envName);
					throw new FrameWorkException("INVALID ENV");
				}
			}

			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	// first create the object of Properties class then create the obect of
	// Fileinputstream and path of the config file as parameter for the
	// constructor(fileinput stram will build the connection)
	// then load this file to properties class so that we can access the properties
	// file and there values inside in it.

	/**
	 * screenshort logic
	 * @return 
	 */
	
	public static String getScreenshot(String methodName)
	{
		TakesScreenshot ts=(TakesScreenshot)getDriver();
		File srcFile=ts.getScreenshotAs(OutputType.FILE);// screenshot will be stored in the temp folder we need to copy to our own folder.
		
		String path=System.getProperty("user.dir") +"/screenshot/"+methodName+"_"+System.currentTimeMillis()+".png";
				
		File file=new File(path);
		
		try {
			FileHandler.copy(srcFile, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	
	
}
