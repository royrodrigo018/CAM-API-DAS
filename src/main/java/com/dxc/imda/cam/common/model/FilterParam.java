package com.dxc.imda.cam.common.model;

public class FilterParam {
	
	private String columnName; //groupId or groupName
	private String operator;
	private String keyword; //value of the columnName
	
	public FilterParam() {
		super();
	}
	
	public FilterParam(String columnName, String operator, String keyword) {
		super();
		this.columnName = columnName;
		this.operator = operator;
		this.keyword = keyword;
	}
	
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "FilterParam [columnName=" + columnName + ", operator=" + operator + ", keyword=" + keyword + "]";
	}	
}
