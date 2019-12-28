package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC_005_DELETE_Employee_Record extends BaseClass {

	@BeforeClass
	public void deleteEmployeeRecord() {

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.DELETE, "/delete/" + empId);
	}

	@Test
	public void checkResponseBody() {
		String responseBody = response.getBody().toString();
		//Assert.assertTrue(!responseBody.contains(empId));
		System.out.println(responseBody);
	}

}
