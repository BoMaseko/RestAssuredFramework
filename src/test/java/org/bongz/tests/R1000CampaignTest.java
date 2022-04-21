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

public class R1000CampaignTest extends BaseTest{


	@Test
	public void R1000CashBackCampaignTest(Map<String, String> data) throws IOException {

		String createContractBody = ReadJsonUtils.getJsonFileAsString(FrameworkConstants.getRequestFolderPath() + "contracts.json");
		String safetyQuestionnaireBody = ReadJsonUtils.getJsonFileAsString(FrameworkConstants.getRequestFolderPath() + "SafetyQuestionnaire.json");
		String financeQuestionnaireBody = ReadJsonUtils.getJsonFileAsString(FrameworkConstants.getRequestFolderPath() + "financialQuestionnaire.json");
		String physicalQuestionnaireBody = ReadJsonUtils.getJsonFileAsString(FrameworkConstants.getRequestFolderPath() + "physicalQuestionnaire.json");
		String fitnessAssessmentBody = ReadJsonUtils.getJsonFileAsString(FrameworkConstants.getRequestFolderPath() + "fitnessAssessment.json");
		String activeDayzBody = ReadJsonUtils.getJsonFileAsString(FrameworkConstants.getRequestFolderPath() + "activeDayz.json");
		String cardLinkBody = ReadJsonUtils.getJsonFileAsString(FrameworkConstants.getRequestFolderPath() + "cardLink.json");
		String monthlyWinzBody = ReadJsonUtils.getJsonFileAsString(FrameworkConstants.getRequestFolderPath() + "monthlyWin.json");
		String weeklyWinzBody = ReadJsonUtils.getJsonFileAsString(FrameworkConstants.getRequestFolderPath() + "weeklyWin.json");
		String hhsBodyFatBody = ReadJsonUtils.getJsonFileAsString(FrameworkConstants.getRequestFolderPath() + "HHS_BodyFat.json");
		String hhsDiabetesBody = ReadJsonUtils.getJsonFileAsString(FrameworkConstants.getRequestFolderPath() + "HHS_Diabetes.json");
		String hhsHeightBody = ReadJsonUtils.getJsonFileAsString(FrameworkConstants.getRequestFolderPath() + "HHS_Height.json");
		String hhsWeightBody = ReadJsonUtils.getJsonFileAsString(FrameworkConstants.getRequestFolderPath() + "HHS_Weight.json");
		String hhsRandomBSBody = ReadJsonUtils.getJsonFileAsString(FrameworkConstants.getRequestFolderPath() + "HHS_RandomBloodSugar.json");
		String hhsSmokerBody = ReadJsonUtils.getJsonFileAsString(FrameworkConstants.getRequestFolderPath() + "HHS_SmokerStatus.json");
		String hhsSugarBody = ReadJsonUtils.getJsonFileAsString(FrameworkConstants.getRequestFolderPath() + "HHS_Sugar.json");
		String hhsTotalChBody = ReadJsonUtils.getJsonFileAsString(FrameworkConstants.getRequestFolderPath() + "HHS_TotalCho.json");
		String hhsWaistBody = ReadJsonUtils.getJsonFileAsString(FrameworkConstants.getRequestFolderPath() + "HHS_Waist.json");
		String cashBackBody = ReadJsonUtils.getJsonFileAsString(FrameworkConstants.getRequestFolderPath() + "cashBack.json");

		String createContractBodyNew = createContractBody.replace("_firstname", FakerUtils.getFirstName())
				.replaceAll("_lastname", FakerUtils.getLastName())
				.replace("_agent", FakerUtils.getFirstName())
				.replace("_address", FakerUtils.getAddress())
				.replace("_productRefNo", RandomUtils.generateRandomNumericString(6))
				.replace("_accoountNo", RandomUtils.generateRandomNumericString(10))
				.replace("_accountHolder", FakerUtils.getFirstName() + " " + FakerUtils.getLastName())
				.replace("_phoneNo", FakerUtils.getPhone())
				.replace("_id", FakerUtils.getID())
				.replace("_contractStartDate", data.get("contractStartDate"));


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

		searchContractResponse.prettyPrint();

		String customerNo = searchContractResponse.jsonPath().getString("contractMembers[0].customerId[0]");

		Response customerCampaignResponse = given()
				.log()
				.all()
				.baseUri(FrameworkConstants.getBaseURI())
				.pathParam("customerNo", customerNo)
				.get("/CustomerEngagement/rest/campaignActivity/customerCampaignDetails/{customerNo}");

		customerCampaignResponse.prettyPrint();

		ExtentLogger.pass(MarkupHelper.createCodeBlock(createContractResponse.asPrettyString(), CodeLanguage.JSON));

		String completeStatus = customerCampaignResponse.jsonPath().getString("campaigns[0].isCompleted");

		if(completeStatus.equalsIgnoreCase("false")) {

			//**************************Milestone 1 ****************************************
			String safetyQuestionnaireBodyNew = safetyQuestionnaireBody.replace("_customerNumber", customerNo);

			Response safetyQuestionnaireResponse = RequestBuilder.getBuilder()
					.body(safetyQuestionnaireBodyNew)
					.post("/multiply-questionnaire-impl/rest/v1/questionnaire/transaction");
			
			ExtentLogger.pass(MarkupHelper.createCodeBlock(safetyQuestionnaireResponse.asPrettyString(), CodeLanguage.JSON));

			String financeQuestionnaireBodyNew = financeQuestionnaireBody.replace("_customerNumber", customerNo);

			Response financeQuestionnaireResponse = RequestBuilder.getBuilder()
					.body(financeQuestionnaireBodyNew)
					.post("/multiply-questionnaire-impl/rest/v1/questionnaire/transaction");
			
			ExtentLogger.pass(MarkupHelper.createCodeBlock(financeQuestionnaireResponse.asPrettyString(), CodeLanguage.JSON));

			String physicalQuestionnaireBodyNew = physicalQuestionnaireBody.replace("_customerNumber", customerNo);

			Response physicalQuestionnaireResponse = RequestBuilder.getBuilder()
					.body(physicalQuestionnaireBodyNew)
					.post("/multiply-questionnaire-impl/rest/v1/questionnaire/transaction");
			
			ExtentLogger.pass(MarkupHelper.createCodeBlock(physicalQuestionnaireResponse.asPrettyString(), CodeLanguage.JSON));
			
			String cardLinkBodyNew = cardLinkBody.replace("_customerNumber", customerNo)
					.replace("_eventDate", data.get("eventDate"))
					.replace("_receivedDate", data.get("eventReceivedDate"));

			Response cardLinkBodyResponse = RequestBuilder.getBuilder()
					.filter(new RequestLoggingFilter(captor))
					.body(cardLinkBodyNew)
					.post(RequestBuilder.eventTxnURL());
			
			
			String activeDayzBodyNew = activeDayzBody.replace("_customerNumber", customerNo)
					.replace("_eventDate", data.get("eventDate"))
					.replace("_receivedDate", data.get("eventReceivedDate"));

			Response activeDayzBodyResponse = RequestBuilder.getBuilder()
					.filter(new RequestLoggingFilter(captor))
					.body(activeDayzBodyNew)
					.post(RequestBuilder.eventTxnURL());
			
			//***************Milestone 2 *********************************
			
			String cashBackBodyNew = cashBackBody.replace("_customerNumber", customerNo)
					.replace("_eventDate", data.get("eventDate"))
					.replace("_receivedDate", data.get("eventReceivedDate"));

			Response cashBackBodyResponse = RequestBuilder.getBuilder()
					.filter(new RequestLoggingFilter(captor))
					.body(cashBackBodyNew)
					.post(RequestBuilder.eventTxnURL());
			
			String weeklyWinzBodyNew = weeklyWinzBody.replace("_customerNumber", customerNo)
					.replace("_eventDate", data.get("eventDate"))
					.replace("_receivedDate", data.get("eventReceivedDate"));

			Response weeklyWinzBodyResponse = RequestBuilder.getBuilder()
					.filter(new RequestLoggingFilter(captor))
					.body(weeklyWinzBodyNew)
					.post(RequestBuilder.eventTxnURL());
			
			String hhsBodyFatBodyNew = hhsBodyFatBody.replace("_customerNumber", customerNo)
					.replace("_eventDate", data.get("eventDate"))
					.replace("_receivedDate", data.get("eventReceivedDate"));

			Response hhsBodyFatBodyResponse = RequestBuilder.getBuilder()
					.filter(new RequestLoggingFilter(captor))
					.body(hhsBodyFatBodyNew)
					.post(RequestBuilder.eventTxnURL());
			
			String hhsDiabetesBodyNew = hhsDiabetesBody.replace("_customerNumber", customerNo)
					.replace("_eventDate", data.get("eventDate"))
					.replace("_receivedDate", data.get("eventReceivedDate"));

			Response hhsDiabetesBodyResponse = RequestBuilder.getBuilder()
					.filter(new RequestLoggingFilter(captor))
					.body(hhsDiabetesBodyNew)
					.post(RequestBuilder.eventTxnURL());
			
			String hhsHeightBodyNew = hhsHeightBody.replace("_customerNumber", customerNo)
					.replace("_eventDate", data.get("eventDate"))
					.replace("_receivedDate", data.get("eventReceivedDate"));

			Response hhsHeightBodyResponse = RequestBuilder.getBuilder()
					.filter(new RequestLoggingFilter(captor))
					.body(hhsHeightBodyNew)
					.post(RequestBuilder.eventTxnURL());
			
			String hhsRandomBSBodyNew = hhsRandomBSBody.replace("_customerNumber", customerNo)
					.replace("_eventDate", data.get("eventDate"))
					.replace("_receivedDate", data.get("eventReceivedDate"));

			Response hhsRandomBSBodyResponse = RequestBuilder.getBuilder()
					.filter(new RequestLoggingFilter(captor))
					.body(hhsRandomBSBodyNew)
					.post(RequestBuilder.eventTxnURL());
			
			String hhsSmokerBodyNew = hhsSmokerBody.replace("_customerNumber", customerNo)
					.replace("_eventDate", data.get("eventDate"))
					.replace("_receivedDate", data.get("eventReceivedDate"));

			Response hhsSmokerBodyResponse = RequestBuilder.getBuilder()
					.filter(new RequestLoggingFilter(captor))
					.body(hhsSmokerBodyNew)
					.post(RequestBuilder.eventTxnURL());
			
			String hhsSugarBodyNew = hhsSugarBody.replace("_customerNumber", customerNo)
					.replace("_eventDate", data.get("eventDate"))
					.replace("_receivedDate", data.get("eventReceivedDate"));

			Response hhsSugarBodyResponse = RequestBuilder.getBuilder()
					.filter(new RequestLoggingFilter(captor))
					.body(hhsSugarBodyNew)
					.post(RequestBuilder.eventTxnURL());
			
			String hhsTotalChBodyNew = hhsTotalChBody.replace("_customerNumber", customerNo)
					.replace("_eventDate", data.get("eventDate"))
					.replace("_receivedDate", data.get("eventReceivedDate"));

			Response hhsTotalChBodyResponse = RequestBuilder.getBuilder()
					.filter(new RequestLoggingFilter(captor))
					.body(hhsTotalChBodyNew)
					.post(RequestBuilder.eventTxnURL());
			
			String hhsWaistBodyNew = hhsWaistBody.replace("_customerNumber", customerNo)
					.replace("_eventDate", data.get("eventDate"))
					.replace("_receivedDate", data.get("eventReceivedDate"));

			Response hhsWaistBodyResponse = RequestBuilder.getBuilder()
					.filter(new RequestLoggingFilter(captor))
					.body(hhsWaistBodyNew)
					.post(RequestBuilder.eventTxnURL());
			
			String hhsWeightBodyNew = hhsWeightBody.replace("_customerNumber", customerNo)
					.replace("_eventDate", data.get("eventDate"))
					.replace("_receivedDate", data.get("eventReceivedDate"));

			Response hhsWeightBodyResponse = RequestBuilder.getBuilder()
					.filter(new RequestLoggingFilter(captor))
					.body(hhsWeightBodyNew)
					.post(RequestBuilder.eventTxnURL());

			Response HHSBatchResponse = given()
					.baseUri(FrameworkConstants.getBaseURI())
					.get("/healthy-heart-score-batch-rest/rest/HealthyHeartScoreRestService/runHealthyHeartScoreBatch");

			//*********************Milestone 3 *************************
			String monthlyWinzBodyNew = monthlyWinzBody.replace("_customerNumber", customerNo)
					.replace("_eventDate", data.get("eventDate"))
					.replace("_receivedDate", data.get("eventReceivedDate"));

			Response monthlyWinzBodyResponse = RequestBuilder.getBuilder()
					.filter(new RequestLoggingFilter(captor))
					.body(monthlyWinzBodyNew)
					.post(RequestBuilder.eventTxnURL());

			String fitnessAssessmentBodyNew = fitnessAssessmentBody.replace("_customerNumber", customerNo)
					.replace("_eventDate", data.get("eventDate"))
					.replace("_receivedDate", data.get("eventReceivedDate"));

			Response fitnessAssessmentResponse = RequestBuilder.getBuilder()
					.filter(new RequestLoggingFilter(captor))
					.body(fitnessAssessmentBodyNew)
					.post(RequestBuilder.eventTxnURL());
			
			assertThat(fitnessAssessmentResponse.getStatusCode()).isEqualTo(Integer.parseInt(data.get("statusCode")));
			
			System.out.println("Milestone completed for " + customerNo + " please process the payout");
			
			ExtentLogger.info("Milestone completed for " + customerNo + " please process the payout");

		}
		
		ExtentLogger.logRequestAndReponseInReport(writer.toString());

		assertThat(createContractResponse.getStatusCode()).isEqualTo(STATUSCODE200);

	}
}
