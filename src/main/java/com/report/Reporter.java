package com.report;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.Markup;
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
	StringBuilder tableAppender;

	static String failStyle = "style=\"background-color: lightcyan;font-size: 17px;color: red;\"";
	static String passStyle = "style=\"background-color: lightcyan;\"";

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
		log.info("PASS : " + removeTags(description));
		test.pass(description);
	}

	/**
	 * This method adds a fail statement to the current test instance.
	 * 
	 * @param description
	 */
	public void fail(String description) {
		test.fail("<div style=\"color: red;\">" + description + "</div>");
	}

	public void failWithBold(String description) {
		fail("<b style=\"font-size: 16px\";>" + description + "</b>");
	}

	public void fail(Throwable ex) {
		failWithBold(ex.getMessage());
		test.fail(ex);
	}

	/**
	 * This method adds a info statement to the current test instance.
	 * 
	 * @param description
	 */
	public void info(String description) {
		log.info("INFO : " + removeTags(description));
		test.info(description);
	}

	public void info(Markup description) {
		log.info("INFO : " + description);

		test.info(description);
	}

	public void reportRequestXml(String xml, String msg) {
		test.info("<details style=\"font-size: 17px;\"><summary style=\"color: brown;\"><b>" + msg
				+ " - REQUEST</b></summary><pre><code><textarea disabled=\"\" style=\"height: 228px;\">" + xml
				+ "</textarea></code></pre></details>");
	}

	public void reportResponseXml(String xml, String msg) {
		test.info("<details style=\"font-size: 17px;\"><summary style=\"color: brown;\"><b>" + msg
				+ " - RESPONSE</b></summary><pre><code><textarea disabled=\"\" style=\"height: 228px;\">" + xml
				+ "</textarea></code></pre></details>");
	}

	public void skip(String description) {
		log.info(removeTags(description));
		test.skip(description);
	}

	public void setAuthor(String author) {
		log.info("Setting Author " + author);
		test.assignAuthor(author);
	}

	public void setCategory(String category) {
		log.info("Setting Category " + category);
		test.assignCategory(category);
	}

	/**
	 *
	 * This method flushes report to the active extent instance.
	 *
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

	public String removeTags(String data) {

		return data.replaceAll("<br>", "").replaceAll("<b>", "").replaceAll("</b>", "");

	}

	public void createTable(List<String> headers) {
		tableAppender = new StringBuilder();
		
		tableAppender.append("<table><tbody><tr>");
		for (String header : headers) {
			tableAppender.append(insertTh(header));
		}
		tableAppender.append("</tr>");
	}

	
	public void closeTable() {
		tableAppender.append("</tbody></table>");
		if (tableAppender.toString().contains("Failed") || tableAppender.toString().contains("fail")) {
			test.fail(tableAppender.toString());
		} else {
			test.info(tableAppender.toString());
		}
	}

	
	public void createTable(String expected, String actual, String elementName, String status) {
		tableAppender = new StringBuilder();
		tableAppender.append("<table><tbody>").append("<tr>"+insertTh(expected)+insertTh(actual)+insertTh(elementName)+insertTh(status)+"</tr>");
	}
	
	public void createTable() {
			createTable("Expected", "Actual", "Element", "Status");
	}
	
	

	public void insertRow(String expected, String actual, String elementName, String status) {

		if (status.equalsIgnoreCase("fail") || status.equalsIgnoreCase("failed")) {
			tableAppender.append("<tr " + failStyle + ">"+insertTd(expected)+insertTd(actual)+ insertTd(elementName)+insertTd(status.toUpperCase())+"</tr>");
		} else {
			tableAppender.append("<tr " + passStyle + ">"+insertTd(expected)+insertTd(actual)+ insertTd(elementName)+insertTd(status)+"</tr>");
		}
	}
	
	public void insertRow(String expected, String actual, String elementName) {

		String temp = verifyEquals(expected, actual);
		if (temp.equalsIgnoreCase("failed")) {
			tableAppender.append("<tr " + failStyle + ">"+insertTd(expected)+insertTd(actual)+ insertTd(elementName)+insertTd(temp.toUpperCase())+"</tr>");
		} else {
			tableAppender.append("<tr " + passStyle + ">"+insertTd(expected)+insertTd(actual)+ insertTd(elementName)+insertTd(temp.toUpperCase())+"</tr>");
		}
	}
	
	public void insertRowVerifyEquals(String expected, String actual, String elementName) {
		if (actual.equals(expected)) {
			tableAppender.append("<tr " + passStyle + ">"+insertTd(expected)+insertTd(actual)+ insertTd(elementName)+insertTd("Passed  - Equal")+"</tr>");
		} else {
			tableAppender.append("<tr " + failStyle + ">"+insertTd(expected)+insertTd(actual)+ insertTd(elementName)+insertTd("Failed - Equal")+"</tr>");
		}
	}
	
	public void insertRowVerifyEqualIgnorecase(String expected, String actual, String elementName) {
		if (expected.equalsIgnoreCase(actual)) {
			tableAppender.append("<tr " + passStyle + ">"+insertTd(expected)+insertTd(actual)+ insertTd(elementName)+insertTd("Passed  - IgnoreCase")+"</tr>");
		} else {
			tableAppender.append("<tr " + failStyle + ">"+insertTd(expected)+insertTd(actual)+ insertTd(elementName)+insertTd("Failed - IgnoreCase")+"</tr>");
		}
	}
	
	public void insertRowVerifyContains(String expected, String actual, String elementName) {
		if (expected.contains(actual)) {
			tableAppender.append("<tr " + passStyle + ">"+insertTd(expected)+insertTd(actual)+ insertTd(elementName)+insertTd("Passed - Contains")+"</tr>");
		} else {
			tableAppender.append("<tr " + failStyle  + ">"+insertTd(expected)+insertTd(actual)+ insertTd(elementName)+insertTd("Failed - Contains")+"</tr>");
		}
	}
	
	public void insertRowVerifyNotContains(String expected, String actual, String elementName) {
		if (!actual.contains(expected)) {
			tableAppender.append("<tr " + passStyle + ">"+insertTd(expected)+insertTd(actual)+ insertTd(elementName)+insertTd("Passed - NotContains")+"</tr>");
		} else {
			tableAppender.append("<tr " + failStyle + ">"+insertTd(expected)+insertTd(actual)+ insertTd(elementName)+insertTd("Failed - NotContains")+"</tr>");
		}
		
	}
	
	
	private String insertTh(String data) {
		return "<th style=\"width: 25%;\">"+data+"</th>";
	}
	
	private String insertTd(String data) {
		return "<td style=\"width: 25%;\">"+data+"</td>";
	}
	
	private String verifyEquals(Object expected, Object actual) {
		
		if(expected instanceof String) {
			if(expected.toString().equalsIgnoreCase(actual.toString()))
				return "Passed";
		}else {
			if(expected.equals(actual))
				return "Passed";
		}
		return "Failed";
	
	}
	
	

	public static void main(String[] args) throws Exception {
		Logger log = Logger.getLogger("Test");
		Reporter report = new Reporter(log);
		report.initReports(System.getProperty("user.dir"), "Testing");
		report.createTest("Awesome");
		report.createTable();
		report.insertRowVerifyEquals("pravinn", "pravin", "firstName");
		report.insertRowVerifyEqualIgnorecase("pravin", "Pravin", "firstName");
		report.insertRowVerifyContains("pravin", "ravi", "firstName");
		report.insertRowVerifyNotContains("pravin", "rini", "firstName");

		report.closeTable();




		System.out.println(report.tableAppender);
	}

}
