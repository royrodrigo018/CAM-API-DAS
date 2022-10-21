package com.dxc.imda.cam.common.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dxc.imda.cam.common.constant.Enums.ResourceType;
import com.dxc.imda.cam.common.model.ErrorResponse;
import com.dxc.imda.cam.common.util.ErrorHandler;
import com.dxc.imda.cam.common.util.MessageHandler;
import com.dxc.imda.cam.common.util.StringUtil;

@Component
public class UserValidator {
	
	private static final String resourceType = ResourceType.USER.toString();
	
	private ErrorResponse errorResponse = null;
	
	@Autowired
	private ErrorHandler errorHandler;
	
	@Autowired
	private MessageHandler messageHandler;
	
	public ErrorResponse validateUserId(String userId) {	
		if (StringUtil.isBlank(userId)) {
			errorResponse = errorHandler.getErrorResponse(resourceType, messageHandler.getMessage("required.userId"), "404");
		}else if (userId.length() > 50) {
			errorResponse = errorHandler.getErrorResponse(resourceType, messageHandler.getMessage("length.userId"), "404");
		}		
		return errorResponse;
	}
	
	public ErrorResponse validateUserStatus(String status) {
		String[] arrStatus = new String[]{"A", "I"};
		List<String> statusList = new ArrayList<>(Arrays.asList(arrStatus));
		if (StringUtil.isBlank(status)) {
			errorResponse = errorHandler.getErrorResponse(resourceType, messageHandler.getMessage("required.userStatus"), "404");
		}else if (!statusList.contains(status))  {
			errorResponse = errorHandler.getErrorResponse(resourceType, messageHandler.getMessage("invalid.userStatus"), "404");
		}		
		return errorResponse;
	}
	
	public ErrorResponse validateUserInfo(Object object) {	
		if (StringUtil.isNull(object)) {
			errorResponse = errorHandler.getErrorResponse(resourceType, messageHandler.getMessage("null.userInfo"), "404");
		}		
		return errorResponse;
	}
	
	public ErrorResponse validatePageUserInfo(Object object) {	
		if (StringUtil.isNull(object)) {
			errorResponse = errorHandler.getErrorResponse(resourceType, messageHandler.getMessage("cannot.found.groupIdorgroupName"), "404");
		}		
		return errorResponse;
	}
}
