package org.bongz.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.bongz.constants.FrameworkConstants;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public final class ExtentReport {

	private ExtentReport() {}
	
	private static ExtentReports extent;
	private static ExtentTest test;
	
	public static void initReport() {
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter(FrameworkConstants.getExtentReportFolderPath());
		extent.attachReporter(spark);
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setReportName("Bongz Automation Labz - Training");
		spark.config().setDocumentTitle("Bongz Automation Learningz");
	}
	
	public static void tearDownReports() throws IOException {
		extent.flush();
		Desktop.getDesktop().browse(new File(FrameworkConstants.getExtentReportFolderPath()).toURI());
	}
	
	public static void createTest(String testcasename) {
		test = extent.createTest(testcasename);
		ExtentManager.setExtentTest(test);
	}
}
