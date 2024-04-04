
package com.globetrotterde.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
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
	
	// XS size- id = "id_importdimgroesse_0"  xpath = "//input[@value='XS']"    //*[@id="id_importdimgroesse_0"]  or xpath="//*[@id=\"5637464199_import:dim_GROESSE_1\"]/label/font/font"
			@FindBy(xpath= "//input[@value='XS']")
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
	
		//In English 
	@FindBy(xpath="//font[contains(text(),'Add to Cart')]")
	private WebElement addToCartBtnEng;
	
	//German - add to cart
	@FindBy(xpath="//button[contains(text(),'In den Warenkorb')]")
	private WebElement addToCartBtn;
	
	
	//English
	@FindBy(xpath="//font[contains(text(),'The following item was added to the shopping cart.')]")
	private WebElement addToCartMessagEng;
	
	//German
	@FindBy(xpath="//div[contains(text(),'Folgender Artikel wurde in den Warenkorb gelegt.')]")
	private WebElement addToCartMessag;
	
	//English
	@FindBy(xpath="//font[contains(text(),'To the basket of gooods')]")
	private WebElement proceedToBasketBtnEng;
	
	//German
	@FindBy(xpath="//a[contains(text(),'Zum Warenkorb')]")
	private WebElement proceedToBasketBtn;
	
	public AddToCartPage() {
		PageFactory.initElements(getDriver(), this);
	}

//	public void enterQuantity(String quantity1) throws Throwable {
//		action.type(quantity, quantity1);
//	}
	
	public void clickOnSize(String size1) throws Throwable {
//		 String sizexpath = "//input[@value='" + size1 + "']"; 
//		 @FindBy(xpath=sizexpath)
//		 private WebElement pSize;
		List<WebElement> list = getDriver().findElements(By.cssSelector("#article-form > div.size-variations.js-product-option.product-option.clearfix > div.content.clearfix > div > div.size-menu > ul > li > input "));
		
		for (int i = 0; i < list.size(); i++)
		{
			System.out.println("Size list count : " +list.size());
		System.out.println(list.get(i).getAttribute("value"));
		    
		if(list.size()==1)
		{
			System.out.println("Clicked on Size : " +list.get(i).getAttribute("value"));
		action.click(getDriver(), list.get(i));
		
		//Thread.sleep(20);
	    }
		else if(list.get(i).getAttribute("value").equalsIgnoreCase(size1))
		{
			
		action.click(getDriver(), list.get(i));
			//Thread.sleep(20);
		}
		else 
		{
			System.out.println("No Relevant element present to click");
		}
		}
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
	
	public OrderPage clickOnGoToBasket() throws Throwable {
//		action.fluentWait(getDriver(), proceedToBasketBtn, 10);
//		action.JSClick(getDriver(), proceedToBasketBtn);
		action.click(getDriver(), proceedToBasketBtn);
		return new OrderPage();
	}
	
}
