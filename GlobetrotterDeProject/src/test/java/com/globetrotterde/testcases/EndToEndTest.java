package com.globetrotterde.testcases;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.globetrotterde.base.BaseClass;
import com.globetrotterde.dataprovider.DataProviders;
import com.globetrotterde.pageobjects.AddToCartPage;
import com.globetrotterde.pageobjects.AddressPage;
import com.globetrotterde.pageobjects.LoginPage;
import com.globetrotterde.pageobjects.OrderConfirmationPage;
import com.globetrotterde.pageobjects.OrderPage;
import com.globetrotterde.pageobjects.OrderSummary;
import com.globetrotterde.pageobjects.PaymentPage;
import com.globetrotterde.pageobjects.SearchResultPage;
//import com.globetrotterde.pageobjects.ShippingPage;
import com.globetrotterde.pageobjects.WebIndexPage;
import com.globetrotterde.utility.Log;

/**
 * @Author Sayrina Lopes
 */

public class EndToEndTest extends BaseClass {
	
	private WebIndexPage index;
	private SearchResultPage searchResultPage;
	private AddToCartPage addToCartPage;
	private OrderPage orderPage;
	private LoginPage loginPage;
	private AddressPage addressPage;
	//private ShippingPage shippingPage;
	private PaymentPage paymentPage;
	private OrderSummary orderSummary;
	private OrderConfirmationPage orderConfirmationPage;

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
	public void endToEndTest(String productName, String qty, String size) throws Throwable {
		Log.startTestCase("endToEndTest");
		index= new WebIndexPage();
		searchResultPage=index.searchProduct(productName);
		addToCartPage=searchResultPage.clickOnProduct();
		//addToCartPage.enterQuantity(qty);
		addToCartPage.clickOnSize();
		addToCartPage.clickOnAddToCart();
		Thread.sleep(Duration.ofSeconds(10));
		orderPage=addToCartPage.clickOnGoToBasket();
		Thread.sleep(Duration.ofSeconds(10));
		orderPage.enterQuantity(String.valueOf((int)Double.parseDouble(qty)));
		loginPage=orderPage.clickOnCheckOut();
		Thread.sleep(Duration.ofSeconds(10));
		addressPage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"),addressPage);
		Log.info("Logged in Successfully");
		//shippingPage=addressPage.clickOnCheckOut();
		//shippingPage.checkTheTerms();
		//paymentPage=shippingPage.clickOnProceedToCheckOut();
		//orderSummary=paymentPage.clickOnPaymentMethod();
		addressPage.updatePhoneNumber(prop.getProperty("phoneNum"));
		Log.info("Updated Phone Number.");

		paymentPage=addressPage.clickOnPaymentMethod();
		Log.info("Clicked on Payment Method.");
		orderSummary=paymentPage.giveCCDetails(prop.getProperty("cname"), prop.getProperty("cnumber"), prop.getProperty("cvc"), prop.getProperty("exMonth"), prop.getProperty("exYear"));
		Log.info("Entered Credit card details.");
		orderConfirmationPage=orderSummary.clickOnbuyNowBtn();
		Log.info("Clicked on Buy now button.");
		String actualMessage=orderConfirmationPage.validateConfirmMessage();
		Log.info(actualMessage);
		String expectedMsg="Thank you for your order!";
		Assert.assertEquals(actualMessage, expectedMsg);
		Log.endTestCase("endToEndTest");
	}

}
