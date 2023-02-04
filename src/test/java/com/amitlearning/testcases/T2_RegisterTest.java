package com.amitlearning.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.amitlearning.pageobjects.P1_HomePageObject;
import com.amitlearning.pageobjects.P3_RegisterPageObject;
import com.amitlearning.pageobjects.P6_AccountSuccessPage;
import com.amitlearning.testcomponents.MyBaseTestQAFox;
import com.amitlearning.utils.Utilities;

public class T2_RegisterTest extends MyBaseTestQAFox {

	public WebDriver driver;

	P3_RegisterPageObject p3_RegisterPageObject;
	P6_AccountSuccessPage p6_AccountSuccessPage;

	public T2_RegisterTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplication(configProp.getProperty("browserName"));
		P1_HomePageObject p1_HomePageObject = new P1_HomePageObject(driver);
		p3_RegisterPageObject = p1_HomePageObject.navigateToRegistrationPage();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyRegisteringAnAccountWithMandatoryFields() {

		p6_AccountSuccessPage = p3_RegisterPageObject.registerWith_MandatoryFields(
				testDataProp.getProperty("firstName"), testDataProp.getProperty("lastName"),
				Utilities.generateEmailWithTimeStamp(), testDataProp.getProperty("telephone"),
				configProp.getProperty("validPassword"));
		Assert.assertEquals(p6_AccountSuccessPage.retrieveAccountCreationSuccessMessage(),
				testDataProp.getProperty("expectedSuccessHeading"), "Account Success Page is not displayed");
	}

	@Test(priority = 2)
	public void verifyRegisteringAccountByProvidingAllFields() {

		p6_AccountSuccessPage = p3_RegisterPageObject.registerWith_AllFields(testDataProp.getProperty("firstName"),
				testDataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(),
				testDataProp.getProperty("telephone"), configProp.getProperty("validPassword"));
		Assert.assertEquals(p6_AccountSuccessPage.retrieveAccountCreationSuccessMessage(),
				testDataProp.getProperty("expectedSuccessHeading"), "Account Success Page is not displayed");
	}

	@Test(priority = 3)
	public void verifyRegisteringAccountByProvidingExistingEmailAddress() {

		p3_RegisterPageObject.registerWith_AllFields(testDataProp.getProperty("firstName"),
				testDataProp.getProperty("lastName"), configProp.getProperty("validEmail"),
				testDataProp.getProperty("telephone"), configProp.getProperty("validPassword"));
		Assert.assertEquals(p3_RegisterPageObject.retrieveAlreadyRegisteredWarningMessage(),
				testDataProp.getProperty("expectedEmailWarningMessage"), "Email already exists");
	}

	@Test(priority = 4)
	public void verifyRegisteringAccountWithoutFillingAnyDetails() {

		p3_RegisterPageObject.clickOnContinueButton();
		Assert.assertTrue(p3_RegisterPageObject.displayStatusOfWarningMessages(
				testDataProp.getProperty("expectedPrivacyPolicyWarning"),
				testDataProp.getProperty("expectedFirstNameWarning"),
				testDataProp.getProperty("expectedLastNameWarning"),
				testDataProp.getProperty("expectedInvalidEmailWarning"),
				testDataProp.getProperty("expectedTelephoneWarning"),
				testDataProp.getProperty("expectedPasswordWarning")));
	}
}