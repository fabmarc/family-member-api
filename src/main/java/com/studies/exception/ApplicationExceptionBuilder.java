package com.studies.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.studies.message.MessageEnum;
import com.studies.message.MessageResource;

@Component
public class ApplicationExceptionBuilder {

	@Autowired
	private MessageResource messageResource;
	
	public ApplicationException create(MessageEnum messageEnum) {
		String message = messageResource.getMessage(messageEnum);
		return new ApplicationException(messageEnum.code(), message);
	}
	
	public ApplicationException create(MessageEnum messageEnum, String... messageParams) {
		String message = messageResource.getMessage(messageEnum, messageParams);
		return new ApplicationException(messageEnum.code(), message);
	}
	
	public ApplicationException create(MessageEnum messageEnum, Exception cause) {
		String message = messageResource.getMessage(messageEnum);
		return new ApplicationException(messageEnum.code(), message, cause);
	}
	
	public ApplicationException create(Exception cause, MessageEnum messageEnum, String... messageParams) {
		String message = messageResource.getMessage(messageEnum, messageParams);
		return new ApplicationException(messageEnum.code(), message, cause);
	}
	
}
