package com.retailerp.service.sys;

import java.util.List;

import com.retailerp.pojo.sys.Menu;

public interface MenuService {
	
	//获取用户有权限的菜单
	List<Menu> getMenuByUserId(Integer userId); 
}
