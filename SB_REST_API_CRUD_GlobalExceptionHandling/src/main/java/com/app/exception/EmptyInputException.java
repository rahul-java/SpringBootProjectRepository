package com.app.exception;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class EmptyInputException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	private String errorMsg;
	
	public EmptyInputException() {
		super();
	}
	public EmptyInputException(String errorCode,String errorMsg) {
		super();
		this.errorCode=errorCode;
		this.errorMsg=errorMsg;
	}
}
