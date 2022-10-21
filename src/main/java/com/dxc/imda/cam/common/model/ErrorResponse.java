package com.dxc.imda.cam.common.model;

import java.util.List;


public class ErrorResponse {
		
	private List<String> schemas;
	private String detail;
	private String status;
	
	public ErrorResponse() {
		super();
	}

	public ErrorResponse(List<String> schemas, String detail, String status) {
		super();
		this.schemas = schemas;
		this.detail = detail;
		this.status = status;
	}

	public List<String> getSchemas() {
		return schemas;
	}

	public void setSchemas(List<String> schemas) {
		this.schemas = schemas;
	}	

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
}
