package com.globetrotterde.testcases;

import java.util.HashMap;

//import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.globetrotterde.base.BaseClass;
import com.globetrotterde.dataprovider.DataProviders;

//import com.globetrotterde.pageobjects.AddressPage;
import com.globetrotterde.pageobjects.PaymentPage;
//import com.globetrotterde.pageobjects.AccountCreationPage;
//import com.globetrotterde.pageobjects.HomePage;
//import com.globetrotterde.pageobjects.LoginPage;
//import com.globetrotterde.pageobjects.WebIndexPage;
import com.globetrotterde.utility.Log;

public class PaymentPageTest  extends BaseClass {
//	private WebIndexPage indexPage;
//	private LoginPage loginPage;
//	private AccountCreationPage accountCreationPage;
	
	//private AddressPage addressPage;
	private PaymentPage paymentPage;
	
	@Parameters("browser")
	@BeforeMethod(groups = {"Smoke","Sanity","Regression"})
	public void setup(String browser) {
		launchApp(browser); 
	}
	
	@AfterMethod(groups = {"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}
	
	/*
	 * @Test(groups = "Regression", dataProviderClass = DataProviders.class) public
	 * void verifyCreateAccountPageTest() throws Throwable {
	 * Log.startTestCase("verifyCreateAccountPageTest"); indexPage= new
	 * WebIndexPage(); loginPage=indexPage.clickOnLogin();
	 * accountCreationPage=loginPage.createNewAccount(); boolean
	 * result=accountCreationPage.validateAccountCreatePage();
	 * Assert.assertTrue(result); Log.endTestCase("verifyCreateAccountPageTest"); }
	 */
	
	@Test(groups = "Regression",dataProvider = "payCreditCardDetailsData",dataProviderClass = DataProviders.class)
	public void giveCCDetailsTest(HashMap<String,String> hashMapValue) throws Throwable {
		Log.startTestCase("giveCCPaymentDetailsTest");
//		indexPage= new WebIndexPage();
//		loginPage=indexPage.clickOnLogin();
//		accountCreationPage=loginPage.createNewAccount();
		paymentPage.giveCCDetails(hashMapValue.get("CCHolderName"),
			    hashMapValue.get("CCNumber"), 
			    hashMapValue.get("CVC"), 
			    hashMapValue.get("CCExpiryMonth"),
			    hashMapValue.get("CCExpiryYear"));
		
		//homePage=accountCreationPage.validateRegistration();
		//Assert.assertEquals("http://automationpractice.com/index.php?controller=my-account", homePage.getCurrURL());
		Log.endTestCase("giveCCPaymentDetailsTest");
	}

}
