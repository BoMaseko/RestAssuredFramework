package org.bongz.utils;

import java.util.Locale;

import com.github.javafaker.Faker;

public final class FakerUtils {

	private FakerUtils() {}
	
	public static String getFirstName() {
		return new Faker(new Locale("en-ZA")).name().firstName();
	}
	
	public static String getLastName() {
		return new Faker(new Locale("en-ZA")).name().lastName();
	}
	
	public static String getAddress() {
		return new Faker(new Locale("en-ZA")).address().fullAddress();
	}
	
	public static String getPhone() {
		return new Faker(new Locale("en-ZA")).phoneNumber().cellPhone();
	}
	
	public static String getID() {
		return new Faker(new Locale("en-ZA")).idNumber().valid();
	}
}
