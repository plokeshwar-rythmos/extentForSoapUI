package com.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentXReporter;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Reporter {
	/**
	 * @author Pravin Lokeshwar
	 */

	ExtentHtmlReporter htmlReporter;
	ExtentReports reporter;
	ExtentTest parent;
	ExtentTest test;

	/**
	 * Method creates instance of extent and creates html with reportName and in folderPath.
	 * @param folderPath
	 * @param reportName
	 */
	public void initReports(String folderPath, String reportName) {
		System.out.println("Initializing Extent Reporting");
		htmlReporter = new ExtentHtmlReporter(folderPath + "/" + reportName + ".html");
		htmlReporter.config().setReportName(reportName);
		htmlReporter.config().setDocumentTitle(reportName);
		reporter = new ExtentReports();
		reporter.attachReporter(htmlReporter);
	}

	/**
	 * Method creates instance of extent with ExtentX Server connection.
	 * @param folderPath
	 * @param reportName
	 * @param mongoDBUrl
	 * @param serverUrl
	 * @param projectName
	 */
	public void initReports(String folderPath, String reportName, String mongoDBUrl, String serverUrl,
			String projectName) {
		ExtentXReporter xReporter = new ExtentXReporter(mongoDBUrl);
		xReporter.config().setServerUrl(serverUrl);
		xReporter.config().setReportName(reportName);
		xReporter.config().setProjectName(projectName);

		htmlReporter = new ExtentHtmlReporter(folderPath + "/" + reportName + ".html");
		htmlReporter.config().setReportName(reportName);
		htmlReporter.config().setDocumentTitle(reportName);
		reporter = new ExtentReports();
		reporter.attachReporter(htmlReporter, xReporter);
	}

	/**
	 * Method creates a new instance of test with just testcase name.
	 * @param testCaseName
	 */
	public void createTest(String testCaseName) {
		if (parent == null) {
			System.out.println("Creating test without parent.");
			test = reporter.createTest(testCaseName);
		} else {
			System.out.println("Creating test from parent : "+parent);
			test = parent.createNode(testCaseName);
		}
	}

	/**
	 * Method creates a new instance of test with testcase name and test description.
	 * @param testCaseName
	 * @param description
	 */
	public void createTest(String testCaseName, String description) {
		if (parent == null) {
			System.out.println("Creating test without parent.");
			test = reporter.createTest(testCaseName, description);
		} else {
			System.out.println("Creating test from parent : "+parent);
			test = parent.createNode(testCaseName, description);
		}
	}

	/**
	 * This method creates a new instance of parent test with testcase name.
	 * 
	 * @param testCaseName
	 */
	public void createParentTest(String testCaseName) {
		parent = reporter.createTest(testCaseName);
		System.out.println("Creating Parent Test. : "+parent);

	}

	/**
	 * This method closes the current parent test instance.
	 */
	public void closeParentTest() {
		System.out.println("Closing Parent Test : "+parent);
		if (parent != null)
			parent = null;

	}

	/**
	 * This method adds a pass statement to the current test instance.
	 * @param description
	 */
	public void pass(String description) {
		System.out.println("PASS : "+description);
		test.pass(description);
	}

	/**
	 * This method adds a fail statement to the current test instance.
	 * @param description
	 */
	public void fail(String description) {
		System.err.println("FAIL : "+description);
		test.fail("<div style=\"color: red;\">" + description+"</div>");
	}

	/**
	 * This method adds a info statement to the current test instance.
	 * @param description
	 */
	public void info(String description) {
		System.out.println("INFO : "+description);
		test.info(description);
	}

	/**
	 * This method flushes report to the active extent instance.
	 */
	public void reportFlusher() {
		System.out.println("Flushing the HTML report.");
		reporter.flush();
	}
	
	public void reportRequestResponse(String shortMsg, Object data){
		JsonObject json = new JsonParser().parse(data.toString()).getAsJsonObject();
		String rq = new GsonBuilder().setPrettyPrinting().create().toJson(json);
		
		test.info("<details style=\"font-size: 17px;\"><summary style=\"color: brown;\"><b>" + shortMsg + "</b></summary><pre>" + rq+ "</pre></details>");
	}
	
	public void reportRequest(String shortMsg, Object request){
		System.out.println("Adding Request to the Report.");
		reportRequestResponse(shortMsg+"-Request", request);
	}
	
	public void reportResponse(String shortMsg, Object response){
		System.out.println("Adding Response to the Report.");
		reportRequestResponse(shortMsg+"-Response", response);
	}

}
