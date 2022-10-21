package com.dxc.imda.cam.das.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="T_USR_ROLE")
public class UserRole implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="USR_ROLE_ID_GENERATOR", sequenceName="SEQ_T_USR_ROLE", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USR_ROLE_ID_GENERATOR")
	@Column(name = "PK_ID")
	private Long id;
	
	@Column(name="ROLE_NAME")
	private String roleName;

	@Column(name="ROLE_DESC")
	private String roleDesc;
	
	@Column(name="STATUS")
	private String status;
	
	@Temporal(TemporalType.DATE)
	@Column(name="STATUS_DATE")
	private Date statusDate;
	
	@OneToMany(mappedBy="userRole", fetch = FetchType.EAGER)
	private Set<UserOrgRole> userOrgRoles = new HashSet<UserOrgRole>();
	
	@OneToMany(mappedBy="userRole", fetch = FetchType.LAZY)
	private Set<UserRoleAccess> userRoleAccesses = new HashSet<UserRoleAccess>();
	
	@Column(name="SCHEME")
	@JsonIgnore
	private String scheme;
	
	@Column(name="LA_APP_TYPE")
	@JsonIgnore
	private String laAppType;

	private Long rank;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public Set<UserOrgRole> getUserOrgRoles() {
		return userOrgRoles;
	}

	public void setUserOrgRoles(Set<UserOrgRole> userOrgRoles) {
		this.userOrgRoles = userOrgRoles;
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getLaAppType() {
		return laAppType;
	}

	public void setLaAppType(String laAppType) {
		this.laAppType = laAppType;
	}

	public Long getRank() {
		return rank;
	}

	public void setRank(Long rank) {
		this.rank = rank;
	}	

	public Set<UserRoleAccess> getUserRoleAccesses() {
		return userRoleAccesses;
	}

	public void setUserRoleAccesses(Set<UserRoleAccess> userRoleAccesses) {
		this.userRoleAccesses = userRoleAccesses;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRole other = (UserRole) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "UserRole [id=" + id + ", roleName=" + roleName + ", roleDesc=" + roleDesc + ", status=" + status + "]";
	}	
}
