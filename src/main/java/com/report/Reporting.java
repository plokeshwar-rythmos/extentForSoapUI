package com.report;

public class Reporting extends Verify {
	
	/**
	 * This constructor is for creating instance of Reporting and using the inherited methods.
	 */
	public Reporting() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		Reporting r = new Reporting();
		r.initReports(System.getProperty("user.dir"), "new");
		
		String c = "1516622358596";
		String s = "1516622358596";
		
		r.createTest("NewTest");
		r.verify(c, s, c+" did not match "+s);
		r.reportFlusher();
		
	}

}
