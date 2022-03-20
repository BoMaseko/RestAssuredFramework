package org.bongz.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import org.bongz.constants.FrameworkConstants;
import org.bongz.enums.ConfigProperties;

public final class PropertyUtils {

	private PropertyUtils() {}
	
	private static Properties property = new Properties();
	private static final Map<String, String> CONFIGMAP = new HashMap<>();
	
	static {
		
		try (FileInputStream file = new FileInputStream(FrameworkConstants.getConfigPropertyFilePath())){
			 
			
			property.load(file);
			
			for(Map.Entry<Object, Object> entry: property.entrySet()) {
				CONFIGMAP.put(String.valueOf(entry.getKey()), String.valueOf(property.get(entry.getKey())).trim());
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static String getPropertyValue(ConfigProperties value) throws Exception {
		
		if(Objects.isNull(value) || Objects.isNull(CONFIGMAP.get(value.name().toLowerCase()))) {
			throw new Exception("Property name " + value + " is not found, please check config.properties");
		}
		return CONFIGMAP.get(value.name().toLowerCase());
	}
	
}
