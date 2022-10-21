package com.dxc.imda.cam.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.dxc.imda.cam.common.constant.Constants;
import com.dxc.imda.cam.common.constant.Enums.AppName;
import com.dxc.imda.cam.common.constant.Enums.ResourceType;
import com.dxc.imda.cam.common.model.GroupInfo;
import com.dxc.imda.cam.common.model.UserInfo;

@Component
public class PageUtil {
	
private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public Map<String, Object> getUserInfoMap(Page<UserInfo> pageInfo){
		Map<String, Object> map = new HashMap<>();
		map.put(Constants.SCHEMAS, "urn:ietf:params:scim:api:messages:2.0:ListResponse");
		//map.put("totalResults", pageInfo.getTotalElements());
		map.put(Constants.RESOURCES, pageInfo.getContent());
		map.put(Constants.START_INDEX, pageInfo.getNumber() + 1);
		map.put(Constants.ITEMS_PER_PAGE, pageInfo.getNumberOfElements());
		return map;	
	}
	
	public Map<String, Object> getGroupInfoMap(Page<GroupInfo> pageInfo){
		Map<String, Object> map = new HashMap<>();
		map.put(Constants.SCHEMAS, "urn:ietf:params:scim:api:messages:2.0:ListResponse");
		//map.put("totalResults", pageInfo.getTotalElements());
		map.put(Constants.RESOURCES, pageInfo.getContent());
		map.put(Constants.START_INDEX, pageInfo.getNumber() + 1);
		map.put(Constants.ITEMS_PER_PAGE, pageInfo.getNumberOfElements());
		return map;	
	}
	
	// check ParamUtil
	public Pageable getPageable(int page, int size, AppName appName,
		ResourceType resourceType, String[] sortByArray) {
		
		ParamUtil paramUtil = new ParamUtil();		
		String sortedBy = paramUtil.getSortedBy(sortByArray);
		List<String> orders = new ArrayList<>();
		// Check for NORS, NEUPC or DAS
		orders = paramUtil.getSortOrders(resourceType, sortedBy);
		
		Direction direction = paramUtil.getDirection(sortByArray);
		logger.info("getPageable sortedBy, orders, direction:: {0} {1} {2} " + " --> "+ sortedBy +", "+ orders +", "+ direction);
		Pageable pageable = PageRequest.of(page, size, Sort.by(direction, orders.get(0)));
		logger.info("getPageable pageable: " + pageable.toString() +" --> "+ pageable.getSort());
		return pageable;		
	}

}
