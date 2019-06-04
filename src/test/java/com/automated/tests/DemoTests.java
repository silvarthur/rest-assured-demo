package com.automated.tests;

import org.junit.runner.RunWith;
import org.junit.BeforeClass;
import org.junit.Test;

import com.tngtech.java.junit.dataprovider.*;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;

import static org.hamcrest.Matchers.*;

@RunWith(DataProviderRunner.class)
public class DemoTests {

	private static RequestSpecification requestSpec;
	private static ResponseSpecification responseSpec;
	
	@DataProvider
	public static Object[][] zipCodesBrazilianCapitals() {
		return new Object[][] {
			{"60000-000", "Fortaleza"},
			{"01000-000", "São Paulo"},
			{"69900-000", "Rio Branco"},
			{"40000-000", "Salvador"},
			{"74000-000", "Goiânia"},
			{"30000-000", "Belo Horizonte"},
			{"50000-000", "Recife"},
			{"90000-000", "Porto Alegre"},
			{"57000-000", "Maceió"},
			{"65000-000", "São Luís"},
			{"70000-000", "Brasília"},
			{"68900-000", "Macapá"},
			{"49000-000", "Aracaju"},
			{"78900-000", "Porto Velho"},
			{"64000-000", "Teresina"},
			{"66000-000", "Belém"},
			{"77000-000", "Palmas"},
			{"69300-000", "Boa Vista"},
			{"20000-000", "Rio de Janeiro"},
			{"58000-000", "João Pessoa"},
			{"78000-000", "Cuiabá"},
			{"88000-000", "Florianópolis"},
			{"59000-000", "Natal"},
			{"80000-000", "Curitiba"},
			{"79000-000", "Campo Grande"},
			{"29000-000", "Vitória"},
			{"69000-000", "Manaus"}
		};
	}
	
	@BeforeClass
	public static void setup() {
		requestSpec = new RequestSpecBuilder().
				setBaseUri("http://api.zippopotam.us/br").
				build();
		
		responseSpec = new ResponseSpecBuilder().
				expectStatusCode(200).
				expectContentType(ContentType.JSON).
				build();
	}
	
	@Test
	@UseDataProvider("zipCodesBrazilianCapitals")
	public void allBrazilianCapitals(String zipCode, String capital) {
		given().
			spec(requestSpec).
			pathParam("zipCode", zipCode).
		when().
			get("/{zipCode}").
		then().
			spec(responseSpec).
		and().
			assertThat().
			body("places[0].'place name'", equalTo(capital));
	}
}
