package com.restapitest;

import java.util.Iterator;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC002_CreateUser {

	//This test cases is to learn how to configure and send post request and validate response code, body content and headers
	
	/*
	 * What we need to verify in response, following sections/fields we can check in response
	 * Status Code
	 * Status Line
	 * Response Time and Size
	 * Headers
	 * Response Body and it's content
	 */
	@Test
	public void postCreateUser() {
		RestAssured.baseURI="https://reqres.in/";
		
		RequestSpecification httpRequest=RestAssured.given();
		
		JSONObject requestPayload=new JSONObject();
		
		requestPayload.put("name", "Rajendra");
		requestPayload.put("job", "QA");
		
		httpRequest.header("Content-Type","application/json");
		
		/*httpRequest.body("{\r\n" + 
				"    \"name\": \"rmorpheus\",\r\n" + 
				"    \"job\": \"leader\"\r\n" + 
				"}");
		*/
		
		httpRequest.body(requestPayload.toJSONString());
		Response response=httpRequest.request(Method.POST,"/api/users");
		
		String responseBody=response.getBody().asString();
		
		System.out.println("Response body is :::"+responseBody);
		
		System.out.println("Name value is ::"+response.jsonPath().get("name"));
		System.out.println("JOB value is ::"+response.jsonPath().get("job"));
		
		Assert.assertEquals(201, response.getStatusCode());
		
		System.out.println(response.getHeader("Content-Type"));
		
		Headers head= response.getHeaders();
		
		Iterator<Header> iterator=head.iterator();
		for (Header header : head) {
			System.out.println(header);
		}
		
		Assert.assertEquals(response.getHeader("Server"), "cloudflare");
		
		JsonPath jp=response.jsonPath();
		System.out.println(jp.getString("name"));
		System.out.println(jp.getString("job"));
		System.out.println(jp.getString("id"));
		System.out.println(jp.getString("createdAt"));
		
	}
}
