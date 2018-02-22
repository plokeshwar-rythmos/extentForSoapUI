package com.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentXReporter;

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
			test = reporter.createTest(testCaseName);
		} else {
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
			test = reporter.createTest(testCaseName, description);
		} else {
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

	}

	/**
	 * This method closes the current parent test instance.
	 */
	public void closeParentTest() {
		if (parent != null)
			parent = null;

	}

	/**
	 * This method adds a pass statement to the current test instance.
	 * @param description
	 */
	public void pass(String description) {
		test.pass(description);
	}

	/**
	 * This method adds a fail statement to the current test instance.
	 * @param description
	 */
	public void fail(String description) {
		test.fail("<div style=\"color: red;\">" + description+"</div>");
	}

	/**
	 * This method adds a info statement to the current test instance.
	 * @param description
	 */
	public void info(String description) {
		test.info(description);
	}

	/**
	 * This method flushes report to the active extent instance.
	 */
	public void reportFlusher() {
		reporter.flush();
	}

}
