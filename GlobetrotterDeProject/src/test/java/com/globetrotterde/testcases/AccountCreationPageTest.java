
package com.globetrotterde.testcases;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.globetrotterde.base.BaseClass;
import com.globetrotterde.dataprovider.DataProviders;
import com.globetrotterde.pageobjects.AccountCreationPage;
import com.globetrotterde.pageobjects.HomePage;
import com.globetrotterde.pageobjects.LoginPage;
import com.globetrotterde.pageobjects.WebIndexPage;
import com.globetrotterde.utility.Log;

/**
 * @Author Sayrina Lopes
 */
public class AccountCreationPageTest  extends BaseClass {
	private WebIndexPage indexPage;
	private LoginPage loginPage;
	private AccountCreationPage accountCreationPage;
	private HomePage homePage;
	
	@Parameters("browser")
	@BeforeMethod(groups = {"Smoke","Smoke_new","Sanity","Regression"})
	public void setup(String browser) {
		launchApp(browser); 
	}
	
	@AfterMethod(groups = {"Smoke","Smoke_new","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}
	
	@Test(groups = "Sanity", dataProviderClass = DataProviders.class)
	public void verifyCreateAccountPageTest() throws Throwable {
		Log.startTestCase("verifyCreateAccountPageTest");
		indexPage= new WebIndexPage();
		loginPage=indexPage.clickOnLogin();
		accountCreationPage=loginPage.createNewAccount();
		boolean result=accountCreationPage.validateAccountCreatePage();
		Assert.assertTrue(result);
		Log.endTestCase("verifyCreateAccountPageTest");
	}
	
	@Test(groups = "Regression",dataProvider = "newAcountDetailsData",dataProviderClass = DataProviders.class)
	public void createAccountTest(HashMap<String,String> hashMapValue) throws Throwable {
		Log.startTestCase("createAccountTest");
		indexPage= new WebIndexPage();
		loginPage=indexPage.clickOnLogin();
		accountCreationPage=loginPage.createNewAccount();
		accountCreationPage.createAccount(hashMapValue.get("Email"),
			    hashMapValue.get("SetPassword"));
		homePage=accountCreationPage.validateRegistration();
		Assert.assertEquals("http://automationpractice.com/index.php?controller=my-account", homePage.getCurrURL());
		Log.endTestCase("createAccountTest");
	}

}
