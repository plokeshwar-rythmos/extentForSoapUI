package com.report;

public class Reporting extends Verify {
	
	/**
	 * This constructor is for creating instance of Reporting and using the inherited methods.
	 */
	public Reporting() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		String response = "{ \"ProjectId\":\"5a9691288660a3106467aa71\", \"BranchName\":\"\", \"DistributionName\":\"New Distribution\", \"Description\":\"This is a test Distribution\", \"TocPath\":\"\" }";
		String request = "{    \"modelValidationErrors\":    [             {          \"field\": \"Description\",          \"message\": [\"\"]       },             {          \"field\": \"ProjectName\",          \"message\": [\"\"]       },             {          \"field\": \"RepositoryId\",          \"message\": [\"\"]       },             {          \"field\": \"PublishedPath\",          \"message\":          [             \"\",             \"\"          ]       },             {          \"field\": \"TypeOfContent\",          \"message\": [\"\"]       },             {          \"field\": \"RepositoryName\",          \"message\": [\"\"]       }    ],    \"message\": \"Model validation error occured\",    \"exceptionDetail\": null,    \"httpStatusCode\": null,    \"errorCode\": 0 }";
		
		
		//	new JsonWriter(new StringWriter().append(request));
		System.out.println();
	
		Reporting r = new Reporting();
		r.initReports(System.getProperty("user.dir"), "new");
		
		
		r.createTest("NewTest");
	
		r.reportRequest("CreateDistribution", request);
		r.reportResponse("CreateDistribution", response);
		
		r.reportFlusher();
		
		
	}

}
