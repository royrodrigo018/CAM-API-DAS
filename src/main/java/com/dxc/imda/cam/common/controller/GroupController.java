package com.dxc.imda.cam.common.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.imda.cam.das.controller.DasGroupController;

@RestController
@RequestMapping("/cam/api")
public class GroupController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
		
	@Value("${spring.app.das.accountId:defaultValue}")
	private String dasAccountId;
			
	@Autowired
	private DasGroupController dasGroupController;
	
	@PostMapping(value = "groups/info", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> getGroupInfo(HttpServletRequest request, 
		HttpServletResponse response,
		@RequestParam Map<String, String> reqParamMap, 
		@RequestBody Map<String, String> reqBodyMap
	) {
		logger.info("============== Start getGroupInfo ==============");
		
		String accountId = reqParamMap.get("accountid");
		logger.info("accountId: "+ accountId);
		ResponseEntity<Object> responseEntity = null;
		logger.info("dasAccountId: "+ dasAccountId);

		if (dasAccountId.equalsIgnoreCase(accountId)) {
			responseEntity = dasGroupController.getGroupInfo(request, response, reqParamMap, reqBodyMap);
		}	
		
		logger.info("============== End getGroupInfo ==============");
		logger.info("");
		return responseEntity;
	}
	
	@PostMapping(value = "groups/findbycriteria", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> getGroupInfoList(HttpServletRequest request, 
		HttpServletResponse response,
		@RequestParam Map<String, String> reqParamMap, 
		@RequestBody Map<String, String> reqBodyMap
	) {
		logger.info("============== Start getGroupInfoList ==============");
		
		String accountId = reqParamMap.get("accountid");
		logger.info("accountId: "+ accountId);
		ResponseEntity<Object> responseEntity = null;
		logger.info("dasAccountId: "+ dasAccountId);

		if (dasAccountId.equalsIgnoreCase(accountId)) {
			responseEntity = dasGroupController.getGroupInfoList(request, response, reqParamMap, reqBodyMap);
		}	
		
		logger.info("============== End getGroupInfoList ==============");
		logger.info("");
		return responseEntity;	
	}
	
	@PostMapping(value = "groups/update", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> updateGroup(HttpServletRequest request, 
		HttpServletResponse response,
		@RequestParam Map<String, String> reqParamMap, 
		@RequestBody String reqBody
	) {
		logger.info("============== Start updateGroup ==============");
		
		String accountId = reqParamMap.get("accountid");
		logger.info("accountId: "+ accountId);
		ResponseEntity<Object> responseEntity = null;
		logger.info("dasAccountId: "+ dasAccountId);

		if (dasAccountId.equalsIgnoreCase(accountId)) {
			responseEntity = dasGroupController.updateGroup(request, response, reqParamMap, reqBody);
		}	
		
		logger.info("============== End updateGroup ==============");
		logger.info("");
		return responseEntity;
	}
}
