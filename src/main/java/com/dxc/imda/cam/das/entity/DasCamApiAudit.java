package com.dxc.imda.cam.das.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "T_CAM_API_AUDIT")
public class DasCamApiAudit implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seqCamApiAudit", sequenceName = "SEQ_T_CAM_API_AUDIT", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCamApiAudit")
	@Column(name = "AUDIT_ID")
	private Long auditId;
	
	@Column(name = "RESOURCE_TYPE")
	private String resource;
	
	@Column(name = "REQUEST_URI")
	private String requestUri;
	
	@Column(name = "REQUEST_DATE")
	private Date requestDate;

	private String data;
	
	@Column(name = "RESPONSE_STATUS")
	private int responseStatus;
	
	private String status;
	
	public Long getAuditId() {
		return auditId;
	}
	public void setAuditId(Long auditId) {
		this.auditId = auditId;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public String getRequestUri() {
		return requestUri;
	}
	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(int responseStatus) {
		this.responseStatus = responseStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "DasCamApiAudit [auditId=" + auditId + ", resource=" + resource + ", requestUri=" + requestUri
				+ ", requestDate=" + requestDate + ", data=" + data + ", responseStatus=" + responseStatus + ", status="
				+ status + "]";
	}	
}
