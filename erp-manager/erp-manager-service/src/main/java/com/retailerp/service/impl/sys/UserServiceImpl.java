package com.retailerp.service.impl.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retailerp.manager.dao.sys.UserMapper;
import com.retailerp.pojo.sys.User;
import com.retailerp.service.sys.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User findUserByLoginName(String loginName) {
		return userMapper.findUserByLoginName(loginName);
	}

}
