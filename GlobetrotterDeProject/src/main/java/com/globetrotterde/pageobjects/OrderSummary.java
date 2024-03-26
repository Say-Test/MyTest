
package com.globetrotterde.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.globetrotterde.actiondriver.Action;
import com.globetrotterde.base.BaseClass;

/**
 * @Author Sayrina Lopes
 */

public class OrderSummary extends BaseClass {
	
	Action action= new Action();
	
	/*
	 * @FindBy(xpath="//span[contains(text(),'I confirm my order')]") private
	 * WebElement confirmOrderBtn;
	 */
	
	//English
	@FindBy(xpath="//font[contains(text(),'Buy now')]")
	private WebElement buyNowBtnEng;
	
	//German //button[@id="checkoutSubmitButton"] div text 'Jetzt kaufen'
	@FindBy(xpath="//button[@id='checkoutSubmitButton']")
	private WebElement buyNowBtn;
	
	public OrderSummary() {
		PageFactory.initElements(getDriver(), this);
	}

	/*
	 * public OrderConfirmationPage clickOnconfirmOrderBtn() throws Throwable {
	 * action.click(getDriver(), confirmOrderBtn); return new
	 * OrderConfirmationPage(); }
	 */
	public OrderConfirmationPage clickOnbuyNowBtn() throws Throwable {
	action.click(getDriver(), buyNowBtn); 
	return new OrderConfirmationPage(); 
	}
	
}
