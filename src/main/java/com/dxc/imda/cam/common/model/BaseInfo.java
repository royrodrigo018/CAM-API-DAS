package com.dxc.imda.cam.common.model;

import java.util.List;

public class BaseInfo {
	
	private List<String> schemas;
	
	private String id;
	private String externalId;
	private String displayName;
	
	private Meta meta;

	public List<String> getSchemas() {
		return schemas;
	}

	public void setSchemas(List<String> schemas) {
		this.schemas = schemas;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}
}
