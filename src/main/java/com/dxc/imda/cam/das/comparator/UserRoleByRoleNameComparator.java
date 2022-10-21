package com.dxc.imda.cam.das.comparator;

import java.util.Comparator;

import com.dxc.imda.cam.common.constant.Enums.SortOrder;
import com.dxc.imda.cam.das.entity.UserRole;

public class UserRoleByRoleNameComparator implements Comparator<UserRole> {
	
	private SortOrder sortOrder;
	
	public UserRoleByRoleNameComparator(SortOrder sortOrder) {
	    this.sortOrder = sortOrder;
	}
	
	@Override
	public int compare(UserRole o1, UserRole o2) {
		int compare = o1.getRoleName().compareTo(o2.getRoleName());
		if (sortOrder == SortOrder.ASC) {
			return compare;
		} else {
			return compare * (-1);
		}
	}
}
