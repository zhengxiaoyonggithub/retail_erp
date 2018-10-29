package com.retailerp.manager.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.retailerp.common.pojo.web.ERPWebResult;
import com.retailerp.common.pojo.web.LayUIDataGridInfo;
import com.retailerp.common.pojo.web.QueryPageParameter;
import com.retailerp.common.pojo.web.sys.MenuQueryPageParameter;
import com.retailerp.common.util.JsonUtils;
import com.retailerp.manager.dao.sys.MenuMapper;
import com.retailerp.manager.dao.sys.UserMapper;
import com.retailerp.pojo.sys.Menu;

@Controller
public class MenuController {

	@Autowired
	private MenuMapper menuMapper;

	@InitBinder
	public void intDate(WebDataBinder dataBinder) {
		dataBinder.addCustomFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));
	}

	@RequestMapping("/sys/menus")
	public String index() {
		return "sys/menuIndex";
	}

	@RequestMapping("/sys/menus/getDatas")
	@ResponseBody
	public LayUIDataGridInfo<Menu> getDatas(MenuQueryPageParameter parameter) throws UnsupportedEncodingException {
		// get参数转编码
		if (parameter != null && parameter.getName() != null) {
			parameter.setName(new String(parameter.getName().getBytes("iso-8859-1"), "utf-8"));
		}
		// 查询数据
		LayUIDataGridInfo<Menu> dataGridInfo = new LayUIDataGridInfo<>();
		int recordNum = menuMapper.getMenuRecordCount(parameter);
		List<Menu> datas = menuMapper.getMenus(parameter);

		// 设置返回数据
		dataGridInfo.setCode(0);
		dataGridInfo.setMsg("");
		dataGridInfo.setCount(recordNum);
		dataGridInfo.setData(datas);
		return dataGridInfo;
	}

	@RequestMapping("/sys/menus/del")
	@ResponseBody
	public ERPWebResult delMenu(Integer id) {
		if (id == null || id <= 0) {
			return new ERPWebResult(500, "数据异常");
		} else {
			menuMapper.delMenuById(id);
		}
		return new ERPWebResult(200, "操作成功");
	}

	@RequestMapping("/sys/menu/showEdit")
	public String editMenu(Integer id, Model model) {
		Menu menu = new Menu();
		if (id > 0) {
			menu = menuMapper.findMenuById(id);
		}
		model.addAttribute("menu", menu);
		return "sys/menuAddAndEdit";
	}

	@RequestMapping("/sys/menu/edit")
	@ResponseBody
	public ERPWebResult editMenu(Menu menu) {
		menu.setUpdateTime(new Date());
		menuMapper.updateMenu(menu);
		return new ERPWebResult(200, "successful");
	}

	@RequestMapping("/sys/menu/getMenuSelect")
	@ResponseBody
	public List<Menu> getAllMenus() {
		return menuMapper.getAllMenus();
	}
}
