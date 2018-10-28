package com.retailerp.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RoleController {

	
	@RequestMapping("/sys/roles")
	public String index() {
		return "sys/roleIndex";
	}
}
