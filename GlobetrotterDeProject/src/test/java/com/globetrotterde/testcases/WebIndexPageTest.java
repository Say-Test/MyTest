package com.globetrotterde.testcases;

//import java.time.Duration;

//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
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
//public void acceptCookie() throws InterruptedException
//{
//	Thread.sleep(Duration.ofMinutes(1));
//	WebElement acceptCookies=  (WebElement) getDriver().findElement(By.xpath("//div[@id='cmpwrapper']")).getShadowRoot()
//            .findElement(By.cssSelector("#cmpbntyestxt"));
//acceptCookies.click();
//}

  @AfterMethod(groups = {"Smoke","Sanity","Regression"}) 
  public void tearDown()
  { getDriver().quit(); }
 

@Test(groups = "Smoke")
public void verifyLogo() throws Throwable {
	Log.info("Accepted All Cookies");
	Log.startTestCase("verifyLogo");
	indexPage= new WebIndexPage();
	boolean result=indexPage.validateLogo();
	Assert.assertTrue(result);
	Log.info("IsLogoValidated? "+result);
	Log.endTestCase("verifyLogo");
}

@Test(groups = "Smoke")
public void verifyTitle() {
	Log.info("Accepted All Cookies");
	Log.startTestCase("verifyTitle");
	String actTitle=indexPage.getGlobetrotterTitle();
	// PageTitle - German 
	Assert.assertEquals(actTitle, "Outdoor-Ausrüstung online kaufen | Globetrotter");
	Log.info("Title is : "+actTitle);
	Log.endTestCase("verifyTitle");
}


}
