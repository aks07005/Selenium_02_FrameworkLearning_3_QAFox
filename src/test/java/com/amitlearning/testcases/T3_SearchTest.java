package com.amitlearning.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.amitlearning.pageobjects.P1_HomePageObject;
import com.amitlearning.pageobjects.P2_LoginPageObject;
import com.amitlearning.pageobjects.P5_SearchPageObject;
import com.amitlearning.testcomponents.MyBaseTestQAFox;

public class T3_SearchTest extends MyBaseTestQAFox {

	public WebDriver driver;

	P1_HomePageObject p1_HomePageObject;
	P2_LoginPageObject p2_LoginPageObject;
	P5_SearchPageObject p5_SearchPageObject;

	public T3_SearchTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplication(configProp.getProperty("browserName"));
		p1_HomePageObject = new P1_HomePageObject(driver);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() {

		p5_SearchPageObject = p1_HomePageObject.searchForAProduct(testDataProp.getProperty("validSearchProductName"));
		Assert.assertTrue(p5_SearchPageObject.retrieveSearchedProductName()
				.equals(testDataProp.getProperty("validSearchProductName")), "Not found the product name");
	}

	@Test(priority = 2)
	public void verifySearchWithInValidProduct() {

		p5_SearchPageObject = p1_HomePageObject.searchForAProduct(testDataProp.getProperty("invalidSearchProductName"));
		Assert.assertEquals(p5_SearchPageObject.retrieveInvalidProductSearchedMessage(), "abcd",
				"Unexpected product display");
	}

//testDataProp.getProperty("noProductInSearchMessage")
	@Test(priority = 3, dependsOnMethods = { "verifySearchWithValidProduct", "verifySearchWithInValidProduct" })
	public void verifySearchWithoutAnyProduct() {
		p5_SearchPageObject = p1_HomePageObject.clickSearchButton();
		Assert.assertEquals(p5_SearchPageObject.retrieveBlankProductSearchMessage(),
				testDataProp.getProperty("noProductInSearchMessage"), "No product search message product display");
	}

	@Test(priority = 4)
	public void verifySearchWithValidProductOnLogin() {
		p2_LoginPageObject = p1_HomePageObject.navigateToLoginPage();
		p2_LoginPageObject.login(configProp.getProperty("validEmail"), configProp.getProperty("validPassword"));
		p5_SearchPageObject = p1_HomePageObject.searchForAProduct(testDataProp.getProperty("validSearchProductName"));
		Assert.assertTrue(p5_SearchPageObject.retrieveSearchedProductName()
				.equals(testDataProp.getProperty("validSearchProductName")), "Not found the product name");
	}
}