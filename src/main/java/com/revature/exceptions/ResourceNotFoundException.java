package com.revature.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4666562761269395553L;
	
	public ResourceNotFoundException(String message) {
		super(message);
	}
	
}
