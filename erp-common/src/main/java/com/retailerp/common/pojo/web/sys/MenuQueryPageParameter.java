package com.retailerp.common.pojo.web.sys;

import com.retailerp.common.pojo.web.QueryPageParameter;

public class MenuQueryPageParameter extends QueryPageParameter {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "MenuQueryPageParameter [name=" + name + "]";
	}
}
