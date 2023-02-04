package com.amitlearning.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.amitlearning.testcomponents.MyBaseTestQAFox;
import com.amitlearning.utils.Utilities;

public class T1_LoginTestInternalDataReader extends MyBaseTestQAFox {

	public WebDriver driver;

	public T1_LoginTestInternalDataReader() {
		super();
	}

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplication(configProp.getProperty("browserName"));
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1, dataProvider = "validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email, String password) {
		driver.findElement(By.id("input-email")).sendKeys(email);
		driver.findElement(By.id("input-password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@value='Login']")).click();

		Assert.assertTrue(driver.findElement(By.linkText("Change your password")).isDisplayed());
	}
	
	@DataProvider(name = "validCredentialsSupplier")
	public Object[][] supplyTestData() {
		Object[][] data = { { "amotooricap9@gmail.com", "12345" }, { "aks07@gmail.com", "12345" },
				{ "aks007@gmail.com", "12345" }, { "aks0007@gmail.com", "12345" } };
		return data;
	}

	@Test(priority = 2)
	public void verifyLoginWithInValidCredentials() throws InterruptedException {
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys(testDataProp.getProperty("invalidPassword"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();

//		String warningMessage = driver.findElement(By.cssSelector("div.alert-danger")).getText();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-danger')]")).getText();

		Assert.assertEquals(actualWarningMessage, testDataProp.getProperty("expectedWarningMessage"));
	}

	@Test(priority = 3)
	public void verifyLoginWithInValidEmailAndValidPassword() {
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys(configProp.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();

		String actualWarningMessage = driver.findElement(By.cssSelector("div.alert-danger")).getText();
//		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-danger')]")).getText();

		String expectedWarningMessage = testDataProp.getProperty("expectedWarningMessage");

//		Assert.assertEquals(actualWarningMessage, "Warning: No match for E-Mail Address and/or Password.");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warning Message is displayed");
	}

	@Test(priority = 4)
	public void verifyLoginWithValidEmailAndInValidPassword() {
		driver.findElement(By.id("input-email")).sendKeys(configProp.getProperty("validEmail"));
		driver.findElement(By.id("input-password")).sendKeys(testDataProp.getProperty("invalidPassword"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();

		String actualWarningMessage = driver.findElement(By.cssSelector("div.alert-danger")).getText();
		String expectedWarningMessage = testDataProp.getProperty("expectedWarningMessage");

		Assert.assertEquals(actualWarningMessage, expectedWarningMessage);
	}

	@Test(priority = 5)
	public void verifyLoginWithoutProvidingCredentials() {
		driver.findElement(By.xpath("//input[@value='Login']")).click();

//		String warningMessage = driver.findElement(By.cssSelector("div.alert-danger")).getText();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-danger')]")).getText();

		Assert.assertEquals(actualWarningMessage, testDataProp.getProperty("expectedWarningMessage"));
	}
}
