
package com.globetrotterde.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.globetrotterde.actiondriver.Action;
import com.globetrotterde.base.BaseClass;

/**
 * @Author Sayrina Lopes
 */

public class AddToCartPage extends BaseClass {
	
	Action action= new Action();
	

//	@FindBy(id="quantity_wanted")
//	private WebElement quantity;
	
	// XS size- xpath = "//input[@value='XS']"    //*[@id="id_importdimgroesse_0"]  or xpath="//*[@id=\"5637464199_import:dim_GROESSE_1\"]/label/font/font"
			@FindBy(id="id_importdimgroesse_0")
			private WebElement xsSize;
	
	// S size   //*[@id="id_importdimgroesse_1"]
		@FindBy(xpath="//input[@value='S']")
		private WebElement sSize;
		
		// M size  //*[@id="id_importdimgroesse_2"]
		@FindBy(xpath="//input[@value='M']")
		private WebElement mSize;
	
	// L size  //*[@id="id_importdimgroesse_3"]
	@FindBy(xpath="//input[@value='L']")
	private WebElement lSize;
	
	// XL size  //*[@id="id_importdimgroesse_4"]
	@FindBy(xpath="//input[@value='XL']")
	private WebElement xlSize;
	
	// XXL size    //*[@id="id_importdimgroesse_5"]
		@FindBy(xpath="//input[@value='XXL']")
		private WebElement xxlSize;
	
	@FindBy(xpath="//font[contains(text(),'Add to Cart')]")
	private WebElement addToCartBtn;
	
	@FindBy(xpath="//font[contains(text(),'The following item was added to the shopping cart.')]")
	private WebElement addToCartMessag;
	
	@FindBy(xpath="//font[contains(text(),'To the basket of gooods')]")
	private WebElement proceedToCheckOutBtn;
	
	public AddToCartPage() {
		PageFactory.initElements(getDriver(), this);
	}

//	public void enterQuantity(String quantity1) throws Throwable {
//		action.type(quantity, quantity1);
//	}
	
	public void clickOnSize() throws Throwable {
		
		action.click(getDriver(), lSize);
	}
	
//	String searchText = "AppraisersGroupTest";
//	WebElement dropdown = driver.findElement(By.id("grdAvailableGroups"));
//	dropdown.click(); // assuming you have to click the "dropdown" to open it
//	List<WebElement> options = dropdown.findElements(By.tagName("li"));
//	for (WebElement option : options)
//	{
//	    if (option.getText().equals(searchText))
//	    {
//	        option.click(); // click the desired option
//	        break;
//	    }
//	}
	
	
//	Method2 -
//	public static void selectRadioButtonByValue(List<WebElement> radioButtons, String value) {
//	    for (WebElement radioButton: radioButtons) {
//	        if (radioButton.getAttribute("value").toLowerCase().equals(value.toLowerCase())) {
//	            radioButton.click();
//	            break;
//	        }
//	    }
//	}
	
	
	public void clickOnAddToCart() throws Throwable {
		action.click(getDriver(), addToCartBtn);
	}
	
	public boolean validateAddtoCart() throws Throwable {
		action.fluentWait(getDriver(), addToCartMessag, 10);
		return action.isDisplayed(getDriver(), addToCartMessag);
	}
	
	public OrderPage clickOnCheckOut() throws Throwable {
		action.fluentWait(getDriver(), proceedToCheckOutBtn, 10);
		action.JSClick(getDriver(), proceedToCheckOutBtn);
		return new OrderPage();
	}
	
}
