package com.automated.tests;

import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DemoTests {

	@Test
	public void test() {
		given().
		when().
			get("http://api.zippopotam.us/us/90210").
		then().
			assertThat().
			body("places[0].state", equalTo("California"));
	}

}
