package com.qa.reports;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter {

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;

	public void onStarts(ITestContext testContext) {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/myReport11.html");
		htmlReporter.config().setDocumentTitle("API autmation");
		htmlReporter.config().setReportName("REST API Report");
		htmlReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "localhost");
		extent.setSystemInfo("Enviroment", "QA");
		extent.setSystemInfo("User", "Nitin");
	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestName());
		test.log(Status.PASS, "Test case is passed " + result.getName());
	}

	public void ontestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "Test case is fail " + result.getName());
		
	}

	public void ontestSkip(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test case is skip " + result.getName());
		
	}
	
	public void onFinish(ITestResult result) {
		extent.flush();
		
	}
}
