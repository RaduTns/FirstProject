package com.internship.project.exceptions;

public class GetAllException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2893891006543104955L;
	String message;

	public GetAllException(String message) {
		super(message);
	}

	public GetAllException(String message, Throwable t) {
		super(message, t);
	}

}
