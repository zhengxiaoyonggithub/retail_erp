package com.retailerp.manager.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.retailerp.common.pojo.web.ERPWebResult;
import com.retailerp.common.pojo.web.LoginInfo;
import com.retailerp.common.util.CaptchaUtil;
import com.retailerp.common.util.CookieUtils;
import com.retailerp.common.util.JsonUtils;
import com.retailerp.manager.dao.sys.MenuMapper;
import com.retailerp.manager.dao.sys.RoleMapper;
import com.retailerp.manager.dao.sys.UserMapper;
import com.retailerp.pojo.sys.Role;
import com.retailerp.pojo.sys.User;

@Controller
public class LoginController {

	@Autowired
	private UserMapper userMapper;

	@Value("${LOGIN_COOKIE_NAME}")
	private String LOGIN_COOKIE_NAME;

	/**
	 * @param redirect
	 * @param model
	 * @return
	 */
	@RequestMapping("/page/login")
	public String showLogin(String redirect, Model model) {
		model.addAttribute("redirect", redirect);
		return "page/login";
	}

	@RequestMapping(value = "/page/captcha", method = RequestMethod.GET)
	@ResponseBody
	public void captcha(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CaptchaUtil.outputCaptcha(request, response);
	}

	@RequestMapping(value = "/page/login", method = RequestMethod.POST)
	@ResponseBody
	public ERPWebResult login(String username, String password, String verify, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {
		// 验证登录信息
//		if (!verify.toLowerCase().equals(session.getAttribute("code").toString().toLowerCase())) {
//			return new ERPWebResult(500, "验证码不正确");
//		}
		
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			return new ERPWebResult(500, "用户名或密码错误");
		}
		User user = userMapper.findUserByLoginName(username);
		if (user == null) {
			return new ERPWebResult(500, "用户名或密码错误");
		} else if (!user.getPassword().equals(password)) {
			return new ERPWebResult(500, "用户名或密码错误");
		}
		//将用户登录信息写入cookie
		LoginInfo loginInfo = new LoginInfo(user.getId(), user.getLoginName());
		String base64LoginInfo = new String(Base64Utils.encode(JsonUtils.objectToJson(loginInfo).getBytes()));
		CookieUtils.setCookie(request, response, LOGIN_COOKIE_NAME, base64LoginInfo);
		
		return ERPWebResult.ok();
	}
}
