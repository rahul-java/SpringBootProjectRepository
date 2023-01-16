package com.app.exception;

public class OrderMethodNotFoundException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	public OrderMethodNotFoundException() {
		super();
	}
	
	public OrderMethodNotFoundException(String msg) {
		super(msg);
	}
}
