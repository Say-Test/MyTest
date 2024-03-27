
package com.globetrotterde.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.globetrotterde.actiondriver.Action;
import com.globetrotterde.base.BaseClass;

/**
 * @Author Sayrina Lopes
 */

public class HomePage extends BaseClass {
	
	Action action= new Action();
	
	//only heart icon 
	@FindBy(xpath = "//header/div[6]/div[1]/div[1]/div[3]/a[1]/div[1]/*[1]")
	private WebElement myWishList;
	
	//German order History
	@FindBy(xpath = "//a[contains(text(),'Bestellungen/')]")
	private WebElement orderHistory;
	//English order History
	@FindBy(xpath = "//font[contains(text(),'Orders/')]")
	private WebElement orderHistoryEng;
	
	
	public HomePage() {
		PageFactory.initElements(getDriver(), this);
	}

	
	public boolean validateMyWishList() throws Throwable {
		return action.isDisplayed(getDriver(), myWishList);
	}
	
	public void clickOnMyWishList() throws Throwable {
		action.click(getDriver(), myWishList);
		
	}
	
	public boolean validateOrderHistory() throws Throwable {
		return action.isDisplayed(getDriver(), orderHistory);
	}
	
	public void clickOnOrderHistoryDet() throws Throwable {
		action.click(getDriver(), orderHistory);
		
	}
	
	public String getCurrURL() throws Throwable {
		String homePageURL=action.getCurrentURL(getDriver());
		return homePageURL;
	}
}
