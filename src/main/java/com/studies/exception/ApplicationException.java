package com.studies.exception;

public class ApplicationException extends Exception {

	private static final long serialVersionUID = -2007802213067194475L;
	
	private String code;
	
	public ApplicationException(String message) {
		super(message);
	}

	public ApplicationException(String code, String message) {
		super(message);
		this.code = code;
	}

	public ApplicationException(String code, String message, Exception cause) {
		super(message, cause);
		this.code = code;
	}
	
	public ApplicationException(String message, Exception cause) {
		super(message, cause);
	}

	public String getCode() {
		return code;
	}
	
}
