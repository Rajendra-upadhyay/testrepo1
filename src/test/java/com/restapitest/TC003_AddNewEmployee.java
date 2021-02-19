package com.restapitest;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_AddNewEmployee {

	@Test(dataProvider = "addEmployee")
	public void addNewEmployee(String name, String sal, String age) {
		RestAssured.baseURI = "http://dummy.restapiexample.com";

		RequestSpecification httpReq = RestAssured.given();

		httpReq.header("Content-Type", "application/json");

		JSONObject reqPayload = new JSONObject();
		reqPayload.put("name", name);
		reqPayload.put("salary", sal);
		reqPayload.put("age", age);

		httpReq.body(reqPayload.toJSONString());

		Response response = httpReq.request(Method.POST, "/api/v1/create");

		System.out.println("Response is::" + response.getBody().asString());

	}

	@DataProvider(name = "addEmployee")
	public Object[][] getEmployeeData() {

		return new Object[][] { { "test39", "1339", "39" }, { "test40", "1340", "40" } };
	}

}
