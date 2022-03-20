package org.bongz.listeners;

import static org.bongz.constants.FrameworkConstants.*;

import java.io.IOException;
import java.util.Arrays;

import org.bongz.reports.ExtentLogger;
import org.bongz.reports.ExtentReport;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ListenerClass implements ITestListener, ISuiteListener{

	@Override
	public void onStart(ISuite suite) {
		ExtentReport.initReport();
	}

	@Override
	public void onFinish(ISuite suite) {
		try {
			ExtentReport.tearDownReports();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentReport.createTest(result.getMethod().getDescription());
		ExtentLogger.info(BOLD_START + "Multiply Automation Framework" + BOLD_END);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		String logText = "<b>" + result.getName() + " passed.<b>" + " " + ICON_SMILEY_PASS;
		Markup markupmessage = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		ExtentLogger.pass(markupmessage);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		ExtentLogger.fail(ICON_BUG + " " + "<b><i>" + result.getThrowable().getMessage() + "</i></b>");
		 String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		   String message = "<details><summary><b><font color=red> Exception occured, click to see details: "
	                + ICON_SMILEY_FAIL + " </font></b>" + "</summary>" + exceptionMessage.replaceAll(",", "<br>")
	                + "</details> \n";
		   ExtentLogger.fail(message);
		   String logText = "<b>" + result.getMethod().getMethodName() + " failed.<b>" + " " + ICON_SMILEY_FAIL;
		   Markup markupmessage = MarkupHelper.createLabel(logText, ExtentColor.RED);
		   ExtentLogger.fail(markupmessage);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentLogger.skip(ICON_BUG + " " + "<b><i>" + result.getThrowable().toString() + "</i></b>");
		String logText = "<b>" + result.getMethod().getMethodName() + " is skipped.<b>" + " " + ICON_SMILEY_SKIP;
		 Markup markupmessage = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		 ExtentLogger.skip(markupmessage);
	}

}
