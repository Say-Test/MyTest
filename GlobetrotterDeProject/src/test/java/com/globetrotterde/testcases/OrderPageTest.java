package com.globetrotterde.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.globetrotterde.base.BaseClass;
import com.globetrotterde.dataprovider.DataProviders;
import com.globetrotterde.pageobjects.AddToCartPage;
import com.globetrotterde.pageobjects.OrderPage;
import com.globetrotterde.pageobjects.SearchResultPage;
import com.globetrotterde.pageobjects.WebIndexPage;
import com.globetrotterde.utility.Log;

/**
 * @Author Sayrina Lopes
 */

public class OrderPageTest extends BaseClass {

	private WebIndexPage index;
	private SearchResultPage searchResultPage;
	private AddToCartPage addToCartPage;
	private OrderPage orderPage;

	@Parameters("browser")
	@BeforeMethod(groups = {"Smoke","Sanity","Regression"})
	public void setup(String browser) {
		launchApp(browser); 
	}
	
	@AfterMethod(groups = {"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}
	
	@Test(groups = "Regression",dataProvider = "getProduct", dataProviderClass = DataProviders.class)
	public void verifyTotalPrice(String productName, String qty, String size) throws Throwable {
		Log.startTestCase("verifyTotalPrice");
		index= new WebIndexPage();
		searchResultPage=index.searchProduct(productName);
		addToCartPage=searchResultPage.clickOnProduct();
		//addToCartPage.enterQuantity(qty);
		addToCartPage.clickOnSize();
		addToCartPage.clickOnAddToCart();
		orderPage.enterQuantity(qty);
		
		//verifyOrderPageUrl
		String actualOrderURL=orderPage.getCurrURL();
	    //after AddToCart expected url
	    String expectedOrderURL="https://integ.globetrotter.de/basket/";
	    Log.info("Verifying if user is able to proceed");
	    Assert.assertEquals(actualOrderURL, expectedOrderURL);

	    //Verify selected Size on orderPage div text xpath= //font[contains(text(),'Details: ')]
		  orderPage.verifySelectedSize(size);

	    
		orderPage=addToCartPage.clickOnCheckOut();
		Double unitPrice=orderPage.getUnitPrice();
		Double totalPrice=orderPage.getTotalPrice();
		Double totalExpectedPrice=(unitPrice*(Double.parseDouble(qty)))+2;
		Assert.assertEquals(totalPrice, totalExpectedPrice);
		Log.endTestCase("verifyTotalPrice");
	}
}
