package com.globetrotterde.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.globetrotterde.actiondriver.Action;
import com.globetrotterde.base.BaseClass;

/**
 * @Author Sayrina Lopes
 */

public class OrderPage extends BaseClass {
	Action action = new Action();
	
	
//	qty xpath = //*[@class="text count js-position-count se-quantity-index0"]
	//xpath = //input[@class="text count js-position-count se-quantity-index0"]
	
	@FindBy(xpath="//input[@class='text count js-position-count se-quantity-index0']")
    private WebElement quantity;
	
    //Verify selected Size on orderPage div text xpath= //font[contains(text(),'Details: ')]
	@FindBy(xpath="//div[@class='table-content product-position clearfix se-basket-price-product-entry ']//font[contains(text(),'Details: ')] ")
	private WebElement selectedSize;
	
	@FindBy(xpath="//span[@class='price small-price se-basket-price-single-item']")
	private WebElement unitPrice;
	
	@FindBy(xpath="//span[@class='price small-price se-basket-price-summarized-article']")
	private WebElement totalPrice;
	
	@FindBy(xpath="//*[@id=\"cart-sum-wrap\"]//font[contains(text(),'Proceed to checkout')]")
	private WebElement proceedToCheckOut;
	
	public OrderPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	public void enterQuantity(String quantity1) throws Throwable {
		action.type(quantity, quantity1);
	}

	public double getUnitPrice() {
		String unitPrice1=unitPrice.getText();
		String unit=unitPrice1.replaceAll("[^a-zA-Z0-9]","");
		double finalUnitPrice=Double.parseDouble(unit);
		return finalUnitPrice/100;
	}
	
	public double getTotalPrice() {
		String totalPrice1=totalPrice.getText();
		String tot=totalPrice1.replaceAll("[^a-zA-Z0-9]","");
		double finalTotalPrice=Double.parseDouble(tot);
		return finalTotalPrice/100;
	}
	
	public LoginPage clickOnCheckOut() throws Throwable {
		action.click(getDriver(), proceedToCheckOut);
		return new LoginPage();
	}
	
	public String getCurrURL() throws Throwable {
		String orderPageURL=action.getCurrentURL(getDriver());
		return orderPageURL;
	}
	
	public String verifySelectedSize(String size) throws Throwable {
		String orderPageSize=selectedSize.getText();
		if(orderPageSize.contains(size))
				{
			System.out.println("Selected Size is matched with given Size : "+ orderPageSize);
				}
			    // Then Second Element that contains the Text Hello is present.

			else 
			{			System.out.println("Selected Size is not matched with given Size : "+ orderPageSize);
}

		return orderPageSize;
	}

}
