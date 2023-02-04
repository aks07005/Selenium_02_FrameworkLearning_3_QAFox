package com.amitlearning.testcomponents;

import java.awt.Desktop;
import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.amitlearning.utils.MyExtentReporter;
import com.amitlearning.utils.Utilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class MyListeners implements ITestListener {

	ExtentReports extentReport;
	ExtentTest extentTest;
	WebDriver driver;

	@Override
	public void onStart(ITestContext context) {
		extentReport = MyExtentReporter.generateExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO, result.getName() + " started executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS, result.getName() + " successfully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {

		// get the driver from the test class
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (Throwable e) {
			e.printStackTrace();
		}

		String desScreenshot = Utilities.captureScreenshot(driver, result.getName());

		extentTest.addScreenCaptureFromPath(desScreenshot);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, result.getName() + " got failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, result.getName() + " got skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
		String extentReportFilePath = System.getProperty("user.dir")
				+ "\\test-output\\ExtentReports\\extentReport.html";
		File extentReportFile = new File(extentReportFilePath);
		try {
			Desktop.getDesktop().browse(extentReportFile.toURI());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
