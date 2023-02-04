package com.amitlearning.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class P5_SearchPageObject {

	WebDriver driver;

	public P5_SearchPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
//	========================================================================
	@FindBy(xpath = "//div[@class='product-thumb']//h4")
	private WebElement validProductSearchedName;
	
	public String retrieveSearchedProductName() {
		String searchedProductName = validProductSearchedName.getText();
		return searchedProductName;		
	}
//	========================================================================
	@FindBy(xpath = "//div[@id='content']/h2/following-sibling::p")
	private WebElement invalidProductSearchedMessage;
	
	public String retrieveInvalidProductSearchedMessage() {
		String invalidProductSearchMessage = invalidProductSearchedMessage.getText();
		return invalidProductSearchMessage;		
	}
//	========================================================================
	@FindBy(xpath = "//div[@id='content']/h2/following-sibling::p")
	private WebElement blankProductSearchMessage;
	
	public String retrieveBlankProductSearchMessage() {
		String blankSearchMessage = blankProductSearchMessage.getText();
		return blankSearchMessage;		
	}
//	========================================================================

}