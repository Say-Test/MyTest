package com.globetrotterde.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.globetrotterde.base.BaseClass;
import com.globetrotterde.actiondriver.Action;

/**
 * @Author Sayrina Lopes
 */

public class WebIndexPage extends BaseClass
{
    Action action= new Action();
	
    //German
	@FindBy(xpath = "//div[contains(text(),'Anmelden')]") 
	private WebElement loginOpt;
	
	@FindBy(xpath = "//header/div[6]/div[1]/a[1]/*[1]")
	private WebElement globetrotterLogo;
	
	@FindBy(id="search-focus")
	// xpath = "//input[@id='search-focus']"
	private WebElement searchProductBox;
	
	//default disabled, on entering text gets enabled 
	@FindBy(className="search-input-submit")
	private WebElement searchButton;
	
	//click on Children German language tab
	@FindBy(xpath = "//span[text()='Kinder']")
	private WebElement searchByKidsTab;
	
		//click on Men's German language tab
		@FindBy(xpath = "//span[text()='Herren']")
		private WebElement searchByMensTab;
		
		//click on Ladies English language tab
				@FindBy(xpath = "//font[text()='Ladies']")
				private WebElement searchByLadiesTab;	
	
	//Constructor
	public WebIndexPage() 
	{
		PageFactory.initElements(getDriver(), this);
	}
	
	public LoginPage clickOnLogin() throws Throwable {
		action.fluentWait(getDriver(), loginOpt, 10);
		action.click(getDriver(), loginOpt);
		return new LoginPage();
	}
	
	public boolean validateLogo() throws Throwable {
		return action.isDisplayed(getDriver(), globetrotterLogo);
	}
	
	public String getGlobetrotterTitle() {
		String myStoreTitle=getDriver().getTitle();
		return myStoreTitle;
	}
	
	//method's return type is SearchResultPage
	public SearchResultPage searchProduct(String productName) throws Throwable 
	{
		action.type(searchProductBox, productName);
		action.scrollByVisibilityOfElement(getDriver(), searchButton);
		action.click(getDriver(), searchButton);
		Thread.sleep(3000);
		return new SearchResultPage();
	}
	
}
