package org.bongz.constants;

import java.util.HashMap;
import java.util.Map;

import org.bongz.enums.Constants;

public final class FrameworkConstantsWithEagerLoading {
	
	private FrameworkConstantsWithEagerLoading() {}
	
	private static final Map<Constants, String> CONSTANTS = new HashMap<>();
	
	static {
		CONSTANTS.put(Constants.BASEURI, "https://multiplyaipre.multiply.co.za");
	}
	
	public static String getValue(Constants key) {
		return CONSTANTS.get(key);
	}

}
