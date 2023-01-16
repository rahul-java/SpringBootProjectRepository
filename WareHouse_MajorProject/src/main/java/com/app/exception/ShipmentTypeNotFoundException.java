package com.app.exception;

public class ShipmentTypeNotFoundException extends RuntimeException{

	
	private static final long serialVersionUID = 1L;

	public ShipmentTypeNotFoundException() {
		super();
	}
	
	public ShipmentTypeNotFoundException(String msg) {
		super(msg);
	}
}
