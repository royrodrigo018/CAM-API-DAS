package com.dxc.imda.cam.das.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dxc.imda.cam.common.model.GroupInfo;
import com.dxc.imda.cam.das.dao.DasUserRoleDao;
import com.dxc.imda.cam.das.entity.UserRole;
import com.dxc.imda.cam.das.entity.UserRoleAccess;
import com.dxc.imda.cam.das.mapper.DasGroupInfoMapper;
import com.dxc.imda.cam.das.service.DasUserRoleService;
import com.dxc.imda.cam.das.service.DasUserRoleAccessService;

@Service
public class DasUserRoleServiceImpl implements DasUserRoleService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DasUserRoleDao dasUserRoleDao;
	
	@Autowired
	private DasUserRoleAccessService dasUserRoleAccessService;
	
	@Autowired
	private DasGroupInfoMapper dasGroupInfoMapper;
	
	@Override
	public Long countAll() {
		return dasUserRoleDao.countAll();
	}

	@Override
	public Long countByRoleNameEquals(String roleName) {
		return dasUserRoleDao.countByRoleNameEquals(roleName);
	}

	@Override
	public Long countByRoleNameContaining(String roleName) {
		return dasUserRoleDao.countByRoleNameContaining(roleName);
	}

	@Override
	public Long countByRoleDescEquals(String roleDesc) {
		return dasUserRoleDao.countByRoleDescEquals(roleDesc);
	}

	@Override
	public Long countByRoleDescContaining(String roleDesc) {
		return dasUserRoleDao.countByRoleDescContaining(roleDesc);
	}

	@Override
	public GroupInfo findByRoleName(String roleName) {
		GroupInfo groupInfo = new GroupInfo();
		try {
			UserRole userRole = dasUserRoleDao.findByRoleName(roleName);
			logger.info("findByRoleName userRole: " + userRole);
			if (userRole != null) {
				Set<UserRoleAccess> userAccesses = dasUserRoleAccessService.getUserRoleAccesses(userRole.getId());
				logger.info("findByRoleName userAccesses.size(): " + userAccesses.size());
				userRole.setUserRoleAccesses(userAccesses);				
				groupInfo = getGroupInfo(userRole);
			}			
		}catch(Exception e) {
			e.printStackTrace();
			groupInfo = null;
		}
		return groupInfo;
	}

	@Override
	public Page<GroupInfo> findAll(Pageable pageable) {
		List<GroupInfo> groupInfos = new ArrayList<>();
		try {
			Page<UserRole> pagedUserRoles = dasUserRoleDao.findAll(pageable);
			logger.info("findAll pagedUserRoles: " + pagedUserRoles);
			List<UserRole> userRoles = pagedUserRoles.getContent();
			logger.info("findAll userRoles.size(): " + userRoles.size());
			groupInfos = getGroupInfos(userRoles);
			logger.info("findAll groupInfos.size(): " + groupInfos.size());
		}catch(Exception e) {
			e.printStackTrace();
			groupInfos = null;
		}
		return new PageImpl<GroupInfo>(groupInfos, pageable, groupInfos.size());
	}

	@Override
	public Page<GroupInfo> findByRoleNameEquals(String roleName, Pageable pageable) {
		List<GroupInfo> groupInfos = new ArrayList<>();
		try {
			Page<UserRole> pagedUserRoles = dasUserRoleDao.findByRoleNameEquals(roleName, pageable);
			logger.info("findByRoleNameEquals pagedUserRoles: " + pagedUserRoles);
			List<UserRole> userRoles = pagedUserRoles.getContent();
			logger.info("findByRoleNameEquals userRoles.size(): " + userRoles.size());
			groupInfos = getGroupInfos(userRoles);
			logger.info("findByRoleNameEquals groupInfos.size(): " + groupInfos.size());
		}catch(Exception e) {
			e.printStackTrace();
			groupInfos = null;
		}
		return new PageImpl<GroupInfo>(groupInfos, pageable, groupInfos.size());
	}

	@Override
	public Page<GroupInfo> findByRoleNameContaining(String roleName, Pageable pageable) {
		List<GroupInfo> groupInfos = new ArrayList<>();
		try {
			Page<UserRole> pagedUserRoles = dasUserRoleDao.findByRoleNameContaining(roleName, pageable);
			logger.info("findByRoleNameContaining pagedUserRoles: " + pagedUserRoles);
			List<UserRole> userRoles = pagedUserRoles.getContent();
			logger.info("findByRoleNameContaining userRoles.size(): " + userRoles.size());
			groupInfos = getGroupInfos(userRoles);
			logger.info("findByRoleNameContaining groupInfos.size(): " + groupInfos.size());
		}catch(Exception e) {
			e.printStackTrace();
			groupInfos = null;
		}
		return new PageImpl<GroupInfo>(groupInfos, pageable, groupInfos.size());
	}

	@Override
	public Page<GroupInfo> findByRoleDescEquals(String roleDesc, Pageable pageable) {
		List<GroupInfo> groupInfos = new ArrayList<>();
		try {
			Page<UserRole> pagedUserRoles = dasUserRoleDao.findByRoleDescEquals(roleDesc, pageable);
			logger.info("findByRoleDescEquals pagedUserRoles: " + pagedUserRoles);
			List<UserRole> userRoles = pagedUserRoles.getContent();
			logger.info("findByRoleDescEquals userRoles.size(): " + userRoles.size());
			groupInfos = getGroupInfos(userRoles);
			logger.info("findByRoleDescEquals groupInfos.size(): " + groupInfos.size());
		}catch(Exception e) {
			e.printStackTrace();
			groupInfos = null;
		}
		return new PageImpl<GroupInfo>(groupInfos, pageable, groupInfos.size());
	}

	@Override
	public Page<GroupInfo> findByRoleDescContaining(String roleDesc, Pageable pageable) {
		List<GroupInfo> groupInfos = new ArrayList<>();
		try {
			Page<UserRole> pagedUserRoles = dasUserRoleDao.findByRoleDescContaining(roleDesc, pageable);
			logger.info("findByRoleDescContaining pagedUserRoles: " + pagedUserRoles);
			List<UserRole> userRoles = pagedUserRoles.getContent();
			logger.info("findByRoleDescContaining userRoles.size(): " + userRoles.size());
			groupInfos = getGroupInfos(userRoles);
			logger.info("findByRoleDescContaining groupInfos.size(): " + groupInfos.size());
		}catch(Exception e) {
			e.printStackTrace();
			groupInfos = null;
		}
		return new PageImpl<GroupInfo>(groupInfos, pageable, groupInfos.size());
	}
	
	// TODO: to be check
	private List<GroupInfo> getGroupInfos(List<UserRole> userRoles){
		List<GroupInfo> groupInfos = new ArrayList<>();
		Set<UserRoleAccess> allUserRoleAccessSet = dasUserRoleAccessService.loadUserRoleAccesses();
		for (UserRole userRole: userRoles) {
			//Set<UserRoleAccess> userAccessesSet = dasUserRoleAccessService.getUserAccesses(userRole.getId()); // TODO			
			Set<UserRoleAccess> userAccessesSet = getUserAccessesSet(allUserRoleAccessSet, userRole);
			userRole.setUserRoleAccesses(userAccessesSet);
			
			GroupInfo groupInfo = getGroupInfo(userRole);
			groupInfos.add(groupInfo);
		}
		return groupInfos;
	}	
	
	private Set<UserRoleAccess> getUserAccessesSet (Set<UserRoleAccess> allUserRoleAccessSet, UserRole userRole){
		Set<UserRoleAccess> userRoleAccessSet = new HashSet<>();
		try {
			Map<Long, UserRoleAccess> userRoleAccessHashMap = new HashMap<>();
			for(UserRoleAccess tempUserRoleAccess: allUserRoleAccessSet) {    
	        	if (userRole.getId().equals(tempUserRoleAccess.getUserRole().getId())) {
	        		userRoleAccessHashMap.put(userRole.getId(), tempUserRoleAccess);
	        		userRoleAccessSet.add(userRoleAccessHashMap.get(userRole.getId()));
	            }
	        }
		} catch (Exception e){
			e.printStackTrace();
		}
		return userRoleAccessSet;		
	}
	
	private GroupInfo getGroupInfo(UserRole userRole){
		return dasGroupInfoMapper.convertUserRoleToJSON(userRole);		
	}
}
