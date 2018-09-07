## ExtentForSoapUI.


OpenSource Extent Reporting API built for SoapUI.

### Download

Download latest copy from [HERE](https://github.com/plokeshwar-rythmos/extentForSoapUI/blob/master/jars/SoapReporterV4.6.1.jar) e.g. : SoapReporterV4.6.1.jar

### Requirements
*Jars*

1. SoapReporterV4.*.jar (See the download section)

*Java*

1. JDK 1.8 and above

*SoapUI*

1. SoapUI 5.3.0 and above (Running on JRE 1.8)


### Installation

1. Close the current instance of SoapUI and end the processes if any.
2. Go to Smartbear installation directory and copy all the jars in the lib folder. (See Below) 
	> C:\Program Files (x86)\SmartBear\SoapUI-5.3.0\lib

3. Start the SoapUI application.



### Setting up the scripts in SoapUI

***Project Level Setup Script***

    def groovyUtils=new  com.eviware.soapui.support.GroovyUtils(context)
	def projectPath = groovyUtils.projectPath
	context.projectPath = projectPath
	if(context.reporter == null){
		log.info "PROJECT : Reporter Instance is Null.  Creating a new Instance"
		context.reporter = new com.report.Reporting(log)
		context.reporter.initReports(projectPath+"//Reports", "Project-"+project.name)
	}

***Project Level TearDown Script***
	
    context.reporter=null

***TestSuite Level Setup Script***

	def groovyUtils=new  com.eviware.soapui.support.GroovyUtils(context)
	def projectPath = groovyUtils.projectPath
	context.projectPath = projectPath
    if(context.reporter == null){
        log.info "TS : Reporter Instance is Null.  Creating a new Instance"
        context.reporter = new com.report.Reporting(log)
        context.reporter.initReports(projectPath, "TestSuite-"+testSuite.name)
    }else{
        context.reporter.createParentTest("TestSuite-"+testSuite.name)
    
    } 

***TestSuite Level TearDown Script***

	context.reporter.closeParentTest()

***TestCase Level Setup Script***

	def groovyUtils=new  com.eviware.soapui.support.GroovyUtils(context)
	def projectPath = groovyUtils.projectPath
	context.projectPath = projectPath
    	if(context.reporter == null){
    	    log.info "TC : Reporter Instance is Null.  Creating a new Instance"
    	    context.reporter = new com.report.Reporting(log)
    	    context.reporter.initReports(projectPath, "TestCase-"+testCase.name)
    	}

	context.reporter.createTest("TC-"+testCase.name,testCase.description)

***TestCase Level TearDown Script***

	if(testRunner.status.toString() == "FINISHED"){
		context.reporter.pass("Test Passed")
	}else{
		context.reporter.fail("Test Failed")
		context.reporter.fail(testRunner.reason.toString())
	}
	context.reporter.reportFlusher();

***TestStep Level Script***

	//This method is for reporting Request
	context.reporter.reportRequest("CreateDistribution", context.expand( '${SomeStepName#Request}' ))
	
	//This method is for reporting Request
	context.reporter.reportResponse("CreateDistribution", response)


	def expectedField = "BranchName"
	def expectedMessage = "The BranchName field is required."


	def actualField = resp.modelValidationErrors[0].field
	def actualMessage = resp.modelValidationErrors[0].message[0]

	assert true == context.reporter.verifyEquals(expectedField, actualField, expectedField+" Error Field Triggered", expectedField+" Error Field Did not Trigger")

	assert true == context.reporter.verifyEquals(expectedMessage, actualMessage, expectedMessage+" Error Triggered", expectedMessage+" Error Did Not Trigger")

##Table reporting
context.reporter.createTable("EXPECTED", "ACTUAL", "ELEMENT", "STATUS")
context.reporter.insertRow("abc", "abc", "match", "pass")
context.reporter.insertRow("john", "johnv", "first", "fail")
context.reporter.closeTable()

### Report Example
Click [Here](https://drive.google.com/open?id=14cwUxr58GtS7OCoJcYp3H6L_9BoA_aK7 "Report Example for TestCase") to download the HTML for TestCase Report Example
