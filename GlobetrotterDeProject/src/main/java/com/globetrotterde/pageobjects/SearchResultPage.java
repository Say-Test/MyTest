package com.globetrotterde.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.globetrotterde.actiondriver.Action;
import com.globetrotterde.base.BaseClass;

/**
 * @Author Sayrina Lopes
 */

public class SearchResultPage extends BaseClass {
	
	Action action= new Action();
	
	@FindBy(xpath="//h1[contains(text(),'T-Shirts')]")
	private WebElement productResultTitle;
	
//	@FindBy(xpath="//font[contains(text(),'Shirts & Tops')]")
//	private WebElement productResultTitle;
	
	 // xpath ="//*[@id="product box container"]//img"
	//  //*[@id="product box container"]//a/div[3] 
	@FindBy(xpath="//*[@id=\"product box container\"]//a/div[1]/img")
	private WebElement productResult;
	
	public SearchResultPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	public boolean validateSearchResultPage() throws Throwable 
	{
		 return action.isDisplayed(getDriver(), productResultTitle);
	}
	
	public boolean isProductAvailable() throws Throwable {
		return action.isDisplayed(getDriver(), productResult);
	}
	
	public AddToCartPage clickOnProduct() throws Throwable {
		action.click(getDriver(), productResult);
		return new AddToCartPage();
	}
}
