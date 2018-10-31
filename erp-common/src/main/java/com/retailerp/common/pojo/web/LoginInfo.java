package com.retailerp.common.pojo.web;

import java.io.Serializable;
import java.util.List;

public class LoginInfo implements Serializable {
	private Integer id;
	private String loginName;

	public LoginInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoginInfo(Integer id, String loginName) {
		super();
		this.id = id;
		this.loginName = loginName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}


}
