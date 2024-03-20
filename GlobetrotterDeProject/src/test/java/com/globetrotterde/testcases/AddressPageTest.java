package com.globetrotterde.testcases;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.globetrotterde.base.BaseClass;
import com.globetrotterde.dataprovider.DataProviders;
import com.globetrotterde.pageobjects.AddressPage;
import com.globetrotterde.pageobjects.PaymentPage;
import com.globetrotterde.pageobjects.WebIndexPage;
import com.globetrotterde.utility.Log;

public class AddressPageTest   extends BaseClass {
//	private WebIndexPage indexPage;
//	private LoginPage loginPage;
//	private AccountCreationPage accountCreationPage;
	
	private AddressPage addressPage;

	
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
	
	@Test(groups = "Regression",dataProvider = "editPhone", dataProviderClass = DataProviders.class)
	public void updatePhoneNumberTest(String PhoneNumber) throws Throwable {
		Log.startTestCase("update Billing Address Phone Number");
		addressPage.updatePhoneNumber(PhoneNumber);
		Log.endTestCase("update Billing Address Phone Number");
	
	}
}
