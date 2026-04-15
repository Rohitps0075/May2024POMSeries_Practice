package com.qa.opencart.practice.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.practice.constants.AppConstants;
import com.qa.opencart.practice.utils.Elementutil;

public class ProductInfoPage {

	private WebDriver driver;
	private Elementutil eleUtil;

	private By productHeader = By.cssSelector("div#content h1");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	private By producImages = By.xpath("//ul[@class='thumbnails']/li");

	private Map<String, String> productMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new Elementutil(driver);

	}

	public String getProductHeader() {
		String headerOfProduct = eleUtil
				.waitforVisiablityofElementLocated(productHeader, AppConstants.DEFAULT_SHOT_TIME_OUT).getText();
		System.out.println(headerOfProduct);
		return headerOfProduct;

	}

//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: Out Of Stock
	private void getProductMetaData() {
		List<WebElement> metaList = eleUtil.getElements(productMetaData);

		for (WebElement meta : metaList) {
			String metaText = meta.getText();
			String metaData[] = metaText.split(":");
			String metaKey = metaData[0].trim();
			String metaValue = metaData[1].trim();
			productMap.put(metaKey, metaValue);

		}
	}

	private void getProductPriceData() {
		List<WebElement> productList = eleUtil.getElements(productPriceData);
		String productPrice = productList.get(0).getText();
		String exTaxPrice = productList.get(1).getText().split(":")[1].trim();
		productMap.put("productPrice", productPrice);
		productMap.put("exTaxPrice", exTaxPrice);

	}

	public Map<String, String> getProductData() {
		productMap = new HashMap<String, String>();// doesnot follow the order
		//productMap = new LinkedHashMap<String, String>();// linked hash map will follow the order.
//		productMap = new TreeMap<String, String>();// follow the order in alphabetical order first upper case and then lower case.
		productMap.put("Header", getProductHeader());
		getProductMetaData();
		getProductPriceData();
		System.out.println("Product Data is:==>" + productMap);
		return productMap;

	}

	public int getProductImageCount() {
		int imagesCount=eleUtil.getElements(producImages).size();
		System.out.println("Total images of the Product are===>"+imagesCount);
		return imagesCount;
	}

}
