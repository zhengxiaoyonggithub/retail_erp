package com.retailerp.manager.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.retailerp.pojo.sys.Menu;

@Controller
public class IndexController {

	@Value("${LOGIN_COOKIE_NAME}")
	private String LOGIN_COOKIE_NAME;

	@RequestMapping(value = { "/", "/{itemId}" })
	public String index(@PathVariable(required = false) Integer itemId, Model model, HttpServletRequest request) {
		List<Menu> menus = (List<Menu>) request.getAttribute("menus");
		// 一级菜单
		List<Menu> mainMenus = new ArrayList<>();
		// 二级菜单和三级菜单
		if (menus != null && menus.size() > 0) {
			if(itemId == null) {
				itemId = menus.get(0).getId();
			}
			for (Menu menu : menus) {
				if (menu.getParentId() == 0) {
					mainMenus.add(menu);
				}
			}
		}
		model.addAttribute("mainMenus", mainMenus);
		List<Menu> dMenus = getMenu(menus,itemId);
		model.addAttribute("menus",dMenus);
		return "index";
	}

	private List<Menu> getMenu(List<Menu> menus, Integer menuId) {
		List<Menu> result = new ArrayList<>();
		for (Menu menu : menus) {
			if(menu.getParentId() == menuId) {
				menu.setMenus(getMenu(menus,menu.getId()));
				result.add(menu);
			}
		}
		return result;
	}
}
