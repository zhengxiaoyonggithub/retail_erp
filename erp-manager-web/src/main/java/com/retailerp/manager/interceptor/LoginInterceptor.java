package com.retailerp.manager.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.retailerp.common.util.CookieUtils;

public class LoginInterceptor implements HandlerInterceptor {

	
	@Value("${LOGIN_COOKIE_NAME}")
	private String LOGIN_COOKIE_NAME;
	
	@Value("${SSO_URL}")
	private String SSO_URL;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String loginInfo = CookieUtils.getCookieValue(request, LOGIN_COOKIE_NAME);
		
		if(request.getRequestURL().indexOf("/page/") != -1) {
			return true;
		}
		
		if(StringUtils.isBlank(loginInfo)) {
			response.sendRedirect(SSO_URL + "/page/login?redirect=" + request.getRequestURL());
			return false;
		}
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
