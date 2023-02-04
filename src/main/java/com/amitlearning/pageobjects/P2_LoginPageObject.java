package com.amitlearning.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class P2_LoginPageObject {

	WebDriver driver;

	public P2_LoginPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	========================================================================
	@FindBy(id = "input-email")
	private WebElement emailAddressField;

	public void enterEmailAddress(String emailText) {
		emailAddressField.sendKeys(emailText);
	}
//	========================================================================

	@FindBy(id = "input-password")
	private WebElement passwordField;

	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);
	}
//	========================================================================

	@FindBy(xpath = "//input[@value='Login']")
	private WebElement loginButton;

	public P4_AccountPageObject loginButtonClick() {
		loginButton.click();
		return new P4_AccountPageObject(driver);
	}

//	========================================================================
	public P4_AccountPageObject login(String emailText, String passwordText) {
		emailAddressField.sendKeys(emailText);
		passwordField.sendKeys(passwordText);
		loginButton.click();
		return new P4_AccountPageObject(driver);
	}

//	========================================================================
	@FindBy(xpath = "//div[contains(@class,'alert-danger')]")
	private WebElement warningDisplayMessage;

	public String captureWarningMessage() {
		String displayedWarningMessage = warningDisplayMessage.getText();
		return displayedWarningMessage;
	}
//	========================================================================
	
//	========================================================================

}