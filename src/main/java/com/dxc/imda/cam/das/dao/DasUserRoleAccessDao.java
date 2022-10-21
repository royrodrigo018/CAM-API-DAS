package com.dxc.imda.cam.das.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dxc.imda.cam.das.entity.UserRoleAccess;

@Repository
public interface DasUserRoleAccessDao extends JpaRepository<UserRoleAccess, Long> {
	
	static final String sqlQuery = "SELECT "
		+ "userRoleAccess "
		+ "FROM UserRoleAccess userRoleAccess ";
					
	static final String whereClauseEqUserRoleId = "WHERE userRoleAccess.userRole.id = :userRoleId ";
	
	//@Query(value=sqlQuery + whereClauseEqUserRoleId, nativeQuery = true)
	@Query(value=sqlQuery + whereClauseEqUserRoleId)
	public Set<UserRoleAccess> findByUserRoleId(Long userRoleId);
	
	@Query(value=sqlQuery)
	public Set<UserRoleAccess> loadUserRoleAccesses();

}
