package com.amitlearning.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class MyExtentReporter {

	public static ExtentReports generateExtentReport() {
		ExtentReports extentReport = new ExtentReports();

		File extentReportFilePath = new File(
				System.getProperty("user.dir") + "\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter reporter = new ExtentSparkReporter(extentReportFilePath);
		reporter.config().setTheme(Theme.DARK);
		reporter.config().setReportName("AmitLearning Test Automation Result");
		reporter.config().setDocumentTitle("AL Automation Report");
		reporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

		extentReport.attachReporter(reporter);

		// configuring displays of the report dashboard

		// reading the configuration file
		Properties configProp = new Properties();
		File configPropFilePath = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\amitlearning\\config\\config.properties");
		try {
			FileInputStream configFis = new FileInputStream(configPropFilePath);
			configProp.load(configFis);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		extentReport.setSystemInfo("Application URL", configProp.getProperty("url"));
		extentReport.setSystemInfo("Browser Name", configProp.getProperty("browserName"));
		extentReport.setSystemInfo("Email", configProp.getProperty("validEmail"));
		extentReport.setSystemInfo("Password", configProp.getProperty("validPassword"));
		extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReport.setSystemInfo("User Name", System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));

		return extentReport;
	}
}
