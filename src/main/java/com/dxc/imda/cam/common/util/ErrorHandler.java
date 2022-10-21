package com.dxc.imda.cam.common.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dxc.imda.cam.common.constant.Enums.ResourceType;
import com.dxc.imda.cam.common.model.ErrorResponse;

@Component
public class ErrorHandler {
	
	private static final String schema = "urn:ietf:params:scim:api:messages:2.0:Error";
	
	@Autowired
	private MessageHandler messageHandler;
	
	public ErrorResponse getErrorResponse (String message, String status) {
		List<String> schemas = new ArrayList<>();
		schemas.add(schema);
		String detail = null;
		if ("401".equalsIgnoreCase(status)) {		
			detail = messageHandler.getMessage("request.unauthorised") + ", " + message;
		}else {			
			detail = messageHandler.getMessage("error.message") + ", " + message;
		}		
		return new ErrorResponse(schemas, detail, status);		
	}

	public ErrorResponse getErrorResponse (String resourceType, String message, String status) {
		List<String> schemas = new ArrayList<>();
		schemas.add(schema);
		String detail = null;
		if ("404".equalsIgnoreCase(status)) {
			if (resourceType.equalsIgnoreCase(ResourceType.USER.toString())) {
				detail = messageHandler.getMessage("not.found.user") + ", " + message;
			}else {
				detail = messageHandler.getMessage("not.found.group") + ", " + message;
			}
		}else {
			detail = messageHandler.getMessage("error.message") + ", " + message;
		}		
		return new ErrorResponse(schemas, detail, status);			
	}
}
