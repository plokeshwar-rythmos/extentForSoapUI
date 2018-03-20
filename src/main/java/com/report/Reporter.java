package com.report;

import java.io.File;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentXReporter;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Reporter extends ConfigLoader {

	Logger log;

	public Reporter(Logger logger) {
		super(logger);
		this.log = logger;
	}

	/**
	 * @author Pravin Lokeshwar
	 */

	static String configFile = "extent.properties";
	ExtentHtmlReporter htmlReporter;
	ExtentReports reporter;
	ExtentTest parent;
	ExtentTest test;

	/**
	 * Method creates instance of extent and creates html with reportName and in
	 * folderPath.
	 * 
	 * @param folderPath
	 * @param reportName
	 */
	public void initReports(String folderPath, String reportName) {
		if (!new File(folderPath).exists())
			new File(folderPath).mkdir();

		log.info("Initializing Extent Reporting");
		htmlReporter = new ExtentHtmlReporter(folderPath + "/" + reportName + ".html");
		htmlReporter.config().setReportName(reportName);
		htmlReporter.config().setDocumentTitle(reportName);
		reporter = new ExtentReports();
		reporter.attachReporter(htmlReporter);
	}

	/**
	 * Method creates instance of extent with ExtentX Server connection.
	 * 
	 * @param folderPath
	 * @param reportName
	 * @param mongoDBUrl
	 * @param serverUrl
	 * @param projectName
	 */
	public void initReports(String folderPath, String reportName, String mongoDBUrl, String serverUrl,
			String projectName) {

		if (!new File(folderPath).exists())
			new File(folderPath).mkdir();
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
	 * Method creates instance of extent with ExtentX Server connection.
	 * 
	 * @param folderPath
	 * @param reportName
	 * @param mongoDBUrl
	 * @param serverUrl
	 * @param projectName
	 */
	public void initReports(String folderPath, String reportName, boolean xReporterIndicator) {

		if (!new File(folderPath).exists())
			new File(folderPath).mkdir();

		if (xReporterIndicator && fileExists(folderPath, configFile)) {
			Properties prop = loadProperties(folderPath, configFile);
			ExtentXReporter xReporter = new ExtentXReporter(prop.getProperty("mongoDbUrl"));
			xReporter.config().setServerUrl(prop.getProperty("serverUrl"));
			xReporter.config().setReportName(prop.getProperty("reportName"));
			xReporter.config().setProjectName(prop.getProperty("projectName"));

			htmlReporter = new ExtentHtmlReporter(folderPath + "/" + reportName + ".html");
			htmlReporter.config().setReportName(prop.getProperty("reportName"));
			htmlReporter.config().setDocumentTitle(prop.getProperty("reportName"));
			reporter = new ExtentReports();
			reporter.attachReporter(htmlReporter, xReporter);
		} else {
			initReports(folderPath, reportName);
		}
	}

	/**
	 * Method creates a new instance of test with just testcase name.
	 * 
	 * @param testCaseName
	 */
	public void createTest(String testCaseName) {
		if (parent == null) {
			log.info("Creating test without parent.");

			test = reporter.createTest(testCaseName);
		} else {
			System.out.println("Creating test from parent : " + parent);
			test = parent.createNode(testCaseName);
		}
	}

	/**
	 * Method creates a new instance of test with testcase name and test
	 * description.
	 * 
	 * @param testCaseName
	 * @param description
	 */
	public void createTest(String testCaseName, String description) {
		if (parent == null) {
			log.info("Creating test without parent.");
			test = reporter.createTest(testCaseName, description);
		} else {
			log.info("Creating test from parent : " + parent);
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
		log.info("Creating Parent Test. : " + parent);

	}

	/**
	 * This method closes the current parent test instance.
	 */
	public void closeParentTest() {
		log.info("Closing Parent Test : " + parent);
		if (parent != null)
			parent = null;

	}

	/**
	 * This method adds a pass statement to the current test instance.
	 * 
	 * @param description
	 */
	public void pass(String description) {
		log.info("PASS : " + description);
		test.pass(description);
	}

	/**
	 * This method adds a fail statement to the current test instance.
	 * 
	 * @param description
	 */
	public void fail(String description) {
		log.fatal("FAIL : " + description);
		test.fail("<div style=\"color: red;\">" + description + "</div>");
	}

	/**
	 * This method adds a info statement to the current test instance.
	 * 
	 * @param description
	 */
	public void info(String description) {
		log.info("INFO : " + description);
		test.info(description);
	}

	/**
	 * This method flushes report to the active extent instance.
	 */
	public void reportFlusher() {
		log.info("Flushing the HTML report.");
		reporter.flush();
	}

	public void reportRequestResponse(String shortMsg, Object data) {
		JsonObject json = new JsonParser().parse(data.toString()).getAsJsonObject();
		String rq = new GsonBuilder().setPrettyPrinting().create().toJson(json);

		test.info("<details style=\"font-size: 17px;\"><summary style=\"color: brown;\"><b>" + shortMsg
				+ "</b></summary><pre>" + rq + "</pre></details>");
	}

	public void reportRequest(String shortMsg, Object request) {
		log.info("Adding Request to the Report.");
		reportRequestResponse(shortMsg + "-Request", request);
	}

	public void reportResponse(String shortMsg, Object response) {
		log.info("Adding Response to the Report.");
		reportRequestResponse(shortMsg + "-Response", response);
	}

}
