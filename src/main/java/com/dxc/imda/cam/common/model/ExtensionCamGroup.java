package com.dxc.imda.cam.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExtensionCamGroup {
	
	@JsonProperty("urn:ietf:params:scim:api:messages:2.0:ListResponse")
	private String groupAccessRightInfo;

	public String getGroupAccessRightInfo() {
		return groupAccessRightInfo;
	}

	public void setGroupAccessRightInfo(String groupAccessRightInfo) {
		this.groupAccessRightInfo = groupAccessRightInfo;
	}
}
