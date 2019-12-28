package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC_002_Get_Single_Employee_Data extends BaseClass {

	@BeforeClass
	public void Get_Single_Employee_Data() {
		logger.info("*********TC 01 stated Get all employee data***********");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employee/"+empId);

	}

	@Test
	public void checkEmplyeeData() {
		logger.info("*******Check Response body*******");
		String body = response.getBody().asString();
		 Assert.assertTrue(body.contains(empId));	 
	}
	
	@Test
	public void checkStatusCode() {
		logger.info("*****Check status code******");
		int statusCode = response.getStatusCode();
		logger.info("Status Code "+statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	public void checkStatusLine() {
		logger.info("*****Check status line*****");
		String statusLine = response.getStatusLine();
		logger.info("Status response code  "+statusLine);
	}
	
	@Test
	public void checkContentType() {
		logger.info("*****Check content type header*******");
		String contentType = response.getHeader("Content-Type");
		logger.info("Content type"+contentType);
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
	}
	
	@Test(enabled=false)
	public void checkContentEncoding() {
		logger.info("****Content Encoding*****");
		String contentEncoding = response.getHeader("Content-Encoding");
		logger.info("content encoding header" + contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
	}
	
	
}
