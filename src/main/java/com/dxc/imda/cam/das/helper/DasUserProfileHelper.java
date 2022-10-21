package com.dxc.imda.cam.das.helper;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.dxc.imda.cam.common.util.StringUtil;

@Component
public class DasUserProfileHelper {
	
	// to debug must check sql statements 
	// this is to check which getUserInfo method to use.
	public String getPageableSortBy(Pageable pageable) {
		String[] sortArray = pageable.getSort().toString().split(":");	
		String sortBy = null;
		if (!StringUtil.isBlank(sortArray[0])) {
			if ("userRole.roleDesc".equalsIgnoreCase(sortArray[0].trim())) {	
				sortBy = "userRole.roleDesc";
			}else if ("userRole.roleName".equalsIgnoreCase(sortArray[0].trim())) {	
				sortBy = "userRole.roleName";
			}
		}		
		return sortBy;		
	}
	
	public String getPageableSortOrder(Pageable pageable) {
		String[] sortArray = pageable.getSort().toString().split(":");	
		String sortOrder = null;
		if (!StringUtil.isBlank(sortArray[1])) {
			sortOrder = sortArray[1].trim();
		}		
		return sortOrder;		
	}
}
