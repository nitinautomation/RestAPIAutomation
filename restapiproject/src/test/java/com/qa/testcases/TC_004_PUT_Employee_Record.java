package com.qa.testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;
import com.qa.utilities.RestUtility;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC_004_PUT_Employee_Record extends BaseClass {

	public String name = RestUtility.getName();
	public String age = RestUtility.getAge();
	public String salary = RestUtility.getSalary();

	@BeforeClass
	public void putEmployeeRecord() {

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		;

		JSONObject requestParam = new JSONObject();
		requestParam.put("name", name);
		requestParam.put("age", age);
		requestParam.put("salary", salary);

		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParam.toJSONString());
		response = httpRequest.request(Method.PUT, "/update/" + empId);
	}

	@Test
	public void checkBody() {
		logger.info("*****Validate Put body******");
		String responseBody = response.getBody().asString();
		Assert.assertTrue(responseBody.contains(name));
	}

	@Test
	public void checkStatusCode() {
		logger.info("*****Check Response status code*****");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(200, statusCode);

	}

	@Test(enabled=false)
	public void checkContentEncoding() {
		logger.info("*****Check eocoding status code*******");
		String contentEncoding = response.getHeader("Content-Encoding");
		Assert.assertEquals(contentEncoding, "gzip");
	}
	


	
}
