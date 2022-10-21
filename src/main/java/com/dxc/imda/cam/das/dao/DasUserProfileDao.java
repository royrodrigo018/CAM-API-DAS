package com.dxc.imda.cam.das.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dxc.imda.cam.das.entity.UserProfile;

@Repository
public interface DasUserProfileDao extends JpaRepository<UserProfile, Long> {
	
	static final String countQuery = "SELECT count(userProfile.userId) "
		+ "FROM UserProfile userProfile "
		+ "JOIN userProfile.userOrgs userOrg "
		+ "JOIN userOrg.codeOrg codeOrg "
		+ "JOIN userOrg.userOrgRoles userOrgRole "
		+ "JOIN userOrgRole.userRole userRole ";
	
	static final String sqlQuery = "SELECT userProfile "
		+ "FROM UserProfile userProfile "
		+ "JOIN userProfile.userOrgs userOrg "
		+ "JOIN userOrg.codeOrg codeOrg "
		+ "JOIN userOrg.userOrgRoles userOrgRole "
		+ "JOIN userOrgRole.userRole userRole ";
	
	static final String whereClauseEqUserId = "WHERE userProfile.userId = :userId ";	
	
	static final String whereClauseEqRoleName = "WHERE userRole.roleName = :roleName ";	
	static final String whereClauseEqRoleDesc = "WHERE userRole.roleDesc = :roleDesc ";

	static final String whereClauseLikeRoleName = "WHERE userRole.roleName LIKE %:roleName% ";	
	static final String whereClauseLikeRoleDesc = "WHERE userRole.roleDesc LIKE %:roleDesc% ";	
	
	static final String whereClauseRoleFilter = "WHERE UPPER(codeOrg.orgType) = 'IMDA' ";	
	static final String andClauseRoleFilter = "AND UPPER(codeOrg.orgType) = 'IMDA' ";	
	
	static final String whereClauseActiveStatus = "AND userProfile.status = 'A' ";
	static final String andClauseActiveStatus = "AND userProfile.status = 'A' ";
	
	// TODO: getting exception when filtered with userProfile.status = 'A'
	@Query(value=sqlQuery + whereClauseEqUserId + andClauseRoleFilter)  
	public UserProfile findByUserId(String userId);
	
	@Query(value=sqlQuery + whereClauseEqRoleName + andClauseRoleFilter + andClauseActiveStatus)		
	public List<UserProfile> findByRoleName(String roleName);
	
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
	public Page<UserProfile> findAll(Pageable pageable);
	
	@Query(value=sqlQuery + whereClauseEqRoleName + andClauseRoleFilter + andClauseActiveStatus)	
	public Page<UserProfile> findByRoleNameEquals(String roleName, Pageable pageable);
	
	@Query(value=sqlQuery + whereClauseLikeRoleName + andClauseRoleFilter + andClauseActiveStatus)	
	public Page<UserProfile> findByRoleNameContaining(String roleName, Pageable pageable);
	
	@Query(value=sqlQuery + whereClauseEqRoleDesc + andClauseRoleFilter + andClauseActiveStatus)	
	public Page<UserProfile> findByRoleDescEquals(String roleDesc, Pageable pageable);
	
	@Query(value=sqlQuery + whereClauseLikeRoleDesc + andClauseRoleFilter + andClauseActiveStatus)	
	public Page<UserProfile> findByRoleDescContaining(String roleDesc, Pageable pageable);	
}
