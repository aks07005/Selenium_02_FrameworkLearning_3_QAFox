package com.amitlearning.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class P3_RegisterPageObject {

	WebDriver driver;

	public P3_RegisterPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	========================================================================
	@FindBy(id = "input-firstname")
	private WebElement firstNameField;

	public void enterFirstName(String firstNameText) {
		firstNameField.sendKeys(firstNameText);
	}

//	========================================================================
	@FindBy(id = "input-lastname")
	private WebElement lastNameField;

	public void enterLastName(String lastNameText) {
		lastNameField.sendKeys(lastNameText);
	}

//	========================================================================
	@FindBy(id = "input-email")
	private WebElement emailField;

	public void enterEmail(String emailText) {
		emailField.sendKeys(emailText);
	}

//	========================================================================
	@FindBy(id = "input-telephone")
	private WebElement telephoneField;

	public void enterTelephone(String telephoneText) {
		telephoneField.sendKeys(telephoneText);
	}

//	========================================================================
	@FindBy(id = "input-password")
	private WebElement passwordField;

	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);
	}

//	========================================================================
	@FindBy(id = "input-confirm")
	private WebElement confirmPasswordField;

	public void enterConfirmPassword(String confirmPasswordText) {
		confirmPasswordField.sendKeys(confirmPasswordText);
	}

//	========================================================================
	@FindBy(name = "agree")
	private WebElement selectPrivacyPolicy;

	public void selectPrivacyPolicyCheckbox() {
		selectPrivacyPolicy.click();
	}

//	========================================================================
	@FindBy(css = "input.btn-primary")
	private WebElement continueButton;

	public P6_AccountSuccessPage clickOnContinueButton() {
		continueButton.click();
		return new P6_AccountSuccessPage(driver);
	}

//	========================================================================
	@FindBy(xpath = "//input[@name='newsletter'][@value='1']")
	private WebElement subscribeYes;

	public void selectYesForSubscription() {
		subscribeYes.click();
	}

//	========================================================================
	@FindBy(xpath = "//div[contains(@class,'alert-danger')]")
	private WebElement alreadyRegisteredWarningMessage;

	public String retrieveAlreadyRegisteredWarningMessage() {
		String displayedWarningMessage = alreadyRegisteredWarningMessage.getText();
		return displayedWarningMessage;
	}

//	========================================================================
	@FindBy(xpath = "//div[contains(@class,'alert-danger')]")
	private WebElement privacyPolicyWarningMessage;

	public String retrievePrivacyPolicyWarningMessage() {
		String displayedWarningMessage = privacyPolicyWarningMessage.getText();
		return displayedWarningMessage;
	}

//	========================================================================
	@FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarningMessage;

	public String retrieveFirstNameWarningMessage() {
		String displayedWarningMessage = firstNameWarningMessage.getText();
		return displayedWarningMessage;
	}

//	========================================================================
	@FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarningMessage;

	public String retrieveLastNameWarningMessage() {
		String displayedWarningMessage = lastNameWarningMessage.getText();
		return displayedWarningMessage;
	}

//	========================================================================
	@FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
	private WebElement invalidEmailWarningMessage;

	public String retrieveInvalidEmailWarningMessage() {
		String displayedWarningMessage = invalidEmailWarningMessage.getText();
		return displayedWarningMessage;
	}

//	========================================================================
	@FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarningMessage;

	public String retrieveTelephoneWarningMessage() {
		String displayedWarningMessage = telephoneWarningMessage.getText();
		return displayedWarningMessage;
	}

//	========================================================================
	@FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarningMessage;

	public String retrievePasswordWarningMessage() {
		String displayedWarningMessage = passwordWarningMessage.getText();
		return displayedWarningMessage;
	}

//	========================================================================
	public P6_AccountSuccessPage registerWith_MandatoryFields(String firstNameText, String lastNameText,
			String emailText, String telephoneText, String passwordText) {
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastNameText);
		emailField.sendKeys(emailText);
		telephoneField.sendKeys(telephoneText);
		passwordField.sendKeys(passwordText);
		confirmPasswordField.sendKeys(passwordText);
		selectPrivacyPolicy.click();
		continueButton.click();
		return new P6_AccountSuccessPage(driver);

	}

//	========================================================================
	public P6_AccountSuccessPage registerWith_AllFields(String firstNameText, String lastNameText, String emailText,
			String telephoneText, String passwordText) {
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastNameText);
		emailField.sendKeys(emailText);
		telephoneField.sendKeys(telephoneText);
		passwordField.sendKeys(passwordText);
		confirmPasswordField.sendKeys(passwordText);
		subscribeYes.click();
		selectPrivacyPolicy.click();
		continueButton.click();
		return new P6_AccountSuccessPage(driver);

	}

//	========================================================================
	public boolean displayStatusOfWarningMessages(String expectedPrivacyWarningMessage,
			String expectedFirstNameWarningMessage, String expectedLastNameWarningMessage,
			String expectedInvalidEmailWarningMessage, String expectedTelephoneWarningMessage,
			String expectedPasswordWarningMessage) {

		boolean privacyPolicyWarningMessageStatus = privacyPolicyWarningMessage.getText()
				.equals(expectedPrivacyWarningMessage);

		boolean firstNameWarningMessageStatus = firstNameWarningMessage.getText()
				.equals(expectedFirstNameWarningMessage);
		boolean lastNameWarningMessageStatus = lastNameWarningMessage.getText().equals(expectedLastNameWarningMessage);
		boolean emailWarningMessageStatus = invalidEmailWarningMessage.getText()
				.equals(expectedInvalidEmailWarningMessage);
		boolean telephoneWarningMessageStatus = telephoneWarningMessage.getText()
				.equals(expectedTelephoneWarningMessage);
		boolean passwordWarningMessageStatus = passwordWarningMessage.getText().equals(expectedPasswordWarningMessage);

		return privacyPolicyWarningMessageStatus && firstNameWarningMessageStatus && lastNameWarningMessageStatus
				&& emailWarningMessageStatus && telephoneWarningMessageStatus && passwordWarningMessageStatus;

	}

}