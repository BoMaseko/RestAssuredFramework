package org.bongz.tests;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.bongz.constants.FrameworkConstants;
import org.bongz.reports.ExtentLogger;
import org.bongz.requestbuilder.RequestBuilder;
import org.bongz.utils.FakerUtils;
import org.bongz.utils.IDNumberDataUtils;
import org.bongz.utils.IDNumberParserUtils;
import org.bongz.utils.RandomUtils;
import org.bongz.utils.ReadJsonUtils;
import org.testng.annotations.Test;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.restassured.response.Response;

public class PremierWithRSAID extends BaseTest{
	
	
	@Test
	public void createPremierWithRSAIDTest(Map<String, String> data) throws Exception {
		
		String createContractBody = ReadJsonUtils.getJsonFileAsString(FrameworkConstants.getRequestFolderPath() + "premierContractRSAID.json");
		
		IDNumberParserUtils idNumberParser = new IDNumberParserUtils();
		IDNumberDataUtils idNumberData = idNumberParser.parse(data.get("identityNumber"));
		
		String createContractBodyNew = createContractBody.replace("_contractStartDate", data.get("contractStartDate"))
				.replaceAll("_firtsName", FakerUtils.getFirstName())
				.replace("_lastName", FakerUtils.getLastName())
				.replace("_dateOfBirth", idNumberData.getBirthdate().toString())
				.replace("_idNumber", data.get("identityNumber"))
				.replace("_gender", idNumberData.getGender().toString())
				.replace("_age", String.valueOf(idNumberData.getAge()))
				.replace("_refNumber", RandomUtils.generateRandomNumericString(8));

		Response createContractResponse = RequestBuilder.getBuilder()
				.body(createContractBodyNew)
				.post("/contract-management-service/rest/v1/contract");
		
		String contractNo = createContractResponse.jsonPath().getString("contractNo");

		Response searchContractResponse = given()
				.log()
				.all()
				.baseUri(FrameworkConstants.getBaseURI())
				.queryParam("contractNo", contractNo)
				.get("/contract-management-service/rest/v1/contract");
		
		String customerNo = searchContractResponse.jsonPath().getString("contractMembers[0].customerId[0]");
		
		createContractResponse.prettyPrint();
		searchContractResponse.prettyPrint();
		
		ExtentLogger.info("Premier contract :" + contractNo + " with customerNo :" + customerNo);
		ExtentLogger.pass(MarkupHelper.createCodeBlock(createContractResponse.asPrettyString(), CodeLanguage.JSON));
		ExtentLogger.pass(MarkupHelper.createCodeBlock(searchContractResponse.asPrettyString(), CodeLanguage.JSON));
		
	}

}
