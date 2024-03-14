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
	
	@FindBy(xpath="//font[contains(text(),'Shirts & Tops')]")
	private WebElement productResultTitle;
	
	 // xpath ="//*[@id="product box container"]//img"
	@FindBy(xpath="//*[@id=\"product box container\"]/div[4]/div[1]/div/div/div/div[1]/a/div[1]/img")
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
