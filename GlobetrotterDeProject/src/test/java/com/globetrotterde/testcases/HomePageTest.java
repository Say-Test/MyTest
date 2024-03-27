
package com.globetrotterde.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.globetrotterde.base.BaseClass;
import com.globetrotterde.dataprovider.DataProviders;
import com.globetrotterde.pageobjects.HomePage;
import com.globetrotterde.pageobjects.LoginPage;
import com.globetrotterde.pageobjects.WebIndexPage;
import com.globetrotterde.utility.Log;

/**
 * @Author Sayrina Lopes
 */

public class HomePageTest extends BaseClass {
	private WebIndexPage indexPage;
	private LoginPage loginPage;
	private HomePage homePage;

	@Parameters("browser")
	@BeforeMethod(groups = {"Smoke","Sanity","Regression"})
	public void setup(String browser) {
		launchApp(browser); 
	}
	
	@AfterMethod(groups = {"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}
	
	@Test(groups = "Smoke",dataProvider = "credentials", dataProviderClass = DataProviders.class)
	public void wishListTest(String uname, String pswd) throws Throwable {
		Log.startTestCase("wishListTest");
		indexPage= new WebIndexPage();
		loginPage=indexPage.clickOnLogin();
		homePage=loginPage.login(uname,pswd,homePage);
		//since, login not navigating to homepage.
		getDriver().get("https://integ.globetrotter.de/wishlist/");
		boolean result=homePage.validateMyWishList();
		Log.info("Wish List is displayed? "+result);
		Assert.assertTrue(result);
		homePage.clickOnMyWishList();
		Log.info("Clicked on My Wish List.");
		Log.endTestCase("wishListTest");
	}
	
	@Test(groups = "Smoke",dataProvider = "credentials", dataProviderClass = DataProviders.class)
	public void orderHistoryandDetailsTest(String uname, String pswd) throws Throwable {
		Log.startTestCase("orderHistoryandDetailsTest");
		indexPage= new WebIndexPage();
		loginPage=indexPage.clickOnLogin();
		homePage=loginPage.login(uname,pswd,homePage);
		//since, login not navigating to homepage.
		getDriver().get("https://integ.globetrotter.de/wishlist/");
		boolean result=homePage.validateOrderHistory();
		Log.info("Order History is displayed? "+result);
		Assert.assertTrue(result);
		homePage.clickOnOrderHistoryDet();
		Log.info("Clicked on Order History Details.");
		Log.endTestCase("orderHistoryandDetailsTest");
	}
}
