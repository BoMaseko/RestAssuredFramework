package org.bongz.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bongz.constants.FrameworkConstants;
import org.bongz.utils.ExcelUtils;
import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

public class MethodInterceptor implements IMethodInterceptor{

	@Override
	public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {

		System.out.println("----------------METHOD INTERCEPTOR--------------------------");
		//System.out.println("methods: " + methods);

		List<Map<String,String>> list = ExcelUtils.getTestDetails(FrameworkConstants.RUNMANAGERSHEET);
		List<IMethodInstance> result = new ArrayList<>();


		for(int i=0;i<methods.size();i++) {
			for(int j=0;j<list.size();j++) {
				if(methods.get(i).getMethod().getMethodName().equalsIgnoreCase(list.get(j).get("testname")) &&
						list.get(j).get("execute").equalsIgnoreCase("yes")) {
					methods.get(i).getMethod().setDescription((list.get(j).get("testdescription")));
					result.add(methods.get(i));
				}

			}
		}
		return result;
	}
}
