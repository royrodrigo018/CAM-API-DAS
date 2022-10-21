package com.dxc.imda.cam.das.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.dxc.imda.cam.das.entity.UserRoleAccess;

@Service
public interface DasUserRoleAccessService {
	
	public Set<UserRoleAccess> getUserRoleAccesses(Long userRoleId);
	
	public Set<UserRoleAccess> loadUserRoleAccesses();
}
