package com.studies.message;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class MessageResource {

	private Locale locale;

	@Autowired
	private MessageSource messageSource;

	public String getMessage(MessageEnum messageEnum) {
		return messageSource.getMessage(messageEnum.code(), null, locale);
	}

	public String getMessage(String code) {
		return messageSource.getMessage(code, null, locale);
	}

	public String getMessage(MessageEnum messageEnum, Object[] args) {
		return messageSource.getMessage(messageEnum.code(), args, locale);
	}

	public String getMessage(String code, Object[] args) {
		return messageSource.getMessage(code, args, locale);
	}

}
