package com.dxc.imda.cam.das.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dxc.imda.cam.das.entity.UserOrgRole;

@Repository
public interface DasUserOrgRoleDao extends JpaRepository<UserOrgRole, Long> {
	
	static final String sqlQuery = "SELECT "
		+ "userOrgRole "
		+ "FROM UserOrgRole userOrgRole ";
					
	static final String whereClauseEqUserRoleId = "WHERE userOrgRole.userRole.id = :userRoleId ";
	
	static final String andClauseEqUserOrgId = "AND userOrgRole.userOrg.id = :userOrgId ";
	
	@Query(value=sqlQuery + whereClauseEqUserRoleId + andClauseEqUserOrgId)
	public UserOrgRole findByUserRoleIdAndUserOrgId(Long userRoleId, Long userOrgId);
}
