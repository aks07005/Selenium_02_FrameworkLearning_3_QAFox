package com.amitlearning.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class P1_HomePageObject {

	WebDriver driver;

	public P1_HomePageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	-----------------------------------------------------
	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccountDropMenu;

	public void clickOnMyAccountDropMenu() {
		myAccountDropMenu.click();
	}

//	-----------------------------------------------------
	@FindBy(linkText = "Login")
	private WebElement loginOption;

	public P2_LoginPageObject selectLoginOption() {
		loginOption.click();
		return new P2_LoginPageObject(driver);
	}

//	-----------------------------------------------------
	public P2_LoginPageObject navigateToLoginPage() {
		myAccountDropMenu.click();
		loginOption.click();
		return new P2_LoginPageObject(driver);
	}

//	-----------------------------------------------------
	@FindBy(linkText = "Register")
	private WebElement registerOption;

	public P3_RegisterPageObject selectRegisterOption() {
		registerOption.click();
		return new P3_RegisterPageObject(driver);
	}

//	-----------------------------------------------------
	public P3_RegisterPageObject navigateToRegistrationPage() {
		myAccountDropMenu.click();
		registerOption.click();
		return new P3_RegisterPageObject(driver);
	}

//	-----------------------------------------------------
	@FindBy(css = "div.pull-left")
	private WebElement currencyDropMenu;

	public void clickOnCurrencyDropMenu() {
		currencyDropMenu.click();
	}

//	-----------------------------------------------------
	@FindBy(xpath = "//div[@class='pull-left']//ul")
	private List<WebElement> currencyListInCurrencyDropMenu;

	public void currencySelection(String currencyType) {
		for (WebElement element : currencyListInCurrencyDropMenu) {
			String elementValue = element.getText();
			if (elementValue.contains(currencyType)) {
				element.click();
			}
		}
	}

//	-----------------------------------------------------
	@FindBy(xpath = "//ul[@class='list-inline']/li[1]/a")
	private WebElement contactUs;

	public void clickOnContactUs() {
		contactUs.click();
	}

//	-----------------------------------------------------
	@FindBy(css = "a#wishlist-total")
	private WebElement wishlist;

	public void clickWishList() {
		wishlist.click();
	}

//	-----------------------------------------------------
	@FindBy(xpath = "//span[text()='Shopping Cart']")
	private WebElement shoppingCart;

	public void clickOnShoppingCart() {
		shoppingCart.click();
	}

//	========================================================================
	@FindBy(css = "input.form-control.input-lg")
	private WebElement searchBoxField;

	// div[@id='search']/descendant::button
	public void sendValueIntoSearchBar(String validProductName) {
		searchBoxField.sendKeys(validProductName);
	}

//	========================================================================
	@FindBy(xpath = "//div[@id='search']/descendant::button")
	private WebElement searchButton;

	public P5_SearchPageObject clickSearchButton() {
		searchButton.click();
		return new P5_SearchPageObject(driver);
	}
//	========================================================================
	public P5_SearchPageObject searchForAProduct(String productName) {
		searchBoxField.sendKeys(productName);
		searchButton.click();
		return new P5_SearchPageObject(driver);
	}

}