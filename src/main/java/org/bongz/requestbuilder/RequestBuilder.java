package org.bongz.requestbuilder;

import static io.restassured.RestAssured.given;

import org.aeonbits.owner.ConfigFactory;
import org.bongz.configs.FrameworkConfig;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public final class RequestBuilder {

	private RequestBuilder() {}

	public static RequestSpecification getBuilder() {

		FrameworkConfig configURL = ConfigFactory.create(FrameworkConfig.class);
		return given()
				.log()
				.all()
				//.baseUri(FrameworkConstants.getBaseuri())
				//.baseUri(FrameworkConstantsWithEagerLoading.getValue(Constants.BASEURI))
				.baseUri(configURL.url())
				.contentType(ContentType.JSON);
	}

	public static String eventTxnURL() {
		String  url= "/event-management-service/rest/v1/eventTransaction";
		return url;
	}
}
