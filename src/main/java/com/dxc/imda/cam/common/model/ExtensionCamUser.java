package com.dxc.imda.cam.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExtensionCamUser {
	
	private String lastLogin;
	private String lastPasswordChanged;
	
	private boolean isPrivileged;
	
	public String getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getLastPasswordChanged() {
		return lastPasswordChanged;
	}
	public void setLastPasswordChanged(String lastPasswordChanged) {
		this.lastPasswordChanged = lastPasswordChanged;
	}
	
	@JsonProperty(value="isPrivileged")  
	public boolean isPrivileged() {
		return isPrivileged;
	}
	public void setIsPrivileged(boolean isPrivileged) {
		this.isPrivileged = isPrivileged;
	}
	
}
