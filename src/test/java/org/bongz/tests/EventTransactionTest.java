package org.bongz.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bongz.constants.FrameworkConstants;
import org.bongz.reports.ExtentLogger;
import org.bongz.reports.ExtentReport;
import org.bongz.requestbuilder.RequestBuilder;
import org.bongz.utils.DataProviderUtils;
import org.bongz.utils.RandomUtils;
import org.bongz.utils.ReadJsonUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.response.Response;

public class EventTransactionTest extends BaseTest{

	
	@Test
	public void fitnessAssessmentTest(Map<String, String> data) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("transformedEntityId", data.get("customerId"));
		map.put("eventDate", data.get("eventDate"));
		map.put("eventDefinitionCode", "Fitness_Assessment");
		map.put("eventSourceCode", "CAMAF");
		map.put("eventReceivedDate", data.get("eventReceivedDate"));
		
		Map<String, Object> etv = new HashMap<>();
		etv.put("eventValues", "5");
		etv.put("eventValueCode", "FITNESS_LEVEL");
		
		
		List<Map<String, Object>> values = new ArrayList<>();
		values.add(etv);
		
		map.put("eventTransactionValues", values);
		
		Response response = RequestBuilder.getBuilder()
				.filter(new RequestLoggingFilter(captor))
				.body(map)
				.post("/event-management-service/rest/v1/eventTransaction");
		
		ExtentLogger.logRequestAndReponseInReport(writer.toString());
		
		assertThat(response.getStatusCode()).isEqualTo(Integer.parseInt(data.get("statusCode")));
				
		
	}
	
	@Test
	public void safetyQuestionnaireTest(Map<String, String> data) throws IOException {
		
		String requestBody = ReadJsonUtils.getJsonFileAsString(FrameworkConstants.getRequestFolderPath() + "SafetyQuestionnaire.json");
		
		String requestBodyNew = requestBody.replace("customerNumber", RandomUtils.generateRandomNumericString(6))
								.replaceAll("AN-HomeQues9Ans11", "AN-HomeQues9Ans10");
		
		Response response = RequestBuilder.getBuilder()
				.body(requestBody)
				.post("/multiply-questionnaire-impl/rest/v1/questionnaire/transaction");
		
		ExtentLogger.info(MarkupHelper.createCodeBlock(response.asPrettyString(), CodeLanguage.JSON));
		
		response.prettyPrint();
		assertThat(response.getStatusCode()).isEqualTo(FrameworkConstants.STATUSCODE200);
		//assertThat(response.jsonPath().get("status")).isEqualTo("complete");
		
	}
	
	/*
	 * @DataProvider private Object[][] getData(){ return new Object[][] {
	 * {"25463215"}, {"54875522"}, {"58745256"} }; }
	 */
}


