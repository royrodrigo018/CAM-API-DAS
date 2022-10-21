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
import com.dxc.imda.cam.common.model.GroupInfo;
import com.dxc.imda.cam.common.model.UpdateInfo;
import com.dxc.imda.cam.common.util.AuditUtil;
import com.dxc.imda.cam.common.util.MessageHandler;
import com.dxc.imda.cam.common.util.PageUtil;
import com.dxc.imda.cam.common.util.ParamUtil;
import com.dxc.imda.cam.common.validator.FilterParamValidator;
import com.dxc.imda.cam.common.validator.GroupValidator;
import com.dxc.imda.cam.das.entity.DasCamApiAudit;
import com.dxc.imda.cam.das.service.DasCamApiAuditService;
import com.dxc.imda.cam.das.service.DasUserOrgRoleService;
import com.dxc.imda.cam.das.service.DasUserRoleService;

@RestController
//@RequestMapping("/api")
public class DasGroupController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${spring.app.das.auditFlag}")
	private String auditFlag;
	
	@Autowired
	private DasUserRoleService dasUserRoleService;
	
	@Autowired
	private DasUserOrgRoleService dasUserOrgRoleService;
	
	@Autowired
	private DasCamApiAuditService dasCamApiAuditService;
	
	@Autowired
	private GroupValidator groupValidator;
	
	@Autowired
	private FilterParamValidator filterParamValidator;
	
	@Autowired
	private MessageHandler messageHandler;
	
	public ResponseEntity<Object> getGroupInfo(HttpServletRequest request, 
		HttpServletResponse response,
		@RequestParam Map<String, String> reqParamMap, 
		@RequestBody Map<String, String> reqBodyMap
	) {
		logger.info("******************** Start getGroupInfo ********************");
		
		String groupId = reqBodyMap.get("groupId");
		logger.info("getGroupInfo groupId: {} " + groupId);
		
		ErrorResponse errorResponse = groupValidator.validateGroupId(groupId);
		if (errorResponse != null) {
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		}

		GroupInfo groupInfo = new GroupInfo();
		
		try {
			
			groupInfo = dasUserRoleService.findByRoleName(groupId);
			
			errorResponse = groupValidator.validateGroupInfo(groupInfo);
			if (errorResponse != null) {
				return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
		    return new ResponseEntity<>(e.getLocalizedMessage() +" "+ messageHandler.getMessage("error.message.admin"),
		    	HttpStatus.INTERNAL_SERVER_ERROR);
		}finally {
			auditLog(request, response, groupInfo);			
		}
		logger.info("******************** End getGroupInfo ********************");
		logger.info("");
		return new ResponseEntity<>(groupInfo, HttpStatus.OK);		
	}
	
	public ResponseEntity<Object> getGroupInfoList(HttpServletRequest request, 
		HttpServletResponse response,
		@RequestParam Map<String, String> reqParamMap, 
		@RequestBody Map<String, String> reqBodyMap
	) {
		logger.info("******************** Start getGroupInfoList ********************");
		
		String filterValue = reqBodyMap.get(Constants.FILTER).toString();
		int page = Integer.parseInt(reqBodyMap.get(Constants.START_INDEX));
		int size = Integer.parseInt(reqBodyMap.get(Constants.ITEMS_PER_PAGE));
		String ascOrderByValue = reqBodyMap.get(Constants.ASC_ORDER_BY);
		String descOrderByValue = reqBodyMap.get(Constants.DESC_ORDER_BY);	
		
		ErrorResponse errorResponse = filterParamValidator.validateFilterParam(ResourceType.GROUP.toString(), reqBodyMap);
		if (errorResponse != null) {
		    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);					
		}
		
		String [] sortByArray = {ascOrderByValue, descOrderByValue};
		//logger.info("getGroupInfoList ascOrderByValue descOrderByValue: {} {} " + ascOrderByValue +" "+ descOrderByValue);
		
		Map<String, Object> groupInfoMap = new HashMap<>();
		
		try {
			
			PageUtil pageUtil = new PageUtil();
			Pageable pageable = pageUtil.getPageable(page, size, AppName.DAS, ResourceType.GROUP, sortByArray);
			Page<GroupInfo> pagedGroupInfos = null;
			Long count = 0L;
			
			if (!filterValue.isBlank()) {
				String[] filterParams = filterParamValidator.validateFilterParam(filterValue);		
				
				if ("roleName".equalsIgnoreCase(filterParams[0])) {
					if ("EQ".equalsIgnoreCase(filterParams[1])) {
						logger.info("getGroupInfoList findByRoleNameEquals filterParams[2]: {} " + filterParams[2]);
						count = dasUserRoleService.countByRoleNameEquals(filterParams[2]);
						if (count > 0) {
							pagedGroupInfos = dasUserRoleService.findByRoleNameEquals(filterParams[2], pageable);
						}
					} else if (Constants.CO_OPERATOR.equalsIgnoreCase(filterParams[1]) || 
						Constants.LIKE_OPERATOR.equalsIgnoreCase(filterParams[1])) {
						logger.info("getGroupInfoList findByRoleNameContaining filterParams[2]: {} " + filterParams[2]);
						count = dasUserRoleService.countByRoleNameContaining(filterParams[2]);
						if (count > 0) {
							pagedGroupInfos = dasUserRoleService.findByRoleNameContaining(filterParams[2], pageable);
						}
					}					
				} else if ("roleDesc".equalsIgnoreCase(filterParams[0])) {
					if ("EQ".equalsIgnoreCase(filterParams[1])) {
						logger.info("getGroupInfoList findByRoleDescEquals filterParams[2]: {} " + filterParams[2]);
						count = dasUserRoleService.countByRoleDescEquals(filterParams[2]);
						if (count > 0) {
							pagedGroupInfos = dasUserRoleService.findByRoleDescEquals(filterParams[2], pageable);
						}
					} else if (Constants.CO_OPERATOR.equalsIgnoreCase(filterParams[1]) || 
						Constants.LIKE_OPERATOR.equalsIgnoreCase(filterParams[1])) {
						logger.info("getGroupInfoList findByRoleDescContaining filterParams[2]: {} " + filterParams[2]);
						count = dasUserRoleService.countByRoleDescContaining(filterParams[2]);
						if (count > 0) {
							pagedGroupInfos = dasUserRoleService.findByRoleDescContaining(filterParams[2], pageable);
						}
					}
				}
			}else {
				count = dasUserRoleService.countAll();
				if (count > 0) {
					pagedGroupInfos = dasUserRoleService.findAll(pageable);
				}
			}
			
			if (count > 0) {
				logger.info("getGroupInfoList count: {} " + count);
				if (pagedGroupInfos != null && pagedGroupInfos.hasContent()) {
					groupInfoMap = pageUtil.getGroupInfoMap(pagedGroupInfos);
					groupInfoMap.put(Constants.TOTAL_RESULTS, count);
				}else {
					errorResponse = groupValidator.validatePageGroupInfo(null);
				    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);					
				}	
			}else {
				errorResponse = groupValidator.validatePageGroupInfo(null);
			    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);					
			}		
			
		} catch (Exception e) {
			e.printStackTrace();
		    return new ResponseEntity<>(e.getLocalizedMessage() +" "+ messageHandler.getMessage("error.message.admin"), 
		    	HttpStatus.INTERNAL_SERVER_ERROR);
		}finally {
			auditLog(request, response, groupInfoMap);			
		}
		logger.info("******************** End getGroupInfoList ********************");
		logger.info("");
		return new ResponseEntity<>(groupInfoMap, HttpStatus.OK);			
	}
	
	public ResponseEntity<Object> updateGroup(HttpServletRequest request, 
		HttpServletResponse response,
		@RequestParam Map<String, String> reqParamMap, 
		@RequestBody String reqBody
	) {
		logger.info("******************** Start updateGroup ********************");
		
		ParamUtil paramUtil = new ParamUtil();
		String groupId = paramUtil.parseRequestBodyAndGetGroupId(reqBody);
		logger.info("updateGroup groupId: {} " + groupId);
		
		ErrorResponse errorResponse = groupValidator.validateGroupId(groupId);
		if (errorResponse != null) {
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		}
		
		String userId = paramUtil.parseRequestBodyAndGetUserId(reqBody);
		logger.info("updateGroup userId: {} " + userId);
		
		errorResponse = groupValidator.validateUserId(userId);
		if (errorResponse != null) {
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		}
		
		String status = paramUtil.parseRequestBodyAndGetStatusByOpsType(reqBody);
		logger.info("updateGroup status: {} " + status);
		
		// TODO
		errorResponse = groupValidator.validateGroupStatus(status);
		if (errorResponse != null) {
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		}
		
		UpdateInfo updateInfo = new UpdateInfo();

		try {
			
			updateInfo = dasUserOrgRoleService.updateUserOrgRole(groupId, userId, status);	
			
			errorResponse = groupValidator.validateGroupInfo(updateInfo);
			if (errorResponse != null) {
				return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		    return new ResponseEntity<>(e.getLocalizedMessage() +" "+ messageHandler.getMessage("error.message.admin"), 
		    	HttpStatus.INTERNAL_SERVER_ERROR);
		}finally {
			auditLog(request, response, updateInfo);			
		}
		logger.info("******************** End updateGroup ********************");
		logger.info("");
		return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);			
	}
	
	private void auditLog(HttpServletRequest request, 
		HttpServletResponse response, Object object) {
		if (auditFlag.equalsIgnoreCase(Constants.ACTIVE_FLAG)) {
			AuditUtil auditUtil = new AuditUtil();
			DasCamApiAudit dasCamApiAudit = auditUtil.logDasAudit(request, response, ResourceType.GROUP, object);			
			int result = dasCamApiAuditService.saveOrUpdate(dasCamApiAudit);
			logger.info("auditLog result: " + result);
		}
	}
}
