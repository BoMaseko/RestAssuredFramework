package org.bongz.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bongz.constants.FrameworkConstants;
import org.testng.annotations.DataProvider;

public final class DataProviderUtils {

	private DataProviderUtils() {}
	private static List<Map<String, String>> list = new ArrayList<>();
	
	@DataProvider(parallel=true)
	public static Object[] getData(Method m) {
		
		String testname = m.getName();
		/*
		 * Reading the excel only once for optimization
		 * 
		 */
		if(list.isEmpty()) {
			list = ExcelUtils.getTestDetails(FrameworkConstants.ITERATIONDATASHEET);
		}
		
		//List<Map<String, String>> list = ExcelUtils.getTestDetails(FrameworkConstants.getIterationdatasheet());
		
		List<Map<String, String>> iterationList = new ArrayList<Map<String,String>>();
		
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).get("testname").equalsIgnoreCase(testname) 
					&& list.get(i).get("execute").equalsIgnoreCase("yes")){
				iterationList.add(list.get(i));
			}
		}
		
		//list.removeAll(iterationList); // Remove the already ran test from the list(optimization)
		return iterationList.toArray();
		
	}
}
