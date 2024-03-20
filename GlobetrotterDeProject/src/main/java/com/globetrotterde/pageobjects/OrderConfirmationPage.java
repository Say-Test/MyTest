package com.globetrotterde.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.globetrotterde.actiondriver.Action;
import com.globetrotterde.base.BaseClass;

/**
 * @Author Sayrina Lopes
 */

public class OrderConfirmationPage extends BaseClass {
	
	Action action= new Action();
	
	//English
	
	@FindBy(xpath="//font[contains(text(),'Thank you for your order!')]")
	private WebElement confirmMessag;
	
	//German
	@FindBy(xpath="//h1[contains(text(),'Danke für deine Bestellung!')]")
	private WebElement confirmMessag1;
	
	public OrderConfirmationPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	public String validateConfirmMessage() {
		String confirmMsg=confirmMessag.getText();
		return confirmMsg;
	}

}
