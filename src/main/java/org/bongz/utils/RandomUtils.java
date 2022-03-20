package org.bongz.utils;

import java.security.SecureRandom;

public final class RandomUtils {

	private RandomUtils() {}
	
	static SecureRandom random = new SecureRandom();
	
	public static String generateRandomNumericString(int lenght) {
		String textnumber = "0123456789";
		StringBuilder sb = new StringBuilder(lenght);
		for(int i = 0; i < lenght; i++) {
			sb.append(textnumber.charAt(random.nextInt(textnumber.length())));
		}
		return sb.toString();
	}
	
	public static String generateRandomString(int lenght) {
		String text = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder(lenght);
		for(int i = 0; i < lenght; i++) {
			sb.append(text.charAt(random.nextInt(text.length())));
		}
		return sb.toString();
	}
}
