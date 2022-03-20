package org.bongz.constants;

import lombok.Getter;

@Getter
public class FrameworkConstantsWithSingleton {
	
	//single instance of a class
	
	private FrameworkConstantsWithSingleton() {}
	
	private final static FrameworkConstantsWithSingleton INSTANCE = new FrameworkConstantsWithSingleton();
	
	public static FrameworkConstantsWithSingleton getInstance() {
		return INSTANCE;
	}
	
	private String baseuri =  "https://multiplyaipre.multiply.co.za";

}
