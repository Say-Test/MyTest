package com.globetrotterde.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.globetrotterde.base.BaseClass;
import com.globetrotterde.pageobjects.WebIndexPage;
import com.globetrotterde.utility.Log;

/**
 * @Author Sayrina Lopes
 */
public class WebIndexPageTest extends BaseClass {
	private WebIndexPage indexPage;

@Parameters({"browser"})
@BeforeMethod(groups = {"Smoke","Sanity","Regression"})
public void setup(String browser) {
	launchApp(browser); 
}

@AfterMethod(groups = {"Smoke","Sanity","Regression"})
public void tearDown() {
	getDriver().quit();
}

@Test(groups = "Smoke")
public void verifyLogo() throws Throwable {
	Log.startTestCase("verifyLogo");
	indexPage= new WebIndexPage();
	boolean result=indexPage.validateLogo();
	Assert.assertTrue(result);
	Log.endTestCase("verifyLogo");
}

@Test(groups = "Smoke")
public void verifyTitle() {
	Log.startTestCase("verifyTitle");
	String actTitle=indexPage.getGlobetrotterTitle();
	// PageTitle - German 
	Assert.assertEquals(actTitle, "Outdoor-Ausrüstung online kaufen | Globetrotter");
	
	Log.endTestCase("verifyTitle");
}


}
