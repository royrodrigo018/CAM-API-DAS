package com.dxc.imda.cam.das.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dxc.imda.cam.das.entity.UserRole;

@Repository
public interface DasUserRoleDao extends JpaRepository<UserRole, Long> {
	
	static final String countQuery = "SELECT count(userRole.roleName) "
		+ "FROM UserRole userRole "
		+ "JOIN userRole.userOrgRoles userOrgRole "
		+ "JOIN userOrgRole.userOrg userOrg "
		+ "JOIN userOrg.codeOrg codeOrg ";
	
	static final String sqlQuery = "SELECT userRole "
		+ "FROM UserRole userRole "
		+ "JOIN userRole.userOrgRoles userOrgRole "
		+ "JOIN userOrgRole.userOrg userOrg "
		+ "JOIN userOrg.codeOrg codeOrg ";
	
	static final String whereClauseEqRoleName = "WHERE userRole.roleName = :roleName ";	
	static final String whereClauseLikeRoleName = "WHERE userRole.roleName LIKE %:roleName% ";
	
	static final String whereClauseEqRoleDesc = "WHERE userRole.roleDesc = :roleDesc ";
	static final String whereClauseLikeRoleDesc = "WHERE userRole.roleDesc LIKE %:roleDesc% ";	
	
	static final String whereClauseRoleFilter = "WHERE codeOrg.orgType = 'IMDA' ";	
	static final String andClauseRoleFilter = "AND codeOrg.orgType = 'IMDA' ";	
	
	static final String whereClauseActiveStatus = "WHERE userRole.status = 'A' ";
	static final String andClauseActiveStatus = "AND userRole.status = 'A' ";
	
	// TODO: might change if there's an updateGroup API
	@Query(value=sqlQuery + whereClauseEqRoleName + andClauseRoleFilter + andClauseActiveStatus)
	public UserRole findByRoleName(String roleName);
	
	/** Count **/

	@Query(value=countQuery + whereClauseRoleFilter + andClauseActiveStatus)
	public Long countAll();
	
	@Query(value=countQuery + whereClauseEqRoleName + andClauseRoleFilter + andClauseActiveStatus)	
	public Long countByRoleNameEquals(String roleName);
	
	@Query(value=countQuery + whereClauseLikeRoleName + andClauseRoleFilter + andClauseActiveStatus)	
	public Long countByRoleNameContaining(String roleName);
	
	@Query(value=countQuery + whereClauseEqRoleDesc + andClauseRoleFilter + andClauseActiveStatus)	
	public Long countByRoleDescEquals(String roleDesc);
	
	@Query(value=countQuery + whereClauseLikeRoleDesc + andClauseRoleFilter + andClauseActiveStatus)
	public Long countByRoleDescContaining(String roleDesc);
	
	/** List **/
	
	@Query(value=sqlQuery + whereClauseRoleFilter + andClauseActiveStatus)
	public Page<UserRole> findAll(Pageable pageable);
	
	@Query(value=sqlQuery + whereClauseEqRoleName + andClauseRoleFilter + andClauseActiveStatus)	
	public Page<UserRole> findByRoleNameEquals(String roleName, Pageable pageable);
	
	@Query(value=sqlQuery + whereClauseLikeRoleName + andClauseRoleFilter + andClauseActiveStatus)	
	public Page<UserRole> findByRoleNameContaining(String roleName, Pageable pageable);
	
	@Query(value=sqlQuery + whereClauseEqRoleDesc + andClauseRoleFilter + andClauseActiveStatus)	
	public Page<UserRole> findByRoleDescEquals(String roleDesc, Pageable pageable);
	
	@Query(value=sqlQuery + whereClauseLikeRoleDesc + andClauseRoleFilter + andClauseActiveStatus)
	public Page<UserRole> findByRoleDescContaining(String roleDesc, Pageable pageable);		
}
