package com.qa.opencart.practice.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.practice.base.Base;
import com.qa.opencart.practice.constants.AppConstants;

public class AccountsPageTest extends Base {
	
	// to go to accounts page first we need to login to the application
	// so login -->accounts page--> enter the search text and click search
	// page chaining model, when we click on a  button or link or any webelement , because of that click if we landed on a new web page then we should return the object of the landing page.
	// here in loginpage when we click on the login button it is navigating to the accounts page so we should return the accountspage class object.
	
	
	
	@BeforeClass
	public void accSetup()
	{
		accPage=loginpage.dologin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void accountPageTitleTest()
	{
		String accTitle=accPage.getAccountsPageTitle();
		Assert.assertEquals(accTitle, AppConstants.Account_PAGE_TITLE);
	}
	
	@Test
	public void accountPageLogoutLinkExistTest()
	{
	Assert.assertTrue(	accPage.isAccPageLogOutLinkExist());
	}
	
	@Test
	public void accPageHeaderTest()
	{
	
		List<String> acctualheaderList=accPage.getAccPageHeaders();
		
		Assert.assertEquals(acctualheaderList, AppConstants.EXPECTEDHEADERLIST);
	}
	
	
	@DataProvider
	public Object[][] getSearchKey()
	{
		return new Object[][]
				{
			{"macbook",3},
			{"imac", 1},
			{"samsung",2}
				};
	}
	
	
	
	@Test(dataProvider = "getSearchKey")
	public void searchCountTest(String searchKey, int searchCount)
	{
		resultsPage=accPage.doSearch(searchKey);
		Assert.assertEquals(resultsPage.getSearchResultCount(), searchCount);
	}
	
	@DataProvider
	public Object[][] getSearchData()
	{
		return new Object[][]
				{
			{"macbook","MacBook Pro"},
			{"macbook","MacBook"},
			{"imac","iMac"},
			{"samsung","Samsung SyncMaster 941BW"},
			{"samsung","Samsung Galaxy Tab 10.1"},
				};
	}
	
	
	
	@Test(dataProvider = "getSearchData")
	public void searchTest(String searchKey, String productName)
	{
		resultsPage=accPage.doSearch(searchKey);
		productInfoPage=resultsPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductHeader(), productName);
		
	}
	

}
