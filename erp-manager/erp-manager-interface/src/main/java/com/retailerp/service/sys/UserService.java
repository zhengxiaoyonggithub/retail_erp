package com.retailerp.service.sys;

import com.retailerp.pojo.sys.User;

public interface UserService {
	User findUserByLoginName(String loginName);
}
