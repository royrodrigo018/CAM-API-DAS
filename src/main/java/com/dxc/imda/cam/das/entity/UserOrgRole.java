package com.dxc.imda.cam.das.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.dxc.imda.cam.das.entity.UserOrgRole;

@Entity
@Table(name = "T_USR_ORG_ROLE")
public class UserOrgRole implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USR_ORG_ROLE_ID_GENERATOR", sequenceName="SEQ_T_USR_ORG_ROLE", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USR_ORG_ROLE_ID_GENERATOR")
	@Column(name = "PK_ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="USR_ORG_ID")
	private UserOrg userOrg;
	
	@ManyToOne
	@JoinColumn(name="USR_ROLE_ID")
	private UserRole userRole;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "CREATED_TIME")
	private Date createdTime;
	
	@Column(name = "LAST_UPD_BY")
	private String lastUpdBy;
	
	@Column(name = "LAST_UPD_TIME")
	private Date lastUpdTime;
		
	public UserOrgRole() {
		super();
	}

	public UserOrgRole(UserOrgRole userOrgRole) {
		super();
		this.id = userOrgRole.id;
		this.userOrg = userOrgRole.userOrg;
		this.userRole = userOrgRole.userRole;
		this.createdBy = userOrgRole.createdBy;
		this.createdTime = userOrgRole.createdTime;		
//		this.status = userOrgRole.status;
//		this.lastUpdBy = userOrgRole.lastUpdBy;
//		this.lastUpdTime = userOrgRole.lastUpdTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserOrg getUserOrg() {
		return userOrg;
	}

	public void setUserOrg(UserOrg userOrg) {
		this.userOrg = userOrg;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getLastUpdBy() {
		return lastUpdBy;
	}

	public void setLastUpdBy(String lastUpdBy) {
		this.lastUpdBy = lastUpdBy;
	}

	public Date getLastUpdTime() {
		return lastUpdTime;
	}

	public void setLastUpdTime(Date lastUpdTime) {
		this.lastUpdTime = lastUpdTime;
	}
		
	@Override
	public String toString() {
		return "UserOrgRole [id=" + id + ", userOrg=" + userOrg + ", userRole=" + userRole + ", status=" + status
				+ ", createdBy=" + createdBy + ", createdTime=" + createdTime + ", lastUpdBy=" + lastUpdBy
				+ ", lastUpdTime=" + lastUpdTime + "]";
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
		UserOrgRole other = (UserOrgRole) obj;
		return Objects.equals(id, other.id);
	}	
}
