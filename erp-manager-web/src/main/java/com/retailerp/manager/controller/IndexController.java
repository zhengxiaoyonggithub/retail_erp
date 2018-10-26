package com.retailerp.manager.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.retailerp.pojo.sys.Menu;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String index(Model model) {
		List<Menu> menus = new ArrayList<>();
		menus.add(new Menu(1, "销售管理", ""));
		menus.add(new Menu(2, "生产采购", ""));
		menus.add(new Menu(3, "仓储物流", ""));
		menus.add(new Menu(4, "产品管理", ""));
		menus.add(new Menu(5, "客户管理", ""));
		menus.add(new Menu(6, "财务管理", ""));
		model.addAttribute("mainMenus", menus);
		return "index";
	}
}
