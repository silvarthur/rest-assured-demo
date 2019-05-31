package com.automated.tests;

import org.junit.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DemoTests {

	@Test
	public void test() {
		given().
			log().all().
		when().
			get("http://api.zippopotam.us/us/90210").
		then().
			assertThat().
			contentType(ContentType.JSON).
			and().
			statusCode(200).
			and().
			body("places[0].state", equalTo("California")).
			and().
			log().body();
	}

}
