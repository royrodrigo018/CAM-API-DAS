package com.dxc.imda.cam.common.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort.Direction;

import com.dxc.imda.cam.common.constant.Constants;
import com.dxc.imda.cam.common.constant.Enums.ResourceType;
import com.dxc.imda.cam.common.constant.Enums.SortOrder;
import com.dxc.imda.cam.common.constant.Enums.SortedBy;

public class ParamUtil {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private SortOrder sortOrder;
	private Direction direction;
	
	// ASC / DESC
	public SortOrder getSortOrder(String[] sortByArray) {		
		if (!StringUtil.isBlank(sortByArray[0])) {
			sortOrder = SortOrder.ASC;
		}else if (!StringUtil.isBlank(sortByArray[1])) {
			sortOrder = SortOrder.DESC;
		}		
		return sortOrder;		
	}
	
	public Direction getDirection(String[] sortByArray) {	
		if (!StringUtil.isBlank(sortByArray[0])) {
			direction = Direction.ASC;
		}else if (!StringUtil.isBlank(sortByArray[1])) {
			direction = Direction.DESC;
		}		
		return direction;		
	}
	
	public String getSortedBy(String[] sortByArray) {
		String sortedBy = null;
		if (!StringUtil.isBlank(sortByArray[0])) {
			sortedBy = sortByArray[0];
		} else if (!StringUtil.isBlank(sortByArray[1])) {
			sortedBy = sortByArray[1];
		}
		return sortedBy;		
	}
	
	// TODO: test
	public List<String> getSortOrders(ResourceType resourceType, String sortedBy) {
		List<String> orders = new ArrayList<>();
		String sortBy = null;
	    if (SortedBy.GROUP_ID.getSortedBy().equals(sortedBy)) {
	    	if (ResourceType.USER.equals(resourceType)) {
	    		sortBy = Constants.USER_ROLE_ROLE_NAME;	  
	    	}else {
	    		sortBy = Constants.ROLE_NAME;
	    	}
		} else if (SortedBy.GROUP_NAME.getSortedBy().equals(sortedBy)) {
	    	if (ResourceType.USER.equals(resourceType)) {
	    		sortBy = Constants.USER_ROLE_ROLE_DESC;	
	    	} else {
	    		sortBy = Constants.ROLE_DESC;
	    	}
		} else if (SortedBy.USER_NAME.getSortedBy().equals(sortedBy)) {
			sortBy = Constants.USER_NAME;
		} else {
			sortBy = Constants.USER_ID;
		}	
		orders.add(sortBy);
		return orders;		
	}
	
	public String parseRequestBodyAndGetUserId(String reqBody, boolean bln) {	
		JSONObject jsonObject = new JSONObject(reqBody);
		logger.info("parseRequestBodyAndGetUserId jsonObject: " + jsonObject);
		String userId = jsonObject.getString("userId");
		return userId;	
	}
	
	public String parseRequestBodyAndGetGroupId(String reqBody) {	
		JSONObject jsonObject = new JSONObject(reqBody);
		logger.info("parseRequestBodyAndGetGroupId jsonObject: " + jsonObject);
		String groupId = jsonObject.getString("groupId");
		return groupId;	
	}
		
	public String parseRequestBodyAndGetUserId(String reqBody) {		
		String userId = null;		
		JSONObject objectsInArray = parseRequestBody(reqBody);	
		Iterator<String> it = objectsInArray.keys();
		while (it.hasNext()) {
			String key = it.next();
			Object objValues = objectsInArray.get(key);		
			if (Constants.OP.equalsIgnoreCase(key) && Constants.REMOVE.equalsIgnoreCase(objValues.toString())) {
			}			
			if (Constants.VALUE.equalsIgnoreCase(key)){					
				userId = objValues.toString();
			}
		}		
		return userId;		
	}
	
	public String parseRequestBodyAndGetStatus(String reqBody) {		
		String status = null;		
		JSONObject objectsInArray = parseRequestBody(reqBody);				
		Iterator<String> it = objectsInArray.keys();
		while (it.hasNext()) {
			String key = it.next();
			Object objValues = objectsInArray.get(key);		
			if (Constants.OP.equalsIgnoreCase(key) && Constants.REPLACE.equalsIgnoreCase(objValues.toString())) {
			}			
			if (Constants.VALUE.equalsIgnoreCase(key)){					
				if (objValues.equals(false)) {	
					status = Constants.ACTIVE_STATUS;
				}else {
					status = Constants.INACTIVE_STATUS;
				}
			}
		}		
		return status;		
	}
	
	public String parseRequestBodyAndGetStatusFlag(String reqBody) {		
		String statusFlag = null;		
		JSONObject objectsInArray = parseRequestBody(reqBody);				
		Iterator<String> it = objectsInArray.keys();
		while (it.hasNext()) {
			String key = it.next();
			Object objValues = objectsInArray.get(key);		
			if (Constants.OP.equalsIgnoreCase(key) && Constants.REPLACE.equalsIgnoreCase(objValues.toString())) {
			}			
			if (Constants.VALUE.equalsIgnoreCase(key)){					
				if (objValues.equals(false)) {	
					statusFlag = Constants.ACTIVE_FLAG;
				}else {
					statusFlag = Constants.INACTIVE_FLAG;
				}
			}
		}		
		return statusFlag;		
	}
	
	public JSONObject parseRequestBody(String reqBody) {
		JSONObject jsonObject = new JSONObject(reqBody);
		logger.info("parseRequestBody jsonObject: " + jsonObject);		
		JSONArray jsonArray = jsonObject.getJSONArray(Constants.OPERATIONS);		
		logger.info("parseRequestBody jsonArray: " + jsonArray);		
		// NOTE: Operations attribute will only contain 1 operation element
		JSONObject objectsInArray = jsonArray.getJSONObject(0);
		return objectsInArray;
	}	
	
	public String parseRequestBodyAndGetStatusByOpsType(String reqBody) {		
		String status = null;		
		JSONObject objectsInArray = parseRequestBody(reqBody);	
		Iterator<String> it = objectsInArray.keys();
		while (it.hasNext()) {
			String key = it.next();
			Object objValues = objectsInArray.get(key);		
			if (Constants.OP.equalsIgnoreCase(key)) {
				if (Constants.ADD.equalsIgnoreCase(objValues.toString())) {
					status = Constants.ACTIVE_STATUS;
				}else if (Constants.REMOVE.equalsIgnoreCase(objValues.toString())) {
					status = Constants.INACTIVE_STATUS;
				}
			}				
		}		
		return status;		
	}
	
	public Boolean parseRequestBodyAndGetValue(String reqBody) {		
		Boolean blnValue = false;		
		JSONObject objectsInArray = parseRequestBody(reqBody);				
		Iterator<String> it = objectsInArray.keys();
		while (it.hasNext()) {
			String key = it.next();
			Object objValues = objectsInArray.get(key);		
			if (Constants.OP.equalsIgnoreCase(key) && Constants.REPLACE.equalsIgnoreCase(objValues.toString())) {
			}			
			if (Constants.VALUE.equalsIgnoreCase(key)){					
				if (objValues.equals(false)) {	
					blnValue = false;
				}else {
					blnValue = true;
				}
			}
		}	
		logger.info("parseRequestBodyAndGetValue blnValue: " + blnValue);
		return blnValue;		
	}
}
