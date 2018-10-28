package com.retailerp.manager.interceptor;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.retailerp.common.cache.JedisClient;
import com.retailerp.common.pojo.web.LoginInfo;
import com.retailerp.common.util.CookieUtils;
import com.retailerp.common.util.JsonUtils;
import com.retailerp.manager.dao.sys.MenuMapper;
import com.retailerp.manager.dao.sys.RoleMapper;
import com.retailerp.pojo.sys.Menu;
import com.retailerp.pojo.sys.Role;

public class LoginInterceptor implements HandlerInterceptor {

	@Value("${LOGIN_COOKIE_NAME}")
	private String LOGIN_COOKIE_NAME;

	@Value("${SSO_URL}")
	private String SSO_URL;

	@Value("${USER_MENU}")
	private String USER_MENU;

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private MenuMapper menuMapper;

	@Autowired
	private JedisClient jedisClient;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 登录页面不验证
		if (request.getRequestURL().indexOf("/page/") != -1) {
			return true;
		}

		// 验证用户登录信息
		String base64LoginInfo = CookieUtils.getCookieValue(request, LOGIN_COOKIE_NAME);
		if (StringUtils.isBlank(base64LoginInfo)) {
			response.sendRedirect(SSO_URL + "/page/login?redirect=" + request.getRequestURL());
			return false;
		}
		String loginInfoStr = new String(Base64Utils.decodeFromString(base64LoginInfo));
		LoginInfo loginInfo = JsonUtils.jsonToPojo(loginInfoStr, LoginInfo.class);
		if (loginInfo == null) {
			response.sendRedirect(SSO_URL + "/page/login?redirect=" + request.getRequestURL());
			return false;
		}

		// 获取用户权限信息
		List<Menu> menus = null;
		String menuInfo = jedisClient.hget(USER_MENU, loginInfo.getId().toString());
		if (StringUtils.isBlank(menuInfo)) {
			List<Role> roles = roleMapper.findRolesByUserId(loginInfo.getId());
			List<Integer> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());
			
			if (roleIds != null && roleIds.size() > 0) {
				menus = menuMapper.findMenuByRoleIds(roleIds);
			}
			menuInfo = JsonUtils.objectToJson(menus);
			jedisClient.hset(USER_MENU, loginInfo.getId().toString(), menuInfo);
		}else {
			menus = JsonUtils.jsonToList(menuInfo, Menu.class);
		}
		request.setAttribute("menus", menus);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
