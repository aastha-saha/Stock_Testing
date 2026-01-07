package com.ibm.climate;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
//import io.restassured.specification.RequestLogSpecification;

public class CityClimateTest {
	
	
	//Validate Response Body Contains Some String
	
	@Test
	public void WeatherMessageBody()
	{
		RestAssured.baseURI = "https://demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Hyderabad");

		// Retrieve the body of the Response
		ResponseBody body = response.getBody();

		// To check for sub string presence get the Response body as a String.
		// Do a String.contains
		String bodyAsString = body.asString();
		Assert.assertEquals(bodyAsString.contains("Delhi") /*Expected value*/, true /*Actual Value*/, "Response body contains Delhi");
	}

	
	//Checking String presence by ignoring alphabet casing
	
	@Test
	public void WeatherMessageBodyIgnoreAlpha()
	{
		RestAssured.baseURI = "https://demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Delhi");

		// Retrieve the body of the Response
		ResponseBody body = response.getBody();

		// To check for substring presence get the Response body as a String.
		// Do a String.contains
		String bodyAsString = body.asString();

		// convert the body into lower case and then do a comparison to ignore casing.
		Assert.assertEquals(bodyAsString.toLowerCase().contains("delhi") /*Expected value*/, true /*Actual Value*/, "Response body contains Delhi");
	}

	
	
	//Extract a Node text from Response using JsonPath
	@Test
	public void VerifyCityInJsonResponse()
	{
		RestAssured.baseURI = "https://demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Hyderabad");

		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();

		// Then simply query the JsonPath object to get a String value of the node
		// specified by JsonPath: City (Note: You should not put $. in the Java code)
		String WS = jsonPathEvaluator.get("Temperature");

		// Let us print the city variable to see what we got
		System.out.println("Temperature received from Response " + WS);

		// Validate the response
		//Assert.assertEquals(WS, "24 Degree celsius", "Correct city name received in the Response");

	}

	
	
  @Test
  public void GetCityClimateDetails() {
	  RestAssured.baseURI="https://demoqa.com/utilities/weather/city";
	  
	  //given condition
	  RequestSpecification httpReq= RestAssured.given();
	  //Response resp= httpReq.request(Method.GET,"/Kolkata");
	  
	  //when condition
	  Response resp=httpReq.request("/Kolkata");
	  
	  int statusCode=resp.getStatusCode();
	  //assertEquals(statusCode,200);
	  System.out.println("the status code is"+statusCode);
	  System.out.println("the status line from the server is"+resp.getStatusLine());
	  String response= resp.body().asString();
	  System.out.println("The response rendered from the server:" + response);
	  
	  Headers headers=resp.headers();
	  for(Header h:headers) {
		  System.out.println("Header name"+h.getName()+"and header value is:"+h.getValue());
	  }
	  
  }

  private void assrtEquals(int statusCode, int i) {
	// TODO Auto-generated method stub
	
  }
}
