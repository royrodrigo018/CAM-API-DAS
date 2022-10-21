package com.dxc.imda.cam.das.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.imda.cam.common.constant.Constants;
import com.dxc.imda.cam.common.constant.Enums.AppName;
import com.dxc.imda.cam.common.constant.Enums.ResourceType;
import com.dxc.imda.cam.common.model.ErrorResponse;
import com.dxc.imda.cam.common.model.UpdateInfo;
import com.dxc.imda.cam.common.model.UserInfo;
import com.dxc.imda.cam.common.util.AuditUtil;
import com.dxc.imda.cam.common.util.MessageHandler;
import com.dxc.imda.cam.common.util.PageUtil;
import com.dxc.imda.cam.common.util.ParamUtil;
import com.dxc.imda.cam.common.validator.FilterParamValidator;
import com.dxc.imda.cam.common.validator.UserValidator;
import com.dxc.imda.cam.das.entity.DasCamApiAudit;
import com.dxc.imda.cam.das.service.DasCamApiAuditService;
import com.dxc.imda.cam.das.service.DasUserProfileService;

@RestController
public class DasUserController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${spring.app.das.auditFlag}")
	private String auditFlag;
	
	@Autowired
	private DasUserProfileService dasUserProfileService;
	
	@Autowired
	private DasCamApiAuditService dasCamApiAuditService;
	
	@Autowired
	private UserValidator userValidator;
			
	@Autowired
	private FilterParamValidator filterParamValidator;
	
	@Autowired
	private MessageHandler messageHandler;
	
	public ResponseEntity<Object> getUserInfo(HttpServletRequest request, 
		HttpServletResponse response,
		@RequestParam Map<String, String> reqParamMap, 
		@RequestBody Map<String, String> reqBodyMap
	) {
		logger.info("******************** Start getUserInfo ********************");
		
		String userId = reqBodyMap.get("userId");
		logger.info("getUserInfo userId: {} " + userId);
		
		ErrorResponse errorResponse = userValidator.validateUserId(userId);
		if (errorResponse != null) {
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		}
		
		UserInfo userInfo = new UserInfo();

		try {
			
			userInfo = dasUserProfileService.findByUserId(userId);	
			
			errorResponse = userValidator.validateUserInfo(userInfo);
			if (errorResponse != null) {
				return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
		    return new ResponseEntity<>(e.getLocalizedMessage() +" "+ messageHandler.getMessage("error.message.admin"),
		    	HttpStatus.INTERNAL_SERVER_ERROR);
		}finally {
			if (auditFlag.equalsIgnoreCase(Constants.ACTIVE_FLAG)) {
				auditLog(request, response, userInfo);
			}
		}
		logger.info("******************** End getUserInfo ********************");
		logger.info("");
		return new ResponseEntity<>(userInfo, HttpStatus.OK);		
	}
	
	public ResponseEntity<Object> getUserInfoList(HttpServletRequest request, 
		HttpServletResponse response,
		@RequestParam Map<String, String> reqParamMap, 
		@RequestBody Map<String, String> reqBodyMap
	) {		
		logger.info("******************** Start getUserInfoList ********************");
		
		String filterValue = reqBodyMap.get(Constants.FILTER).toString();
		int page = Integer.parseInt(reqBodyMap.get(Constants.START_INDEX));
		int size = Integer.parseInt(reqBodyMap.get(Constants.ITEMS_PER_PAGE));
		String ascOrderByValue = reqBodyMap.get(Constants.ASC_ORDER_BY);
		String descOrderByValue = reqBodyMap.get(Constants.DESC_ORDER_BY);
		
		ErrorResponse errorResponse = filterParamValidator.validateFilterParam(ResourceType.USER.toString(), reqBodyMap);
		if (errorResponse != null) {
		    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);					
		}
		
		String [] sortByArray = {ascOrderByValue, descOrderByValue};
		//logger.info("getUserInfoList ascOrderByValue descOrderByValue: {} {} " + ascOrderByValue +" "+ descOrderByValue);
		
		Map<String, Object> userInfoMap = new HashMap<>();
				
		try {
			PageUtil pageUtil = new PageUtil();
			Pageable pageable = pageUtil.getPageable(page, size, AppName.DAS, ResourceType.USER, sortByArray);
			Page<UserInfo> pagedUserInfos = null;
			Long count = 0L;

			if (!filterValue.isBlank()) {
				
				String[] filterParams = filterParamValidator.validateFilterParam(filterValue);	
				
				if ("roleName".equalsIgnoreCase(filterParams[0])) {
					if ("EQ".equalsIgnoreCase(filterParams[1])) {
						logger.info("getUserInfoList findByRoleNameEquals filterParams[2]: {} " + filterParams[2]);
						count = dasUserProfileService.countByRoleNameEquals(filterParams[2]);
						if (count > 0) {
							pagedUserInfos = dasUserProfileService.findByRoleNameEquals(filterParams[2], pageable);
						}
					} else if (Constants.CO_OPERATOR.equalsIgnoreCase(filterParams[1]) || 
						Constants.LIKE_OPERATOR.equalsIgnoreCase(filterParams[1])) {
						logger.info("getUserInfoList findByRoleNameContaining filterParams[2]: {} " + filterParams[2]);
						count = dasUserProfileService.countByRoleNameContaining(filterParams[2]);
						if (count > 0) {
							pagedUserInfos = dasUserProfileService.findByRoleNameContaining(filterParams[2], pageable);
						}
					}
				}else if ("roleDesc".equalsIgnoreCase(filterParams[0])) {
					if ("EQ".equalsIgnoreCase(filterParams[1])) {
						logger.info("getUserInfoList findByRoleDescEquals filterParams[2]: {} " + filterParams[2]);
						count = dasUserProfileService.countByRoleDescEquals(filterParams[2]);
						if (count > 0) {
							pagedUserInfos = dasUserProfileService.findByRoleDescEquals(filterParams[2], pageable);
						}
					} else if (Constants.CO_OPERATOR.equalsIgnoreCase(filterParams[1]) || 
						Constants.LIKE_OPERATOR.equalsIgnoreCase(filterParams[1])) {
						logger.info("getUserInfoList findByRoleDescContaining filterParams[2]: {} " + filterParams[2]);
						count = dasUserProfileService.countByRoleDescContaining(filterParams[2]);
						if (count > 0) {
							pagedUserInfos = dasUserProfileService.findByRoleDescContaining(filterParams[2], pageable);
						}
					}					
				}
			}else {	
				count = dasUserProfileService.countAll();
				if (count > 0) {
					pagedUserInfos = dasUserProfileService.findAll(pageable);
				}
			}
			if (count > 0) {
				logger.info("getUserInfoList count: {} " + count);
				if (pagedUserInfos != null && pagedUserInfos.hasContent()) {
					userInfoMap = pageUtil.getUserInfoMap(pagedUserInfos);
					userInfoMap.put("totalResults", count);
				}else {
					errorResponse = userValidator.validatePageUserInfo(null);
				    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);					
				}	
			}else {
				errorResponse = userValidator.validatePageUserInfo(null);
			    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);					
			}			

		} catch (Exception e) {
			e.printStackTrace();
		    return new ResponseEntity<>(e.getLocalizedMessage() +" "+ messageHandler.getMessage("error.message.admin"),
		    	HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			if (auditFlag.equalsIgnoreCase(Constants.ACTIVE_FLAG)) {
				auditLog(request, response, userInfoMap);
			}
		}
		logger.info("******************** End getUserInfoList ********************");
		logger.info("");
		return new ResponseEntity<>(userInfoMap, HttpStatus.OK);			
	}
	
	public ResponseEntity<Object> updateUser(HttpServletRequest request, 
		HttpServletResponse response,
		@RequestParam Map<String, String> reqParamMap, 
		@RequestBody String reqBody
	) {
		logger.info("******************** Start updateUser ********************");
		
		ParamUtil paramUtil = new ParamUtil();
		String userId = paramUtil.parseRequestBodyAndGetUserId(reqBody, true);
		logger.info("updateUser userId: {} " + userId);
		
		ErrorResponse errorResponse = userValidator.validateUserId(userId);
		if (errorResponse != null) {
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		}	
		
		//String status = paramUtil.parseRequestBodyAndGetStatus(reqBody);
		//logger.info("updateUser status: {} " + status);
		
		//errorResponse = userValidator.validateUserStatus(status);
		//if (errorResponse != null) {
			//return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		//}
		
		Boolean blnValue = paramUtil.parseRequestBodyAndGetValue(reqBody);
		logger.info("updateUser blnValue: {} " + blnValue);
		
		UserInfo userInfo = new UserInfo();
		
		try {
			
			userInfo = dasUserProfileService.updateUser(userId, blnValue);
			
			errorResponse = userValidator.validateUserInfo(userInfo);
			if (errorResponse != null) {
				return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		    return new ResponseEntity<>(e.getLocalizedMessage() +" "+ messageHandler.getMessage("error.message.admin"), 
		    	HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			if (auditFlag.equalsIgnoreCase(Constants.ACTIVE_FLAG)) {
				auditLog(request, response, userInfo);
			}
		}
		logger.info("******************** End updateUser ********************");
		logger.info("");
		return new ResponseEntity<>(userInfo, HttpStatus.OK);			
	}
	
	public ResponseEntity<Object> removeUser(HttpServletRequest request, 
		HttpServletResponse response,
		@RequestParam Map<String, String> reqParamMap, 
		@RequestBody Map<String, String> reqBodyMap
	) {
		logger.info("******************** Start removeUser ********************");
		
		String userId = reqBodyMap.get("userId");	
		logger.info("removeUser userId: {} " + userId);
		
		ErrorResponse errorResponse = userValidator.validateUserId(userId);
		if (errorResponse != null) {
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		}	
		
		UpdateInfo updateInfo = new UpdateInfo();
		
		try {
			
			updateInfo = dasUserProfileService.removeUser(userId, Constants.INACTIVE_STATUS);
			
			errorResponse = userValidator.validateUserInfo(updateInfo);
			if (errorResponse != null) {
				return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
		    return new ResponseEntity<>(e.getLocalizedMessage() +" "+ messageHandler.getMessage("error.message.admin"), 
		    	HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			if (auditFlag.equalsIgnoreCase(Constants.ACTIVE_FLAG)) {
				auditLog(request, response, updateInfo);
			}
		}
		logger.info("******************** End removeUser ********************");
		logger.info("");
		return new ResponseEntity<>("", HttpStatus.NO_CONTENT);			
	}
	
	private void auditLog(HttpServletRequest request, 
		HttpServletResponse response, Object object) {	
		if (auditFlag.equalsIgnoreCase(Constants.ACTIVE_FLAG)) {
			AuditUtil auditUtil = new AuditUtil();
			DasCamApiAudit dasCamApiAudit = auditUtil.logDasAudit(request, response, ResourceType.USER, object);			
			int result = dasCamApiAuditService.saveOrUpdate(dasCamApiAudit);
			logger.info("auditLog result: " + result);
		}
	}
}
