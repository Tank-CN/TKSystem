package com.tk.controller.admin;

import com.tk.manage.SysSettingManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	SysSettingManage settingManage;

	@RequestMapping(value = "")
	public String admin() {
		return "redirect:index";
	}

	@RequestMapping(value = "index")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("admin/index");
		return modelAndView;
	}

}
