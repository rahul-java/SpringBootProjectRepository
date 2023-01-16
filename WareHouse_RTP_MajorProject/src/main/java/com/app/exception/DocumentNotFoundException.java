package com.app.exception;

public class DocumentNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public DocumentNotFoundException() {
		super();
	}
	
	public DocumentNotFoundException(String msg) {
		super(msg);
	}
}
