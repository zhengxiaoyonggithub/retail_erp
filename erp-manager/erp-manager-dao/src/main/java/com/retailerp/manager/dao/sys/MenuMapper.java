package com.retailerp.manager.dao.sys;

import java.util.List;

import com.retailerp.common.pojo.web.sys.MenuQueryPageParameter;
import com.retailerp.pojo.sys.Menu;

public interface MenuMapper {
	List<Menu> findMenuByRoleIds(List<Integer> roleIds);
	
	int getMenuRecordCount(MenuQueryPageParameter parameter);
	
	List<Menu> getMenus(MenuQueryPageParameter parameter);
	
	void delMenuById(Integer id);
	
	Menu findMenuById(Integer id);
	
	void updateMenu(Menu menu);
	
	List<Menu> getAllMenus();
}
