package com.qa.opencart.practice.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.practice.base.Base;
import com.qa.opencart.practice.constants.AppConstants;
import com.qa.opencart.practice.utils.ExcelUtil;

public class ProductInfoPageTest extends Base {

	
	@BeforeClass
	public void productInfoPageSetUp()
	{
		accPage=loginpage.dologin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] productHeaderData()
	{
		return new Object[][] {
			{"macbook","MacBook Pro"},
			{"imac","iMac"},
			{"samsung","Samsung SyncMaster 941BW"},
			{"samsung","Samsung Galaxy Tab 10.1"},
			{"canon","Canon EOS 5D"},
		};
	}
	
	@Test(dataProvider = "productHeaderData")
	public void getProductHeader(String searchKey,String productName)
	{
		resultsPage=accPage.doSearch(searchKey);
		productInfoPage=resultsPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductHeader(),productName);
		
	}
	
	@Test
	public void getProductData()
	{
		resultsPage=accPage.doSearch("macbook");
		productInfoPage=resultsPage.selectProduct("MacBook Pro");
		Map<String,String>acctualProductData=productInfoPage.getProductData();
		softAssert.assertEquals(acctualProductData.get("Brand"), "Apple");
		softAssert.assertEquals(acctualProductData.get("Product Code"), "Product 18");
		softAssert.assertEquals(acctualProductData.get("Reward Points"), "800");
		softAssert.assertEquals(acctualProductData.get("Availability"), "Out Of Stock");
		softAssert.assertAll();
	}
	
	@DataProvider
	public Object[][] productImageData()
	{
		return new Object[][] {
			{"macbook","MacBook Pro",4},
			{"imac","iMac",3},
			{"samsung","Samsung SyncMaster 941BW",1},
			{"samsung","Samsung Galaxy Tab 10.1",7},
			{"canon","Canon EOS 5D",3},
			{"macbook","MacBook",5}
			
		};
	}
	
//	@DataProvider
//	public Object[][] excelProductImageData()
//	{
//		return ExcelUtil.getTestData(AppConstants.SEARCH_SHEET_NAME);
//	}
	
	
	@Test(dataProvider = "productImageData")
	public void getProductImageCount(String searchKey,String productName,int imageCount)
	{
		resultsPage=accPage.doSearch(searchKey);
		productInfoPage=resultsPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductImageCount(),imageCount);
	}
	
/*
 * lets update the code
 * lets pass the data using data provider
 * and lets replace the hard assert with soft asserts--> because hard assert if the validation fails then the execution will be terminated, execution will not continue
 * soft assert if validation fails it will continue the execution
 * for soft assert we need to create the object because  soft assert methods are not static
 * for hard assert we use class name because hard assert methods are static.
 * in soft asserts we have to use assertAll(); at the last otherwise the this will not show the failed tests
 * 
 * lets pass the browser from the xml and will do cross browser testing.
 * 
 * 
 * 
 */
}
