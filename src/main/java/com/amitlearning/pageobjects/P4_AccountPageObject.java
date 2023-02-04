package com.amitlearning.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class P4_AccountPageObject {

	WebDriver driver;

	public P4_AccountPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	--------------------------------------------
	@FindBy(linkText = "Change your password")
	private WebElement changeYourPasswordLink;

	public boolean getDisplayStatusOfChangeYourPasswordLink() {
		boolean displayStatus = changeYourPasswordLink.isDisplayed();
		return displayStatus;
	}
//	--------------------------------------------

//	--------------------------------------------

//	--------------------------------------------

}