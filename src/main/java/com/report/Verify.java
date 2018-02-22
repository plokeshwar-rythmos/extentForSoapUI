package com.report;

public class Verify extends Reporter {

	/**
	 * @author Pravin Lokeshwar
	 * 
	 */

	/**
	 * This method verifies object against object and reports messages.
	 * @param expected
	 * @param actual
	 * @param successMessage
	 * @param errorMessage
	 */

	public void verify(Object expected, Object actual, String successMessage, String errorMessage) {

		if (expected.equals(actual)) {
			pass("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "<br> SUCCESS : " + successMessage);
		} else {
			fail("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "<br> ERROR : " + errorMessage);
		}

	}

	/**
	 * This method verifies object against object and reports only error messages.
	 * @param expected
	 * @param actual
	 * @param errorMessage
	 */
	public void verify(Object expected, Object actual, String errorMessage) {

		if (expected.equals(actual)) {
			pass("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "");

		} else {
			fail("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "<br> ERROR : " + errorMessage);
		}

	}

	/**
	 * This method verifies Text against Text and reports messages.
	 * @param expected
	 * @param actual
	 * @param successMessage
	 * @param errorMessage
	 */

	public void verifyText(String expected, String actual, String successMessage, String errorMessage) {

		if (expected.equals(actual)) {
			pass("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "<br> SUCCESS : " + successMessage);
		} else {
			fail("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "<br> ERROR : " + errorMessage);

		}

	}

	/**
	 * This method verifies Text against Text and reports only error messages.
	 * @param expected
	 * @param actual
	 * @param errorMessage
	 */
	public void verifyText(String expected, String actual, String errorMessage) {

		if (expected.equals(actual)) {
			pass("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "");

		} else {
			fail("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "<br> ERROR : " + errorMessage);
		}

	}

	/**
	 * This method verifies Text against Text ignoring case sensitivity and reports messages.
	 * @param expected
	 * @param actual
	 * @param successMessage
	 * @param errorMessage
	 */
	public void verifyTextIgnoreCase(String expected, String actual, String successMessage, String errorMessage) {

		if (expected.equalsIgnoreCase(actual)) {
			pass("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "<br> SUCCESS : " + successMessage);
		} else {
			fail("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "<br> ERROR : " + errorMessage);
		}

	}

	/**
	 * This method verifies Text against Text ignoring case sensitivity and reports error messages.
	 * @param expected
	 * @param actual
	 * @param errorMessage
	 */
	public void verifyTextIgnoreCase(String expected, String actual, String errorMessage) {

		if (expected.equalsIgnoreCase(actual)) {
			pass("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "");

		} else {
			fail("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "<br> ERROR : " + errorMessage);
		}

	}

	/**
	 * This method verifies boolean against boolean and reports messages.
	 * @param expected
	 * @param actual
	 * @param successMessage
	 * @param errorMessage
	 */
	public void verifyBoolean(boolean expected, boolean actual, String successMessage, String errorMessage) {

		if (expected==actual) {
			pass("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "<br> SUCCESS : " + successMessage);
		} else {
			fail("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "<br> ERROR : " + errorMessage);

		}

	}

	/**
	 * This method verifies boolean against boolean and reports error messages.
	 * @param expected
	 * @param actual
	 * @param errorMessage
	 */
	public void verifyBoolean(boolean expected, boolean actual, String errorMessage) {

		if (expected == actual) {
			pass("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "");

		} else {
			fail("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "<br> ERROR : " + errorMessage);
		}

	}
	
	/**
	 * This method verifies if the expected text contains actual text and reports messages.
	 * @param expected
	 * @param actual
	 * @param successMessage
	 * @param errorMessage
	 */

	public void verifyContainsText(String expected, String actual, String successMessage, String errorMessage) {

		if (expected.contains(actual)) {
			pass("<b>Expected</b> : [" + expected + "] Contains <b>Actual</b> : [" + actual + "]<br> SUCCESS : " + successMessage);
		} else {
			fail("<b>Expected</b> : [" + expected + "] Does Not Contain <b>Actual</b> : [" + actual + "]<br> ERROR : " + errorMessage);

		}

	}

	/**
	 * This method verifies if the expected text contains actual text and reports only error messages.
	 * @param expected
	 * @param actual
	 * @param errorMessage
	 */
	public void verifyContainsText(String expected, String actual, String errorMessage) {

		if (expected.contains(actual)) {
			pass("<b>Expected</b> : [" + expected + "] Contains Actual : [" + actual + "]");

		} else {
			fail("<b>Expected</b> : [" + expected + "] Does Not Contain Actual : [" + actual + "]<br> ERROR : " + errorMessage);
		}

	}


}
