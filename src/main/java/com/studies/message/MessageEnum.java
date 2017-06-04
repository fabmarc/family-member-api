package com.studies.message;

public enum MessageEnum {
	
	REGISTER_NOT_FOUND("register.not.found");

	private String code;

	private MessageEnum(String code) {
		this.code = code;
	}

	public String code() {
		return code;
	}
	
}
