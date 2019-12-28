package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;

public class TC_001_Get_All_Employee extends BaseClass {

	@BeforeClass
	public void Get_All_Employee_Data() {
		logger.info("*********TC 01 stated Get all employee data***********");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employees");
	}

	@Test()
	public void verifyResponseBody() {

		logger.info("*****Checking Response Body********");
		String responseBody = response.getBody().asString();
		logger.info("Response Body-->" + responseBody);
		Assert.assertTrue(responseBody != null);
	}

	@Test
	public void checkResponseTime() {
		long time = response.getTime();
		logger.info("Response time" + time);
		if (time < 2000)
			logger.warn("Response time is more than as expected");
		Assert.assertTrue(time < 40000);
	}

	@Test(enabled=false)
	public void getAllHeaders() {

		Headers allHeaders = response.headers();
		for (io.restassured.http.Header header : allHeaders) {
			System.out.println(header.getName() + " : " + header.getValue());
		}
	}

	@Test
	public void validateStatusCode() {
		logger.info("*****Verify status code**********");
		int statusCode = response.getStatusCode();
		logger.info("Response status code");
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	public void checkstatusLine() {
		logger.info("****Verify status line*********");
		String statusLine = response.getStatusLine();
		logger.info("Response status line" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	@Test
	public void checkServerTypeHeader() {
		logger.info("****Verify header******");
		String serverType = response.getHeader("Server");
		logger.info("Server Type" + serverType);
		Assert.assertEquals(serverType, "nginx/1.16.0");
	}

	@Test
	public void checkContentEncodingHeader() {
		logger.info("****Verify content encoding header*****");
		String contentEncoding = response.getHeader("Content-Encoding");
		logger.info("content encoding header" + contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
	}

}
