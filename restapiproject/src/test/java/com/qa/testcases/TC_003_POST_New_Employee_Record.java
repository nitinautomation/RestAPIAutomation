package com.qa.testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;
import com.qa.utilities.RestUtility;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC_003_POST_New_Employee_Record extends BaseClass {

	String name = RestUtility.getName();
	String salary = RestUtility.getSalary();
	String age = RestUtility.getAge();

	@BeforeClass
	public void add_New_Employee_Record() {
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();

		JSONObject requestParam = new JSONObject();
		requestParam.put("name", name);
		requestParam.put("salary", salary);
		requestParam.put("age", age);

		httpRequest.header("Content_Type", "application/json");
		httpRequest.body(requestParam.toJSONString());
		response = httpRequest.request(Method.POST, "/create");
	}

	@Test
	public void checkBodyResponse() {
		logger.info("****check Response Body*****");
		String responseBody = response.getBody().asString();
		//Assert.assertEquals(responseBody.contains("error"), true);

	}

	@Test
	public void checkStatusCode() {
		logger.info("***Check Status code*****");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	public void checkContentType() {
		logger.info("*****Check content type header*******");
		String contentType = response.getHeader("Content-Type");
		logger.info("Content type" + contentType);
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
	}

	@Test(enabled = false)
	public void checkContentEncoding() {
		logger.info("****Content Encoding*****");
		String contentEncoding = response.getHeader("Content-Encoding");
		logger.info("content encoding header" + contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
	}

}
