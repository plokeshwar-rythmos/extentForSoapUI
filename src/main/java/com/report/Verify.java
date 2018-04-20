package com.report;

import org.apache.log4j.Logger;

import com.exceptions.AssertionException;

public class Verify extends Reporter {

	public Verify(Logger logger) {
		super(logger);
	}

	/**
	 * @author Pravin Lokeshwar
	 * 
	 */

	/**
	 * This method verifies two objects and returns true if equal and reports
	 * message.
	 * 
	 * @param expected
	 * @param actual
	 * @param successMessage
	 * @param errorMessage
	 * @return True if objects equal
	 */

	public boolean verifyEquals(Object expected, Object actual, String successMessage, String errorMessage) {

		if (expected.equals(actual)) {
			pass("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "<br> SUCCESS : "
					+ successMessage);
			return true;
		} else {
			
		//	fail("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "<br> ERROR : " + errorMessage);
			fail(new AssertionException("Expected : "+expected+", Actual : "+actual+", Message : "+errorMessage));
		}

		return false;

	}

	/**
	 * This method verifies two objects and returns true if not equal and
	 * reports message.
	 * 
	 * @param expected
	 * @param actual
	 * @param successMessage
	 * @param errorMessage
	 * @return True if objects not equal
	 */

	public boolean verifyNotEquals(Object expected, Object actual, String successMessage, String errorMessage) {

		if (!expected.equals(actual)) {
			pass("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "<br> SUCCESS : "
					+ successMessage);
			return true;
		} else {
		//	fail("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "<br> ERROR : " + errorMessage);
			fail(new AssertionException("Expected : "+expected+", Actual : "+actual+", Message : "+errorMessage));
		}

		return false;

	}

	/**
	 * This method verifies if the object is null and returns true if null and
	 * reports message.
	 * 
	 * @param actual
	 * @param successMessage
	 * @param errorMessage
	 * @return True if object is null
	 */
	public boolean verifyIsNull(Object actual, String successMessage, String errorMessage) {
		String expected = "null";
		if (actual == null) {
			pass("<b>Expected</b> : null<br> <b>Actual</b> : " + actual + "<br> SUCCESS : " + successMessage);
			return true;
		} else {
			//fail("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "<br> ERROR : " + errorMessage);
			fail(new AssertionException("Expected : "+expected+", Actual : "+actual+", Message : "+errorMessage));
		}

		return false;

	}

	/**
	 * This method verifies if the object is not null and returns true if not
	 * null and reports message.
	 * 
	 * @param actual
	 * @param successMessage
	 * @param errorMessage
	 * @return True if object is not null
	 */

	public boolean verifyNotNull(Object actual, String successMessage, String errorMessage) {
		String expected = "Not Null";
		if (actual != null) {
			pass("<b>Expected</b> : NOT NULL<br> <b>Actual</b> : " + actual + "<br> SUCCESS : " + successMessage);
			return true;
		} else {
			//fail("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "<br> ERROR : " + errorMessage);
			fail(new AssertionException("Expected : "+expected+", Actual : "+actual+", Message : "+errorMessage));
		}

		return false;

	}

	/**
	 * This method verifies if the object is true and returns true if true and
	 * reports message.
	 * 
	 * @param actual
	 * @param successMessage
	 * @param errorMessage
	 * @return True if object is true
	 */
	public boolean verifyTrue(boolean actual, String successMessage, String errorMessage) {
		boolean expected = true;
		if (actual) {
			pass("<b>Expected</b> : TRUE<br> <b>Actual</b> : " + actual + "<br> SUCCESS : " + successMessage);
			return true;
		} else {
			fail(new AssertionException("Expected : "+expected+", Actual : "+actual+", Message : "+errorMessage));
		}

		return false;

	}

	/**
	 * 
	 * This method verifies if the object is false and returns true if false and
	 * reports message.
	 * 
	 * @param actual
	 * @param successMessage
	 * @param errorMessage
	 * @return True if object is false
	 */

