package org.bongz.requestbuilder;

import static io.restassured.RestAssured.given;

import org.bongz.constants.FrameworkConstantsWithEagerLoading;
import org.bongz.enums.Constants;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public final class RequestBuilder {

	private RequestBuilder() {
		
	}
	
	public static RequestSpecification getBuilder() {
		return given()
				.log()
				.body()
				//.baseUri(FrameworkConstants.getBaseuri())
				.baseUri(FrameworkConstantsWithEagerLoading.getValue(Constants.BASEURI))
				.contentType(ContentType.JSON);
	}
}
