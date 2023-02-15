package com.internship.project.exceptions;

public class DeleteByInvNrException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2984193455476335417L;
	String message;

	public DeleteByInvNrException(String message) {
		super(message);
	}

	public DeleteByInvNrException(String message, Throwable t) {
		super(message, t);
	}

}
