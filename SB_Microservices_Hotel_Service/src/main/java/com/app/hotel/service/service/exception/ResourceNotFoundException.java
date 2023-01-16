package com.app.hotel.service.service.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException() {
		super("Resource NOT Found");
	}
	
	public ResourceNotFoundException(String msg)
	{
		super(msg);
	}
}
