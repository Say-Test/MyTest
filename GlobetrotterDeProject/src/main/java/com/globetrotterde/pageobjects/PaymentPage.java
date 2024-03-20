
package com.globetrotterde.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.globetrotterde.actiondriver.Action;
import com.globetrotterde.base.BaseClass;

/**
 * @Author Sayrina Lopes
 */

public class PaymentPage extends BaseClass {
	
	Action action= new Action();
	
	/*
	 * @FindBy(xpath = "//a[contains(text(),'Pay by bank wire')]") 
	 * private WebElement bankWireOption;
	 * 
	 * @FindBy(xpath = "//a[contains(text(),'Pay by check')]") 
	 * private WebElement payByCheckOption;
	 * 
	 */
	@FindBy(xpath = "//input[@name='ccname']") 
	private WebElement creditCardHolderName;
	
	@FindBy(xpath = "//input[@name='cardnumber']") 
	private WebElement creditCardNumber;
	
	@FindBy(xpath = "//input[@name='cvc']") 
	private WebElement creditCVC;
	
	@FindBy(xpath = "//select[@name='ccmonth']") 
	private WebElement creditExpiryMonth;
	
	@FindBy(xpath = "//select[@name='ccyear']") 
	private WebElement creditExpiryYear;
	
	
	
	public PaymentPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	public OrderSummary giveCCDetails(String cname,String cnumber, String cvc, String month, String year) throws Throwable 
	{
		action.type(creditCardHolderName, cname);
		action.type(creditCardNumber, cnumber);
		action.type(creditCVC, cvc);
		action.selectByValue(creditExpiryMonth, month);
		action.selectByValue(creditExpiryYear, year);
		
		return new  OrderSummary();
	}
	
	/*
	 * public OrderSummary clickOnPaymentMethod() throws Throwable {
	 * action.click(getDriver(), bankWireOption); return new OrderSummary(); }
	 */

}
