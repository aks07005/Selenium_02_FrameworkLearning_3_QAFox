package com.amitlearning.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.amitlearning.utils.Utilities;

public class MyBaseTestQAFox {

	WebDriver driver;
	public Properties configProp;
	public Properties testDataProp;

	public MyBaseTestQAFox() {
		configProp = new Properties();
		File configPropFilePath = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\amitlearning\\config\\config.properties");
		try {
			FileInputStream configFis = new FileInputStream(configPropFilePath);
			configProp.load(configFis);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		testDataProp = new Properties();
		File testDataPropFilePath = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\amitlearning\\testdata\\testdata.properties");
		try {
			FileInputStream testDataFis = new FileInputStream(testDataPropFilePath);
			testDataProp.load(testDataFis);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public WebDriver initializeBrowserAndOpenApplication(String browserName) {
		if (browserName.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "D:\\TestLogFile\\FirefoxLogFile.txt");
			driver = new FirefoxDriver();
		} else if (browserName.equals("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.implicit_wait_time));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.pageload_time));
		driver.get(configProp.getProperty("url"));
		return driver;
	}
}
