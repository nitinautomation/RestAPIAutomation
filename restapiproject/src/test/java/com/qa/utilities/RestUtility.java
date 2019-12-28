package com.qa.utilities;

import org.apache.commons.lang3.RandomStringUtils;

import com.qa.base.BaseClass;

public class RestUtility extends BaseClass {
	
	public static String getName() {
		String genaratedString = RandomStringUtils.randomAlphabetic(1);
		return ("John"+genaratedString);
	}
	
	public  static String getSalary() {
		String generatedSalary = RandomStringUtils.randomNumeric(5);
		return (generatedSalary);
	}
	
	public static String getAge() {
		String generateAge = RandomStringUtils.random(2);
		return (generateAge);
		
	}
	
	
}
