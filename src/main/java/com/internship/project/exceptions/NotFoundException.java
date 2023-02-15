package com.internship.project.exceptions;

public class NotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2859486589900885460L;

	public NotFoundException(String message, Throwable t) {
		super(message, t);
	}

}
