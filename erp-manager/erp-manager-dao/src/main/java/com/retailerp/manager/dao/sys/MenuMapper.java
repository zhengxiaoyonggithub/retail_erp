package com.retailerp.manager.dao.sys;

import java.util.List;

import com.retailerp.pojo.sys.Menu;

public interface MenuMapper {
	List<Menu> findMenuByRoleIds(List<Integer> roleIds);
}
