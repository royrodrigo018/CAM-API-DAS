package com.dxc.imda.cam.das.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "T_CODE_ORG")
public class CodeOrg implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PK_ID")
	private Long id;
	
	@Column(name = "CODE_DES")
	private String codeDes;
	
	@Column(name = "ORG_TYPE")
	private String orgType;
	
	@Column(name = "SHORT_NAME")
	private String shortName;
	
	@Column(name = "WEB_URL")
	@JsonIgnore
	private String webUrl;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "STATUS_DATE")
	private Date statusDate;
	
	@Column(name = "RUNNING_NO")
	@JsonIgnore
	private Long runningNo;

	@Column(name = "CONTACT_NO")
	@JsonIgnore
	private String contactNo;
	
	@OneToMany(mappedBy = "codeOrg")
	@JsonIgnore
	private Set<UserOrg> userOrgs = new HashSet<>();
	
	@Column(name = "UEN")
	private String uen;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodeDes() {
		return codeDes;
	}

	public void setCodeDes(String codeDes) {
		this.codeDes = codeDes;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
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

	public Long getRunningNo() {
		return runningNo;
	}

	public void setRunningNo(Long runningNo) {
		this.runningNo = runningNo;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public Set<UserOrg> getUserOrgs() {
		return userOrgs;
	}

	public void setUserOrgs(Set<UserOrg> userOrgs) {
		this.userOrgs = userOrgs;
	}

	public String getUen() {
		return uen;
	}

	public void setUen(String uen) {
		this.uen = uen;
	}
}
