package com.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class Reporting {
	
	public static void main(String[] args) {
		Reporting testing = new Reporting();
		testing.initReports("D:\\CLCS\\ServiceProject", "PravinONE");
		testing.createTest("Awesome Test");
		testing.pass("Passed");
		testing.reportFlusher();
	}
	
	ExtentHtmlReporter htmlReporter;
	ExtentReports reporter;
	ExtentTest tester;
	
	public void initReports(String folderPath, String reportName){
		
			htmlReporter = new ExtentHtmlReporter(folderPath + "/"+reportName+".html");
			htmlReporter.config().setReportName(reportName);
			htmlReporter.config().setDocumentTitle(reportName);
			reporter = new ExtentReports();
			reporter.attachReporter(htmlReporter);
	}

 
	public void createTest(String testCaseName){
		tester = reporter.createTest(testCaseName);
	}
	
	public void pass(String description){
		tester.pass(description);
	}
	
	public void fail(String description){
		//logger.info(description+" Failed");
		
		tester.fail(description);
	}
	
	public void info(String description){
		//logger.info(description+" INFORMATION");
		tester.info(description);
	}
	
	public void reportFlusher(){
		//logger.info("Flushing out report");
		reporter.flush();
	}

}
