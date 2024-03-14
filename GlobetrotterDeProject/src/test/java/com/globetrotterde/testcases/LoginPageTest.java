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

public class LoginPageTest extends BaseClass {
	private WebIndexPage indexPage;
	private LoginPage loginPage;
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
	@Test(groups = {"Smoke","Sanity"},dataProvider = "credentials", dataProviderClass = DataProviders.class)
	public void loginTest(String uname, String pswd) throws Throwable {
		Log.startTestCase("loginTest");
		indexPage= new WebIndexPage();
		Log.info("user is going to click on Login");
		loginPage=indexPage.clickOnLogin();
		Log.info("Enter Username and Password");
	    //homePage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		homePage=loginPage.login(uname,pswd,homePage);
	    String actualURL=homePage.getCurrURL();
	    //after successful login expected url
	    String expectedURL="https://integ.globetrotter.de/login-success/";
	    Log.info("Verifying if user is able to login");
	    Assert.assertEquals(actualURL, expectedURL);
	    Log.info("Login is Success");
	    Log.endTestCase("loginTest");
	}
}
