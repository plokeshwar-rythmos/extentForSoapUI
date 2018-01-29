package com.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentXReporter;


public class Reporting {
	
	static boolean flag = false;
	
	public static void main(String[] args) {
		
		String mongoURL = "192.168.1.186:27017";
		String serverURL = "http://192.168.1.186:1337";
		String projectName = "Test";
		
		Reporting testing = new Reporting();
		testing.initReports("D:\\CLCS\\ServiceProject", "PravinONE", mongoURL, serverURL, projectName);
		
		testing.createParentTest("Parent");
		testing.createTest("Testing");
		
		testing.pass("Passed");
		testing.reportFlusher();
		
		/*if(flag){
		testing.createParentTest("Suite One");
		}else{
			testing.createTest("Alone Test Case");
		}
		
		testing.createChildTest("Suite One Child One");
		testing.pass("Suite One Child One Passed");
		testing.info("Suite One Child One Information");
		testing.reportFlusher();
		
		
		testing.createChildTest("Suite One Child Two");
		testing.pass("Suite One Child Two Passed");
		testing.info("Suite One Child Two Information");
		testing.reportFlusher();
		
		
		testing.createChildTest("Suite One Child Three");
		testing.pass("Suite One Child Two Passed");
		testing.info("Suite One Child Two Information");
		testing.reportFlusher();
		
	//	testing.createParentTest("Suite Two");
		testing.createChildTest("Suite Two Child One");
		testing.pass("Suite Two Child One Passed");
		testing.info("Suite Two Child One Information");
		testing.reportFlusher();
	
		testing.createChildTest("Suite Two Child Two");
		testing.pass("Suite Two Child Two Passed");
		testing.info("Suite Two Child Two Information");
		testing.fail("Suite Two Child Two Failure");
		testing.reportFlusher();*/
	
	}
	
	ExtentHtmlReporter htmlReporter;
	ExtentReports reporter;
	ExtentTest parent;
	ExtentTest test;
	
	public void initReports(String folderPath, String reportName){
		
			htmlReporter = new ExtentHtmlReporter(folderPath + "/"+reportName+".html");
			htmlReporter.config().setReportName(reportName);
			htmlReporter.config().setDocumentTitle(reportName);
			reporter = new ExtentReports();
			reporter.attachReporter(htmlReporter);
	}
	
	public void initReports(String folderPath, String reportName, String mongoDBUrl, String serverUrl, String projectName){
		ExtentXReporter xReporter = new ExtentXReporter(mongoDBUrl);
		xReporter.config().setServerUrl(serverUrl);
		xReporter.config().setReportName(reportName);
		xReporter.config().setProjectName(projectName);

		htmlReporter = new ExtentHtmlReporter(folderPath + "/"+reportName+".html");
		htmlReporter.config().setReportName(reportName);
		htmlReporter.config().setDocumentTitle(reportName);
		reporter = new ExtentReports();
		reporter.attachReporter(htmlReporter, xReporter);
}
	
	

	public void createTest(String testCaseName){
		if(parent == null){
			test = reporter.createTest(testCaseName);
		}else{
			test = parent.createNode(testCaseName);
		}
	}
	
	
	
	public void createTest(String testCaseName, String description){
		if(parent == null){
			test = reporter.createTest(testCaseName, description);
		}else{
			test = parent.createNode(testCaseName, description);
		}
	}
 
	public void createParentTest(String testCaseName){
		parent = reporter.createTest(testCaseName);
		
	}
	
	public void closeParentTest(){
		if(parent != null)
			parent = null;
		
	}
	
	public void pass(String description){
		test.pass(description);
	}
	
	
	
	public void fail(String description){
		test.fail(description);
	}
	
	
	public void info(String description){
		test.info(description);
	}
	
	
	public void reportFlusher(){
		reporter.flush();
	}

}
