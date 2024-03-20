
package com.globetrotterde.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.globetrotterde.actiondriver.Action;
import com.globetrotterde.base.BaseClass;

/**
 * @Author Sayrina Lopes
 */

public class AddressPage extends BaseClass {
	
	Action action= new Action();
	
	/*
	 * @FindBy(xpath="//span[text()='Proceed to checkout']") 
	 * private WebElement proceedToCheckOut;
	 */
	
	  @FindBy(xpath="//input[@id='billingAddressViewModel.invoiceAddress.phoneNumber']") 
	  private WebElement phoneNumber;
	  
	
	  @FindBy(xpath="//input[@id='radio-bezahlung-kreditkarte']") 
	  private WebElement creditCard;
	  
	  @FindBy(xpath="//input[@id='radio-bezahlung-sofortueberweisung']") 
	  private WebElement instantBankTransfer;
	  
	  @FindBy(xpath="//input[@id='radio-bezahlung-paypal']") 
	  private WebElement payPal;
	  
	  @FindBy(xpath="//input[@id='radio-bezahlung-vorauskasse']") 
	  private WebElement payInAdvance;
	
	public AddressPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	// "https://www.globetrotter.de/checkout/mainCheckout/"

	/*
	 * public ShippingPage clickOnCheckOut() throws Throwable {
	 * action.click(getDriver(), proceedToCheckOut); return new ShippingPage(); }
	 */
	
	public void updatePhoneNumber(String phNumber) throws Throwable {
		action.type(phoneNumber, phNumber);
		
	}
	
	
	public PaymentPage clickOnPaymentMethod() throws Throwable {
		action.click(getDriver(), creditCard);
		return new PaymentPage();
	}
	
}
