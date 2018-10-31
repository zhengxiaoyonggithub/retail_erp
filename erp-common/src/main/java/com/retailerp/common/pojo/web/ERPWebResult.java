package com.retailerp.common.pojo.web;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class ERPWebResult implements Serializable {
	private Integer status;
	private String message;
	
	public ERPWebResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ERPWebResult(Integer status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public static ERPWebResult ok() {
		return ok(StringUtils.EMPTY);
	}
	public static ERPWebResult ok(String msg) {
		return new ERPWebResult(200,msg);
	}
}
