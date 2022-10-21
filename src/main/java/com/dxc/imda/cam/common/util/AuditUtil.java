package com.dxc.imda.cam.common.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dxc.imda.cam.common.constant.Constants;
import com.dxc.imda.cam.common.constant.Enums.ResourceType;
import com.dxc.imda.cam.das.entity.DasCamApiAudit;

public class AuditUtil {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public DasCamApiAudit logDasAudit(HttpServletRequest request, 
		HttpServletResponse response,
		ResourceType resourceType, Object object) {
		
		DasCamApiAudit dasCamApiAudit = new DasCamApiAudit();
		if (resourceType.equals(ResourceType.USER)) {
			dasCamApiAudit.setResource(ResourceType.USER.toString());
		}else {
			dasCamApiAudit.setResource(ResourceType.GROUP.toString());
		}
		dasCamApiAudit.setRequestUri(request.getRequestURI());		
		dasCamApiAudit.setRequestDate(new Date());
		
		JSONUtil jsonUtil = new JSONUtil();
		String jsonString = jsonUtil.convertObjectToJsonString(object);
		dasCamApiAudit.setData(jsonString);	
		
		logger.info("logDasAudit response.getStatus(): " + response.getStatus());
		dasCamApiAudit.setResponseStatus(response.getStatus());
		dasCamApiAudit.setStatus(Constants.SUCCESS);
		if (response.getStatus() >= 400) {
			dasCamApiAudit.setStatus(Constants.ERROR);	
		}		
		return dasCamApiAudit;
	}
}