	public boolean verifyFalse(boolean actual, String successMessage, String errorMessage) {
		boolean expected = false;
		if (!actual) {
			pass("<b>Expected</b> : FALSE<br> <b>Actual</b> : " + actual + "<br> SUCCESS : " + successMessage);
			return true;
		} else {
		//	fail("<b>Expected</b> : FALSE<br> <b>Actual</b> : " + actual + "<br> ERROR : " + errorMessage);
			fail(new AssertionException("Expected : "+expected+", Actual : "+actual+", Message : "+errorMessage));
		}
		

		return false;

	}

	/**
	 * This method verifies object against object and returns true if equal and
	 * reports messages.
	 * 
	 * @param expected
	 * @param actual
	 * @param successMessage
	 * @param errorMessage
	 * @return True if both objects are equal.
	 */

	public boolean verify(Object expected, Object actual, String successMessage, String errorMessage) {

		if (expected.equals(actual)) {
			pass("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "<br> SUCCESS : "
					+ successMessage);
			return true;
		} else {
		//	fail("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "<br> ERROR : " + errorMessage);
			fail(new AssertionException("Expected : "+expected+", Actual : "+actual+", Message : "+errorMessage));
		}

		return false;

	}

	/**
	 * This method verifies object against object and returns true if equal and
	 * reports only error messages.
	 * 
	 * @param expected
	 * @param actual
	 * @param errorMessage
	 * @return True if both objects are equal.
	 * 
	 */
	public boolean verify(Object expected, Object actual, String errorMessage) {

		if (expected.equals(actual)) {
			pass("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "");
			return true;

		} else {
			//fail("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "<br> ERROR : " + errorMessage);
			fail(new AssertionException("Expected : "+expected+", Actual : "+actual+", Message : "+errorMessage));
		}
		return false;
	}

	/**
	 * This method verifies Text against Text and reports messages.
	 * 
	 * @param expected
	 * @param actual
	 * @param successMessage
	 * @param errorMessage
	 * @return True if both Strings are equal.
	 * 
	 */

	public boolean verifyText(String expected, String actual, String successMessage, String errorMessage) {

		if (expected.equals(actual)) {
			pass("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "<br> SUCCESS : "
					+ successMessage);
			return true;
		} else {
			//fail("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "<br> ERROR : " + errorMessage);
			fail(new AssertionException("Expected : "+expected+", Actual : "+actual+", Message : "+errorMessage));

		}
		return false;
	}

	/**
	 * This method verifies Text against Text and reports only error messages.
	 * 
	 * @param expected
	 * @param actual
	 * @param errorMessage
	 * @return True if both Strings are equal.
	 */
	public boolean verifyText(String expected, String actual, String errorMessage) {

		if (expected.equals(actual)) {
			pass("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "");
			return true;

		} else {
			//fail("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "<br> ERROR : " + errorMessage);
			fail(new AssertionException("Expected : "+expected+", Actual : "+actual+", Message : "+errorMessage));
		}

		return false;

	}

	/**
	 * This method verifies Text against Text ignoring case sensitivity and
	 * reports messages.
	 * 
	 * @param expected
	 * @param actual
	 * @param successMessage
	 * @param errorMessage
	 * @return True if both Strings are equal.
	 * 
	 */
	public boolean verifyTextIgnoreCase(String expected, String actual, String successMessage, String errorMessage) {

		if (expected.equalsIgnoreCase(actual)) {
			pass("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "<br> SUCCESS : "
					+ successMessage);
			return true;
		} else {
			//fail("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "<br> ERROR : " + errorMessage);
			fail(new AssertionException("Expected : "+expected+", Actual : "+actual+", Message : "+errorMessage));
		}

		return false;

	}

	/**
	 * This method verifies Text against Text ignoring case sensitivity and
	 * reports error messages.
	 * 
	 * @param expected
	 * @param actual
	 * @param errorMessage
	 * @return True if both Strings are equal.
	 * 
	 */
	public boolean verifyTextIgnoreCase(String expected, String actual, String errorMessage) {

		if (expected.equalsIgnoreCase(actual)) {
			pass("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "");
			return true;

		} else {
			//fail("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "<br> ERROR : " + errorMessage);
			fail(new AssertionException("Expected : "+expected+", Actual : "+actual+", Message : "+errorMessage));
		}
		return false;
	}

	/**
	 * This method verifies boolean against boolean and reports messages.
	 * 
	 * @param expected
	 * @param actual
	 * @param successMessage
	 * @param errorMessage
	 * @return True if both objects are equal.
	 * 
	 */
	public boolean verifyBoolean(boolean expected, boolean actual, String successMessage, String errorMessage) {

		if (expected == actual) {
			pass("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "<br> SUCCESS : "
					+ successMessage);
			return true;
		} else {
			//fail("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "<br> ERROR : " + errorMessage);
			fail(new AssertionException("Expected : "+expected+", Actual : "+actual+", Message : "+errorMessage));

		}
		return false;

	}

	/**
	 * This method verifies boolean against boolean and reports error messages.
	 * 
	 * @param expected
	 * @param actual
	 * @param errorMessage
	 * @return True if both objects are equal.
	 * 
	 */
	public boolean verifyBoolean(boolean expected, boolean actual, String errorMessage) {

		if (expected == actual) {
			pass("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "");
			return true;
		} else {
			//fail("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "<br> ERROR : " + errorMessage);
			fail(new AssertionException("Expected : "+expected+", Actual : "+actual+", Message : "+errorMessage));
		}

		return false;

	}

	/**
	 * This method verifies if the expected text contains actual text and
	 * reports messages.
	 * 
	 * @param expected
	 * @param actual
	 * @param successMessage
	 * @param errorMessage
	 * @return True if expected contains actual.
	 * 
	 */

	public boolean verifyContainsText(String expected, String actual, String successMessage, String errorMessage) {

		if (actual.contains(expected)) {
			pass("<b>Expected</b> : [" + expected + "] is available in <b>Actual</b> : [" + actual + "]<br> SUCCESS : "
					+ successMessage);
			return true;
		} else {
			//fail("<b>Expected</b> : [" + expected + "] is not available in <b>Actual</b> : [" + actual	+ "]<br> ERROR : " + errorMessage);
			fail(new AssertionException("Expected : "+expected+" Not Available in Actual : "+actual+". Message : "+errorMessage));

		}
		return false;
	}

	/**
	 * This method verifies if the expected text contains actual text and
	 * reports only error messages.
	 * 
	 * @param expected
	 * @param actual
	 * @param errorMessage
	 * @return True if expected contains actual.
	 */
	public boolean verifyContainsText(String expected, String actual, String errorMessage) {

		if (actual.contains(expected)) {
			pass("<b>Expected</b> : [" + expected + "] is available in <b>Actual</b> : [" + actual + "]");
			return true;
		} else {
			//fail("<b>Expected</b> : [" + expected + "] is not available in <b>Actual</b> : [" + actual+ "]<br> ERROR : " + errorMessage);
			fail(new AssertionException("Expected : "+expected+" Not Available in Actual : "+actual+". Message : "+errorMessage));

		}
		return false;

	}

	public boolean verifyGreaterThan(int expected, int actual, String successMessage, String errorMessage) {

		if (actual > expected) {
			pass("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "<br> SUCCESS : "
					+ successMessage);
			return true;
		} else {
			//fail("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "<br> ERROR : " + errorMessage);
			fail(new AssertionException("Expected : "+expected+", Actual : "+actual+", Message : "+errorMessage));
		}

		return false;

	}

	public boolean verifyLesserThan(int expected, int actual, String successMessage, String errorMessage) {

		if (actual < expected) {
			pass("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "<br> SUCCESS : "
					+ successMessage);
			return true;
		} else {
			//fail("<b>Expected</b> : " + expected + "<br> <b>Actual</b> : " + actual + "<br> ERROR : " + errorMessage);
			fail(new AssertionException("Expected : "+expected+", Actual : "+actual+", Message : "+errorMessage));
		}

		return false;

	}
	

}
