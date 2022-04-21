package org.bongz.tests;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
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

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StarterWithRSAID extends BaseTest{
	
	@Test
	public void createStarterWithRSAIDTest(Map<String, String> data) throws Exception {
		
		//******************************Creating a Starter Contract*************************************
				String createContractBody = ReadJsonUtils.getJsonFileAsString(FrameworkConstants.getRequestFolderPath() + "starterContractSAID.json");
				
				
				IDNumberParserUtils idNumberParser = new IDNumberParserUtils();
				IDNumberDataUtils idNumberData = idNumberParser.parse(data.get("identityNumber"));
				

				String createContractBodyNew = createContractBody.replace("_contractStartDate", data.get("contractStartDate"))
						.replaceAll("_firtsName", FakerUtils.getFirstName())
						.replace("_lastName", FakerUtils.getLastName())
						.replace("_dateOfBirth", idNumberData.getBirthdate().toString())
						.replace("_eventDate", data.get("eventDate"))
						.replace("_idNumber", data.get("identityNumber"))
						.replace("_gender", idNumberData.getGender().toString())
						.replace("_age", String.valueOf(idNumberData.getAge()))
						.replace("_refNumber", RandomUtils.generateRandomNumericString(7));

				Response createContractResponse = given()
						.auth()
						.basic("contractmanagement", "mu1t!p1yc0ntract")
						.log()
						.all()
						.baseUri("https://pre-internal-ces.mmiholdings.com")
						.contentType(ContentType.JSON)
						.body(createContractBodyNew)
						.post("/contracts/contract-service/api/contracts/quote");
				
				createContractResponse.prettyPrint();
				
				String contractNo = createContractResponse.jsonPath().getString("contractNumber");
				
				//********************************Activate Contract****************************
				
				Map<String, Object> map = new HashMap<>();
				map.put("contractNo", Integer.parseInt(contractNo));
				map.put("contractSource", "ABHI");
				map.put("paymentStatusCd", "CLR");
				
				Response activateContractResponse = RequestBuilder.getBuilder()
						.body(map)
						.put("/contract-management-service/rest/v1/contract/activateContract");
				
				activateContractResponse.prettyPrint();
						
				//*********************************Search Contract**************************************
				Response searchContractResponse = given()
						.log()
						.all()
						.baseUri(FrameworkConstants.getBaseURI())
						.queryParam("contractNo", contractNo)
						.get("/contract-management-service/rest/v1/contract");
				
				searchContractResponse.prettyPrint();
				
				//**********************************Search Customer on Campaign*****************************
				
				String customerNo = searchContractResponse.jsonPath().getString("contractMembers[0].customerId[0]");
				
				Response customerCampaignResponse = given()
						.log()
						.all()
						.baseUri(FrameworkConstants.getBaseURI())
						.pathParam("customerNo", customerNo)
						.get("/CustomerEngagement/rest/campaignActivity/customerCampaignDetails/{customerNo}");
				
				customerCampaignResponse.prettyPrint();
				ExtentLogger.info("Starter contract :" + contractNo + " with customerNo :" + customerNo);
				ExtentLogger.pass(MarkupHelper.createCodeBlock(createContractResponse.asPrettyString(), CodeLanguage.JSON));
				ExtentLogger.pass(MarkupHelper.createCodeBlock(searchContractResponse.asPrettyString(), CodeLanguage.JSON));
				
	}

}
