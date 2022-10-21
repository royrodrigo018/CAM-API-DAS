package com.dxc.imda.cam.das.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "T_USR_ORG")
public class UserOrg implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USR_ORG_ID_GENERATOR", sequenceName="SEQ_T_USR_ORG", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USR_ORG_ID_GENERATOR")
	@Column(name = "PK_ID")
	private Long id;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="DESIGNATION")
	private String designation;

	@Column(name="DEPARTMENT")
	private String department;
	
	@Column(name="EMAIL")
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "ORG_ID")
	private CodeOrg codeOrg;
	
	@ManyToOne
	@JoinColumn(name = "USR_PROFILE_ID")
	private UserProfile userProfile;
	
	//bi-directional many-to-one association to UserOrgRole
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "userOrg")
	private List<UserOrgRole> userOrgRoles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	

	public CodeOrg getCodeOrg() {
		return codeOrg;
	}

	public void setCodeOrg(CodeOrg codeOrg) {
		this.codeOrg = codeOrg;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public List<UserOrgRole> getUserOrgRoles() {
		return userOrgRoles;
	}

	public void setUserOrgRoles(List<UserOrgRole> userOrgRoles) {
		this.userOrgRoles = userOrgRoles;
	}
	
	@Override
	public String toString() {
		return "UserOrg [id=" + id + ", status=" + status + ", designation=" + designation + ", department="
				+ department + ", email=" + email + ", codeOrg=" + codeOrg + ", userProfile=" + userProfile
				+ ", userOrgRoles=" + userOrgRoles + "]";
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
		UserOrg other = (UserOrg) obj;
		return Objects.equals(id, other.id);
	}	
}
