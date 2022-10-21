package com.dxc.imda.cam.das.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.imda.cam.das.entity.UserRoleAccess;
import com.dxc.imda.cam.das.service.DasUserRoleAccessService;
import com.dxc.imda.cam.das.dao.DasUserRoleAccessDao;

@Service
public class DasUserRoleAccessServiceImpl implements DasUserRoleAccessService{

	@Autowired
	private DasUserRoleAccessDao dasUserRoleAccessDao;
	
	@Override
	public Set<UserRoleAccess> getUserRoleAccesses(Long userRoleId) {
		return dasUserRoleAccessDao.findByUserRoleId(userRoleId);
	}

	@Override
	public Set<UserRoleAccess> loadUserRoleAccesses() {
		return dasUserRoleAccessDao.loadUserRoleAccesses();
	}
}
