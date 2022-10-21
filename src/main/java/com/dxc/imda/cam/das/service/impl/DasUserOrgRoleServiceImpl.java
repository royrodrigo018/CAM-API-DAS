package com.dxc.imda.cam.das.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.imda.cam.common.constant.Constants;
import com.dxc.imda.cam.common.model.UpdateInfo;
import com.dxc.imda.cam.das.mapper.DasGroupInfoMapper;
import com.dxc.imda.cam.das.service.DasUserOrgRoleService;
import com.dxc.imda.cam.das.dao.DasUserOrgRoleDao;
import com.dxc.imda.cam.das.dao.DasUserProfileDao;
import com.dxc.imda.cam.das.entity.UserOrg;
import com.dxc.imda.cam.das.entity.UserOrgRole;
import com.dxc.imda.cam.das.entity.UserProfile;

@Service
public class DasUserOrgRoleServiceImpl implements DasUserOrgRoleService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DasUserOrgRoleDao dasUserOrgRoleDao;
	
	@Autowired
	private DasUserProfileDao dasUserProfileDao;
	
	@Autowired
	private DasGroupInfoMapper dasGroupInfoMapper;
	
	@Override
	public UserOrgRole findByUserRoleIdAndUserOrgId(Long roleId, Long userProfileId) {
		return dasUserOrgRoleDao.findByUserRoleIdAndUserOrgId(roleId, userProfileId);
	}

	@Override
	public UpdateInfo updateUserOrgRole(String roleName, String userId, String status) {
		UpdateInfo updateInfo = new UpdateInfo();
		try {
			UserProfile userProfile = dasUserProfileDao.findByUserId(userId);
			logger.info("updateUserOrgRole userProfile.getId(): " + userProfile.getId());
			
			Long roleId = getRoleId(userProfile, roleName);	
			logger.info("updateUserOrgRole roleId: " + roleId);

			UserOrgRole userOrgRole = findByUserRoleIdAndUserOrgId(roleId, userProfile.getId());		
			logger.info("updateUserOrgRole userOrgRole.getId(): " + userOrgRole.getId());
			
			userOrgRole.setStatus(status);
			userOrgRole.setLastUpdBy(Constants.CAM_USER);
			userOrgRole.setLastUpdTime(new Date());
			userOrgRole = dasUserOrgRoleDao.save(userOrgRole);
			logger.info("updateUserOrgRole userOrgRole: " + userOrgRole);	
			
			updateInfo = dasGroupInfoMapper.convertUpdateInfoToJSON(userOrgRole);
			logger.info("updateUserOrgRole updateInfo: " + updateInfo);							
		}catch(Exception e) {
			e.printStackTrace();
			updateInfo = null;
		}
		return updateInfo;
	}
	
	private Long getRoleId(UserProfile userProfile, String roleName) {
		Long roleId = null;
		logger.info("getRoleId userProfile.getUserOrgs().size(): " + userProfile.getUserOrgs().size());
		for(UserOrg userOrg: userProfile.getUserOrgs()) {
			logger.info("getRoleId userOrg.getUserOrgRoles().size(): " + userOrg.getUserOrgRoles().size());
			for(UserOrgRole userOrgRole: userOrg.getUserOrgRoles()) {
				logger.info("getRoleId roleName: " + roleName);
				logger.info("getRoleId userOrgRole.getUserRole().getRoleName(): " + userOrgRole.getUserRole().getRoleName());
				if (roleName.equalsIgnoreCase(userOrgRole.getUserRole().getRoleName())){
					roleId = userOrgRole.getUserRole().getId();
					break;
				}
			}				
		}
		return roleId;		
	}
}
