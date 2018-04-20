package com.exceptions;

public class FailedStepException extends RuntimeException{

	public FailedStepException(String message) {
		super(message);
	}
}
