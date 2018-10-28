package com.retailerp.manager.dao.sys;

import java.util.List;

import com.retailerp.pojo.sys.Role;

public interface RoleMapper {

	List<Role> findRolesByUserId(Integer userId);
}
