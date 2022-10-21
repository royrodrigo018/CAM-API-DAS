package com.dxc.imda.cam.das.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dxc.imda.cam.common.model.UpdateInfo;
import com.dxc.imda.cam.common.model.UserInfo;

@Service
public interface DasUserProfileService {
	
	public UserInfo findByUserId(String userId);
	
	/** Count **/

	public Long countAll();
	
	public Long countByRoleNameEquals(String roleName);

	public Long countByRoleNameContaining(String roleName);
	
	public Long countByRoleDescEquals(String roleDesc);
	
	public Long countByRoleDescContaining(String roleDesc);
	
	/** List **/
	
	public Page<UserInfo> findAll(Pageable pageable);
	
	public Page<UserInfo> findByRoleNameEquals(String roleName, Pageable pageable);

	public Page<UserInfo> findByRoleNameContaining(String roleName, Pageable pageable);
	
	public Page<UserInfo> findByRoleDescEquals(String roleDesc, Pageable pageable);
	
	public Page<UserInfo> findByRoleDescContaining(String roleDesc, Pageable pageable);
	
	/** Update **/
	
	public UserInfo updateUser(String userId, String status);
	
	public UserInfo updateUser(String userId, Boolean blnValue);
	
	public UpdateInfo removeUser(String userId, String status);
}
