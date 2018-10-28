package com.retailerp.manager.dao.sys;

import com.retailerp.pojo.sys.User;

public interface UserMapper {

	void insertUser(User user);
	
	User findUserByLoginName(String loginName);
}
