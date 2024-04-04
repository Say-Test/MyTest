package com.globetrotterde.testcases;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;

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
	@BeforeMethod(groups = { "Smoke", "Sanity", "Regression" })
	public void setup(String browser) {
		launchApp(browser);
	}

	@AfterMethod(groups = { "Smoke", "Sanity", "Regression" })
	public void tearDown() {
		getDriver().quit();
	}

	@Test(groups = "Regression", dataProvider = "getProduct", dataProviderClass = DataProviders.class)
	public void verifyTotalPrice(String productName, String qty, String size) throws Throwable {
		Log.startTestCase("verifyTotalPrice");
		index = new WebIndexPage();
		searchResultPage = index.searchProduct(productName);
		addToCartPage = searchResultPage.clickOnProduct();
		// addToCartPage.enterQuantity(qty);
		//addToCartPage.clickOnSize();
		addToCartPage.clickOnSize(size);
		Log.info("Given Size: " + size);
		addToCartPage.clickOnAddToCart();
		Log.info("Clicked on Add to cart button.");
		// Thread.sleep(Duration.ofSeconds(10));
		orderPage = addToCartPage.clickOnGoToBasket();
		Log.info("Clicked on go to basket button.");

		// Thread.sleep(Duration.ofSeconds(10));

		// verifyOrderPageUrl
		String actualOrderURL = orderPage.getCurrURL();
		// after AddToCart expected url
		String expectedOrderURL = "https://integ.globetrotter.de/basket/";
		Log.info("Navigated to Actual URL: " + actualOrderURL);

		Assert.assertEquals(actualOrderURL, expectedOrderURL);

		orderPage.enterQuantity(String.valueOf((int) Double.parseDouble(qty)));
		Log.info("Updated quantity.");
		Thread.sleep(Duration.ofSeconds(5));

		// Verify selected Size on orderPage div text xpath=
		// //font[contains(text(),'Details: ')]
		String sResult = orderPage.verifySelectedSize(size);
		Log.info("Selected Size : " + sResult);
		Double unitPrice = orderPage.getUnitPrice();
		Log.info("Unit Price " + unitPrice);
		Double totalPrice = orderPage.getTotalPrice();
		Log.info("Total Price " + totalPrice);
		// Need to check original formula -- Double
		// totalExpectedPrice=(unitPrice*(Double.parseDouble(qty)))+2;
		Double totalExpectedPrice = (unitPrice * (Double.parseDouble(qty))); // need to roundoff to 2 decimal points
																				// else assertion fails
		BigDecimal bd = BigDecimal.valueOf(totalExpectedPrice.doubleValue());
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		Log.info("Expected Total Price : " + bd.doubleValue());

		Assert.assertEquals(totalPrice, bd.doubleValue());

		orderPage.clickOnCheckOut();
		Log.info("Clicked on Checkout button.");
		Thread.sleep(Duration.ofSeconds(2));

		Log.endTestCase("verifyTotalPrice");
	}
}
