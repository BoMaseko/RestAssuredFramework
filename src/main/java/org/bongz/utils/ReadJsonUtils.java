package org.bongz.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class ReadJsonUtils {
	
	private ReadJsonUtils() {}
	
	public static String getJsonFileAsString(String location) throws IOException {
		
		return new String(Files.readAllBytes(Paths.get(location)));
	}

}
