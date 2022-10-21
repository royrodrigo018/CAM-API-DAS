package com.dxc.imda.cam.common.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInfo extends BaseInfo{
		
	private String userName;
		
	private Name name;
	
	private boolean active;
	
	private List<Email> emails;
	
	private String profileUrl;
	private String title;
	private String userType;
	
	private List<Group> groups;
	
	@JsonProperty("urn:ietf:params:scim:schemas:extension:enterprise:2.0:User")
	private EnterpriseUser enterpriseUser;
	
	@JsonProperty("urn:ietf:params:scim:schemas:extension:cam:2.0:User")
	private ExtensionCamUser extensionCamUser;
	
	public ExtensionCamUser getExtensionCamUser() {
		return extensionCamUser;
	}

	public void setExtensionCamUser(ExtensionCamUser extensionCamUser) {
		this.extensionCamUser = extensionCamUser;
	}

	public EnterpriseUser getEnterpriseUser() {
		return enterpriseUser;
	}

	public void setEnterpriseUser(EnterpriseUser enterpriseUser) {
		this.enterpriseUser = enterpriseUser;
	}	

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}	

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
}
