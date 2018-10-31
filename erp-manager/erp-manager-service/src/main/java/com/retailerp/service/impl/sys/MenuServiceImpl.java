package com.retailerp.service.impl.sys;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.retailerp.common.cache.JedisClient;
import com.retailerp.common.util.JsonUtils;
import com.retailerp.manager.dao.sys.MenuMapper;
import com.retailerp.manager.dao.sys.RoleMapper;
import com.retailerp.pojo.sys.Menu;
import com.retailerp.pojo.sys.Role;
import com.retailerp.service.sys.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	@Value("${USER_MENU}")
	private String USER_MENU;

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private MenuMapper menuMapper;

	@Autowired
	private JedisClient jedisClient;

	@Override
	public List<Menu> getMenuByUserId(Integer userId) {
		List<Menu> menus = null;
		String menuInfo = jedisClient.hget(USER_MENU, userId.toString());
		if (StringUtils.isBlank(menuInfo)) {
			List<Role> roles = roleMapper.findRolesByUserId(userId);
			List<Integer> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());

			if (roleIds != null && roleIds.size() > 0) {
				menus = menuMapper.findMenuByRoleIds(roleIds);
			}
			menuInfo = JsonUtils.objectToJson(menus);
			jedisClient.hset(USER_MENU, userId.toString(), menuInfo);
		} else {
			menus = JsonUtils.jsonToList(menuInfo, Menu.class);
		}
		return menus;
		
	}

}
