package com.amitlearning.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.amitlearning.pageobjects.P1_HomePageObject;
import com.amitlearning.pageobjects.P2_LoginPageObject;
import com.amitlearning.pageobjects.P4_AccountPageObject;
import com.amitlearning.testcomponents.MyBaseTestQAFox;
import com.amitlearning.utils.Utilities;

public class T1_LoginTestDataFromExcelTest extends MyBaseTestQAFox {

	public WebDriver driver;

	P2_LoginPageObject p2_LoginPageObject;

	public T1_LoginTestDataFromExcelTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplication(configProp.getProperty("browserName"));
		P1_HomePageObject p1_HomePageObject = new P1_HomePageObject(driver);
		p2_LoginPageObject = p1_HomePageObject.navigateToLoginPage();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1, dataProvider = "validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email, String password) {

		P4_AccountPageObject p4_AccountPageObject = p2_LoginPageObject.login(email, password);
		Assert.assertTrue(p4_AccountPageObject.getDisplayStatusOfChangeYourPasswordLink());
	}

	@DataProvider(name = "validCredentialsSupplier")
	public Object[][] supplyTestData() {
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}

	@Test(priority = 2)
	public void verifyLoginWithInValidCredentials() throws InterruptedException {

		p2_LoginPageObject.login(Utilities.generateEmailWithTimeStamp(), testDataProp.getProperty("invalidPassword"));
		String actualWarningMessage = p2_LoginPageObject.captureWarningMessage();
		Assert.assertEquals(actualWarningMessage, testDataProp.getProperty("expectedWarningMessage"));
	}

	@Test(priority = 3)
	public void verifyLoginWithInValidEmailAndValidPassword() {

		p2_LoginPageObject.login(Utilities.generateEmailWithTimeStamp(), configProp.getProperty("validPassword"));
		String actualWarningMessage = p2_LoginPageObject.captureWarningMessage();
		String expectedWarningMessage = testDataProp.getProperty("expectedWarningMessage");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warning Message is displayed");
	}

	@Test(priority = 4)
	public void verifyLoginWithValidEmailAndInValidPassword() {

		p2_LoginPageObject.login(configProp.getProperty("validEmail"), testDataProp.getProperty("invalidPassword"));
		String actualWarningMessage = p2_LoginPageObject.captureWarningMessage();
		String expectedWarningMessage = testDataProp.getProperty("expectedWarningMessage");
		Assert.assertEquals(actualWarningMessage, expectedWarningMessage);
	}

	@Test(priority = 5)
	public void verifyLoginWithoutProvidingCredentials() {

		p2_LoginPageObject.loginButtonClick();
		String actualWarningMessage = p2_LoginPageObject.captureWarningMessage();
		Assert.assertEquals(actualWarningMessage, testDataProp.getProperty("expectedWarningMessage"));
	}
}
