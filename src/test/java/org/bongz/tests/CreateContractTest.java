package org.bongz.tests;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.bongz.constants.FrameworkConstants.STATUSCODE200;

import java.io.IOException;
import java.util.Map;

import org.bongz.constants.FrameworkConstants;
import org.bongz.reports.ExtentLogger;
import org.bongz.requestbuilder.RequestBuilder;
import org.bongz.utils.FakerUtils;
import org.bongz.utils.RandomUtils;
import org.bongz.utils.ReadJsonUtils;
import org.testng.annotations.Test;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.response.Response;

public class CreateContractTest extends BaseTest{


	@Test
	public void createContractTest(Map<String, String> data) throws IOException {

		String requestBody = ReadJsonUtils.getJsonFileAsString(FrameworkConstants.getRequestFolderPath() + "contracts.json");

		String requestBodyNew = requestBody.replace("_firstname", FakerUtils.getFirstName())
				.replaceAll("_lastname", FakerUtils.getLastName())
				.replace("_agent", FakerUtils.getFirstName())
				.replace("_address", FakerUtils.getAddress())
				.replace("_productRefNo", RandomUtils.generateRandomNumericString(6))
				.replace("_accoountNo", RandomUtils.generateRandomNumericString(10))
				.replace("_accountHolder", FakerUtils.getFirstName() + " " + FakerUtils.getLastName())
				.replace("_phoneNo", FakerUtils.getPhone())
				.replace("_id", FakerUtils.getID());


		Response response = RequestBuilder.getBuilder()
				.body(requestBodyNew)
				.post("/contract-management-service/rest/v1/contract");

		Response response2 = given()
				.baseUri(FrameworkConstants.getBaseURI())
				.get("/contract-management-service/rest/v1/contract?contractNo=100609660");

		ExtentLogger.pass(MarkupHelper.createCodeBlock(response.asPrettyString(), CodeLanguage.JSON));
		ExtentLogger.pass(MarkupHelper.createCodeBlock(response2.asPrettyString(), CodeLanguage.JSON));

		response.prettyPrint();
		assertThat(response.getStatusCode()).isEqualTo(STATUSCODE200);

	}
}
