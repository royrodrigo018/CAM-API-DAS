package com.dxc.imda.cam.common.util;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class MessageHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	public String getMessage(String code, Object[] params, Locale locale) {		
		return messageSource.getMessage(code, params, locale);		
	}
	
	public String getMessage(String code) {		
		return messageSource.getMessage(code, null, Locale.ENGLISH);		
	}
}
