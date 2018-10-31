package com.retailerp.common.pojo.web;

import java.io.Serializable;

public class QueryPageParameter implements Serializable {
	private int page;
	private int limit;
	private int startRow;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getStartRow() {
		return (page - 1) * limit;
	}

	@Override
	public String toString() {
		return "QueryPageParameter [page=" + page + ", limit=" + limit + ", startRow=" + startRow + "]";
	}
}
