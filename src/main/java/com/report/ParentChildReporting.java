package com.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class ParentChildReporting {
	
	public static void main(String[] args) {
		ParentChildReporting testing = new ParentChildReporting();
		testing.initReports("D:\\CLCS\\ServiceProject", "PravinONE");
		testing.createParentTest("Suite One");
		testing.createChildTest("Suite One Child One");
		testing.childPass("Suite One Child One Passed");
		testing.childInfo("Suite One Child One Information");
		testing.reportFlusher();
		
		testing.createChildTest("Suite One Child Two");
		testing.childPass("Suite One Child Two Passed");
		testing.childInfo("Suite One Child Two Information");
		testing.reportFlusher();
		
		testing.createChildTest("Suite One Child Three");
		testing.childPass("Suite One Child Two Passed");
		testing.childInfo("Suite One Child Two Information");
		testing.reportFlusher();
		
		testing.createParentTest("Suite Two");
		testing.createChildTest("Suite Two Child One");
		testing.childPass("Suite Two Child One Passed");
		testing.childInfo("Suite Two Child One Information");
		testing.reportFlusher();
		
		testing.createChildTest("Suite Two Child Two");
		testing.childPass("Suite Two Child Two Passed");
		testing.childInfo("Suite Two Child Two Information");
		testing.reportFlusher();
	
	}
	
	ExtentHtmlReporter htmlReporter;
	ExtentReports reporter;
	ExtentTest parent;
	ExtentTest child;
	
	public void initReports(String folderPath, String reportName){
		
			htmlReporter = new ExtentHtmlReporter(folderPath + "/"+reportName+".html");
			htmlReporter.config().setReportName(reportName);
			htmlReporter.config().setDocumentTitle(reportName);
			reporter = new ExtentReports();
			reporter.attachReporter(htmlReporter);
	}

 
	public void createParentTest(String testCaseName){
		parent = reporter.createTest(testCaseName);
		
	}
	
	public void createChildTest(String testCaseName){
		child = parent.createNode(testCaseName);
	}
	
	
	public void parentPass(String description){
		parent.pass(description);
	}
	
	public void childPass(String description){
		child.pass(description);
	}
	
	public void parentFail(String description){
		parent.fail(description);
	}
	
	public void childFail(String description){
		child.fail(description);
	}
	
	public void parentInfo(String description){
		parent.info(description);
	}
	
	public void childInfo(String description){
		child.info(description);
	}
	
	
	
	
	public void reportFlusher(){
		//logger.info("Flushing out report");
		reporter.flush();
	}

}
