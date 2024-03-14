
package com.globetrotterde.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.globetrotterde.actiondriver.Action;
import com.globetrotterde.base.BaseClass;

/**
 * @Author Sayrina Lopes
 */


public class AccountCreationPage extends BaseClass {
	
	Action action= new Action();
	
	//Validate account creation page
	@FindBy(xpath = "//font[contains(text(),'Registration')]")
	private WebElement formBreadcrumb;
	
	@FindBy(xpath = "//*[@id='page-wrapper']/div/div[1]/div[2]/h1/font/font")
	private WebElement formCreateLogin;
	
	//Create account Form fields
	// xpath="//*[@id="email"]"
	@FindBy(xpath = "/html[1]/body[1]/div[4]/div[2]/div[1]/div[1]/div[3]/form[1]/div[1]/input")
	private WebElement custEmail;

	// xpath = "//*[@id="userData.password"]"
	@FindBy(id = "userData.password")
	private WebElement passWord;
	
	//send button
	@FindBy(xpath = "//*[@id=\"page-wrapper\"]/div/div[1]/div[5]/button/font/font")
	private WebElement registerSendBtn;
	
	public AccountCreationPage() 
	{
		PageFactory.initElements(getDriver(), this);
	}

	public void createAccount(String email,String pswd) throws Throwable 
	{
		action.type(custEmail, email);
		action.type(passWord, pswd);
	}
	
	public HomePage validateRegistration() throws Throwable 
	{
		registerSendBtn.click();
		return new HomePage();
	}
	
	public boolean validateAccountCreatePage() throws Throwable 
	{
		 return action.isDisplayed(getDriver(), formBreadcrumb);
	}
	
}
