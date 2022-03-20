package org.bongz.tests;

import java.io.PrintStream;
import java.io.StringWriter;

import org.apache.commons.io.output.WriterOutputStream;
import org.bongz.reports.ExtentLogger;
import org.testng.annotations.BeforeMethod;



public class BaseTest {

	protected StringWriter writer;
	protected PrintStream captor;


	@BeforeMethod
	public void setup() {

		writer = new StringWriter();
		captor = new PrintStream(new WriterOutputStream(writer), true);
		
	}
	
	
}
