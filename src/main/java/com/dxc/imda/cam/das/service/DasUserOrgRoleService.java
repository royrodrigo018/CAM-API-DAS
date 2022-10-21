package com.dxc.imda.cam.das.service;

import org.springframework.stereotype.Service;

import com.dxc.imda.cam.common.model.UpdateInfo;
import com.dxc.imda.cam.das.entity.UserOrgRole;

@Service
public interface DasUserOrgRoleService {
	
	public UpdateInfo updateUserOrgRole(String roleName, String userId, String status);
	
	public UserOrgRole findByUserRoleIdAndUserOrgId(Long roleId, Long userProfileId);
}
