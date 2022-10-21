package com.dxc.imda.cam.das.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dxc.imda.cam.common.model.GroupInfo;

@Service
public interface DasUserRoleService {
	
	public GroupInfo findByRoleName(String roleName);
	
	/** Count **/

	public Long countAll();
	
	public Long countByRoleNameEquals(String roleName);

	public Long countByRoleNameContaining(String roleName);
	
	public Long countByRoleDescEquals(String roleDesc);
	
	public Long countByRoleDescContaining(String roleDesc);
	
	/** List **/

	public Page<GroupInfo> findAll(Pageable pageable);
	
	public Page<GroupInfo> findByRoleNameEquals(String roleName, Pageable pageable);

	public Page<GroupInfo> findByRoleNameContaining(String roleName, Pageable pageable);
	
	public Page<GroupInfo> findByRoleDescEquals(String roleDesc, Pageable pageable);
	
	public Page<GroupInfo> findByRoleDescContaining(String roleDesc, Pageable pageable);
}
