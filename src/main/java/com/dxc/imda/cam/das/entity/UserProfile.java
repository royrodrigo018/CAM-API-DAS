package com.dxc.imda.cam.das.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "T_USR_PROFILE")
public class UserProfile implements Serializable{

	private static final long serialVersionUID = 1L;
		
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="T_USR_PROFILE")
	@SequenceGenerator(name="T_USR_PROFILE", sequenceName="SEQ_T_USR_PROFILE", allocationSize = 1)
	@Column(name = "PK_ID")
	private Long id;
	
	@Column(name = "USERID")
	private String userId;
		
	@Column(name = "NAME")
	private String userName;
	
	@Column(name = "NRIC")
	private String nric;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "USER_TYPE")
	private String userType;
	
	@Column(name = "STATUS_DATE")
	private Date statusDate;	
	
	@Column(name = "LAST_ACCESS_DATE")
	private Date lastAccessDate;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "CREATED_TIME")
	private Date createdDate;
	
	@Column(name = "LAST_UPD_BY")
	private String lastUpdBy;
	
	@Column(name = "LAST_UPD_TIME")
	private Date lastUpdDate;
	
	@Column(name = "GENDER")
	@JsonIgnore
	private String gender;

	@Column(name = "CITIZENSHIP")
	@JsonIgnore
	private String citizenship;
	
	@Column(name = "OFFICE_TEL_NO")
	@JsonIgnore
	private String officeTelNo;
	
	@Column(name = "HANDPHONE_NO")
	@JsonIgnore
	private String handphoneNo;
	
	@Column(name = "FAX_NO")
	@JsonIgnore
	private String faxNo;
	
	@Column(name = "MAIN_CONTACT")
	@JsonIgnore
	private String mainContact;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "userProfile")
	//@JsonIgnore
	private List<UserOrg> userOrgs = new ArrayList<UserOrg>();
	
	public UserProfile() {
		super();
	}

	public UserProfile(UserProfile userProfile) {
		super();
		this.id = userProfile.id;
		this.userId = userProfile.userId;	
		this.userName = userProfile.userName;	
		this.nric = userProfile.nric;
		this.email = userProfile.email;
		this.userType = userProfile.userType;
		this.status = userProfile.status;
		this.statusDate = userProfile.statusDate;
		this.lastAccessDate = userProfile.lastAccessDate;
		this.createdBy = userProfile.createdBy;
		this.createdDate = userProfile.createdDate;
		this.lastUpdBy = userProfile.lastUpdBy;
		this.lastUpdDate = userProfile.lastUpdDate;
		this.userOrgs = userProfile.userOrgs;
		
		// Not use in CAM but might be affected when updating
		this.gender = userProfile.gender;
		this.citizenship = userProfile.citizenship;
		this.officeTelNo = userProfile.officeTelNo;
		this.handphoneNo = userProfile.handphoneNo;
		this.faxNo = userProfile.faxNo;
		this.email = userProfile.email;
		this.mainContact = userProfile.mainContact;
		//		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNric() {
		return nric;
	}

	public void setNric(String nric) {
		this.nric = nric;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public Date getLastAccessDate() {
		return lastAccessDate;
	}

	public void setLastAccessDate(Date lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastUpdBy() {
		return lastUpdBy;
	}

	public void setLastUpdBy(String lastUpdBy) {
		this.lastUpdBy = lastUpdBy;
	}

	public Date getLastUpdDate() {
		return lastUpdDate;
	}

	public void setLastUpdDate(Date lastUpdDate) {
		this.lastUpdDate = lastUpdDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	public String getOfficeTelNo() {
		return officeTelNo;
	}

	public void setOfficeTelNo(String officeTelNo) {
		this.officeTelNo = officeTelNo;
	}

	public String getHandphoneNo() {
		return handphoneNo;
	}

	public void setHandphoneNo(String handphoneNo) {
		this.handphoneNo = handphoneNo;
	}

	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public String getMainContact() {
		return mainContact;
	}

	public void setMainContact(String mainContact) {
		this.mainContact = mainContact;
	}

	public List<UserOrg> getUserOrgs() {
		return userOrgs;
	}

	public void setUserOrgs(List<UserOrg> userOrgs) {
		this.userOrgs = userOrgs;
	}
}
