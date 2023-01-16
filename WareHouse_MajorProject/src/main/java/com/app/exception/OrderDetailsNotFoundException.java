package com.app.exception;

public class OrderDetailsNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public OrderDetailsNotFoundException() {
		super();
	}
	
	public OrderDetailsNotFoundException(String msg) {
		super(msg);
	}
}
