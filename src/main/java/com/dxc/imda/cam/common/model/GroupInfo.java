package com.dxc.imda.cam.common.model;

import java.util.List;

public class GroupInfo extends BaseInfo{
		
	private List<Member> members;
	
	private ExtensionCamGroup extensionCamGroup;
	
	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public ExtensionCamGroup getExtensionCamGroup() {
		return extensionCamGroup;
	}

	public void setExtensionCamGroup(ExtensionCamGroup extensionCamGroup) {
		this.extensionCamGroup = extensionCamGroup;
	}
}
