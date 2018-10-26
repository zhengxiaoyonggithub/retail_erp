package com.retailerp.manager.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.retailerp.common.pojo.web.ERPWebResult;
import com.retailerp.common.util.CaptchaUtil;

@Controller
public class LoginController {

	
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
	public ERPWebResult login(String username, String password, String verify, HttpSession session, HttpServletResponse response) {
		System.out.println(username);
		System.out.println(password);
		System.out.println(session.getAttribute("code"));
		ERPWebResult result = new ERPWebResult();
		if(verify.toLowerCase().equals(session.getAttribute("code").toString().toLowerCase())) {
			result.setStatus(200);
			result.setMessage("successful");
			return result;
		}
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			result.setStatus(500);
			result.setMessage("username or password is empty");
			return result;
		}
		
		Cookie cookie = new Cookie(LOGIN_COOKIE_NAME, username);
		response.addCookie(cookie);
		return result;
	}
}
