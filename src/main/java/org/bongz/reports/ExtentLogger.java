package org.bongz.reports;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public final class ExtentLogger {
	
	private ExtentLogger() {}

	public static void pass(String message) {
		ExtentManager.getExtentTest().pass(message);
	}
	
	public static void pass(Markup message) {
		ExtentManager.getExtentTest().pass(message);
	}

	public static void fail(String message) {
		ExtentManager.getExtentTest().fail(message);
	}
	
	public static void fail(Markup message) {
		ExtentManager.getExtentTest().fail(message);
	}

	public static void skip(String message) {
		ExtentManager.getExtentTest().skip(message);
	}
	
	public static void skip(Markup message) {
		ExtentManager.getExtentTest().skip(message);
	}

	public static void info(String message) {
		ExtentManager.getExtentTest().info(message);
	}

	public static void info(Markup message) {
		ExtentManager.getExtentTest().info(message);
	}
	
	public static void logRequestAndReponseInReport(String request) {
		String prettyPrint = request.replace("\n", "<br>");
		ExtentManager.getExtentTest().info("<pre>" + prettyPrint + "</pre>");
		//ExtentManager.getExtentTest().info(MarkupHelper.createCodeBlock(response, CodeLanguage.JSON));
	}
}
