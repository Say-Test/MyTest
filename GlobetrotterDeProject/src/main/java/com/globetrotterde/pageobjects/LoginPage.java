package com.globetrotterde.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.globetrotterde.actiondriver.Action;
import com.globetrotterde.base.BaseClass;

/**
 * @Author Sayrina Lopes
 */

public class LoginPage extends BaseClass {
	
	Action action= new Action();
	
	//  id="customer_email"
	
	@FindBy(xpath = "//input[@id='customer_email']")
	private WebElement email;
	
	//  id="customer_password"
	
	@FindBy(xpath = "//input[@id='customer_password']")
	private WebElement password;
/*
	xpath ="//*[@id="main-navi-wrapper"]//font[contains(text(),'Register')]"
	or
	//*[@id="login-form"]/div[4]/button/font/font
	 or
	 xpath = "//button[@type="submit"]//font[contains(text(),'Register')]" 
	 
	           or className="btn primary lg js-login-event se-login-id js-spinner"
	           
	 */
	
	//Login - (wrong text) 
	@FindBy(xpath = "//button[@type='submit']//font[contains(text(),'Register')]")
	private WebElement loginBtn;
	
	/*
	 * @FindBy(name="email_create") 
	 * private WebElement emailForNewAccount;
	 */
	
	//Create Login - //a[@class='btn primary lg']//font[contains(text(),'Create login')]
	@FindBy(xpath="//a[@class='btn primary lg']//font[contains(text(),'Create login')]") 
	private WebElement createNewAccountBtn;
	
	
	public LoginPage() 
	{
		PageFactory.initElements(getDriver(), this);
	}
	
	public HomePage login(String uname, String pswd,HomePage homePage) throws Throwable {
		action.scrollByVisibilityOfElement(getDriver(), email);
		action.type(email, uname);
		action.type(password, pswd);
		action.JSClick(getDriver(), loginBtn);
		Thread.sleep(2000);
		homePage=new HomePage();
		return homePage;
	}
	
	public AddressPage login(String uname, String pswd,AddressPage addressPage) throws Throwable {
		action.scrollByVisibilityOfElement(getDriver(), email);
		action.type(email, uname);
		action.type(password, pswd);
		action.click(getDriver(), loginBtn);
		Thread.sleep(2000);
		addressPage=new AddressPage();
		return addressPage;
	}
	
	
	public AccountCreationPage createNewAccount() throws Throwable {
		//action.type(emailForNewAccount, newEmail);
		action.click(getDriver(), createNewAccountBtn);
		return new AccountCreationPage();
	}
	

}
