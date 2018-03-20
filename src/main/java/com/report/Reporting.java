package com.report;

import org.apache.log4j.Logger;

public class Reporting extends Verify {
	
	public Reporting(Logger logger) {
		super(logger);
	}
	
	
	public static void main(String[] args) {
		String response = "{ \"ProjectId\":\"5a9691288660a3106467aa71\", \"BranchName\":\"\", \"DistributionName\":\"New Distribution\", \"Description\":\"This is a test Distribution\", \"TocPath\":\"\" }";
		String request = "{    \"modelValidationErrors\":    [             {          \"field\": \"Description\",          \"message\": [\"\"]       },             {          \"field\": \"ProjectName\",          \"message\": [\"\"]       },             {          \"field\": \"RepositoryId\",          \"message\": [\"\"]       },             {          \"field\": \"PublishedPath\",          \"message\":          [             \"\",             \"\"          ]       },             {          \"field\": \"TypeOfContent\",          \"message\": [\"\"]       },             {          \"field\": \"RepositoryName\",          \"message\": [\"\"]       }    ],    \"message\": \"Model validation error occured\",    \"exceptionDetail\": null,    \"httpStatusCode\": null,    \"errorCode\": 0 }";
		
		
		//	new JsonWriter(new StringWriter().append(request));
		System.out.println();
		Logger log = null;
		Reporting r = new Reporting(null);
		r.initReports(System.getProperty("user.dir"), "new", true);
		
		
		r.createTest("NewTest");
		Object o = null;
		r.verifyNotNull(o, "I am not null", "I am null");
		r.reportFlusher();
		
		
	}

}
