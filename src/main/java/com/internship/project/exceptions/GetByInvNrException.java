package com.internship.project.exceptions;

public class GetByInvNrException extends RuntimeException {

	private static final long serialVersionUID = 4475081131685835631L;
	String message;

	public GetByInvNrException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String toString() {
		return message;
	}
}
