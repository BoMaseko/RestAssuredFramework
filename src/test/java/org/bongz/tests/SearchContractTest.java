package org.bongz.tests;

import org.testng.annotations.Test;
import static org.bongz.constants.FrameworkConstants.*;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.*;

import org.bongz.constants.FrameworkConstants;

public class SearchContractTest {
	
	@Test(enabled=false)
	public void getUserProfile() {
		
		Response response = given()
				.baseUri(FrameworkConstants.getBaseURI())
				.pathParam("clientNo", "121397")
				.get("/user-profile-service/rest/v1/userprofile/clientnumber/{clientNo}");
		
		assertThat(response.getStatusCode()).isEqualTo(STATUSCODE200);
		assertThat(response.jsonPath().get("contractStatus")).isEqualTo("Active");
		response.prettyPrint();
		
	}

}
