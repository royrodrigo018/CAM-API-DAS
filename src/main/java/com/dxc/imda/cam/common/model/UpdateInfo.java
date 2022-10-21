package com.dxc.imda.cam.common.model;

public class UpdateInfo {
		
	private String userId;
	private String groupId;
	private String status;
	private String lastUpdDate;
	private String lastUpdBy;
	private boolean blnSuccess;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}	
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLastUpdDate() {
		return lastUpdDate;
	}
	public void setLastUpdDate(String lastUpdDate) {
		this.lastUpdDate = lastUpdDate;
	}
	public String getLastUpdBy() {
		return lastUpdBy;
	}
	public void setLastUpdBy(String lastUpdBy) {
		this.lastUpdBy = lastUpdBy;
	}
	public boolean isBlnSuccess() {
		return blnSuccess;
	}
	public void setBlnSuccess(boolean blnSuccess) {
		this.blnSuccess = blnSuccess;
	}
	@Override
	public String toString() {
		return "UpdateInfo [userId=" + userId + ", groupId=" + groupId + ", status=" + status + ", lastUpdDate="
				+ lastUpdDate + ", lastUpdBy=" + lastUpdBy + ", blnSuccess=" + blnSuccess + "]";
	}	
}
