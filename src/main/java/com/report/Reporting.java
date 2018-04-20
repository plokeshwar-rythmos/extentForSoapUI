package com.report;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.log4j.Logger;

public class Reporting extends Verify {
	
	public Reporting(Logger logger) {
		super(logger);
	}
	
	final static Logger logger = Logger.getLogger(Reporting.class);
	
	public static void main(String[] args) {
		//String response = "{ \"ProjectId\":\"5a9691288660a3106467aa71\", \"BranchName\":\"\", \"DistributionName\":\"New Distribution\", \"Description\":\"This is a test Distribution\", \"TocPath\":\"\" }";
		//String request = "{    \"modelValidationErrors\":    [             {          \"field\": \"Description\",          \"message\": [\"\"]       },             {          \"field\": \"ProjectName\",          \"message\": [\"\"]       },             {          \"field\": \"RepositoryId\",          \"message\": [\"\"]       },             {          \"field\": \"PublishedPath\",          \"message\":          [             \"\",             \"\"          ]       },             {          \"field\": \"TypeOfContent\",          \"message\": [\"\"]       },             {          \"field\": \"RepositoryName\",          \"message\": [\"\"]       }    ],    \"message\": \"Model validation error occured\",    \"exceptionDetail\": null,    \"httpStatusCode\": null,    \"errorCode\": 0 }";
		
		//	new JsonWriter(new StringWriter().append(request));
		try {
		String tmp = readFileAsString("D:\\CLCS\\Data\\501826570-Response.xml");
		
		
		Reporting r = new Reporting(logger);
		r.initReports(System.getProperty("user.dir"), "new", true);
		
		
		r.createTest("NewTest");
		r.setAuthor("pravin");
		r.setCategory("new");
		r.reportRequestXml(tmp, "Create Member");
		//r.info();
		r.reportFlusher();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	 public static String readFileAsString(String fileName)throws Exception
	  {
	    String data = "";
	    data = new String(Files.readAllBytes(Paths.get(fileName)));
	    return data;
	  }

}
