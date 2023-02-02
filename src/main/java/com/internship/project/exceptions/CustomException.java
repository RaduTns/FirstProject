package com.internship.project.exceptions;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 4475081131685835631L;
	String message;

	public CustomException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String toString() {
		return message;
	}
}
