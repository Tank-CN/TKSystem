package com.tk.controller.admin.sys;

import com.tk.manage.SysRoleManage;
import com.tk.manage.SysRoleModuleManage;
import com.tk.model.SysRole;
import com.tk.model.SysRoleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 角色模块对应关系
 * 
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/admin/sys")
public class SysRoleModuleController {

	@Autowired
	private SysRoleModuleManage sysRoleModuleManage;
	@Autowired
	private SysRoleManage sysRoleManage;

	@RequestMapping(value = "roleModule/addView")
	public String addView() {
		return "admin/sys/role_module_add";
	}

	@RequestMapping(value = "roleModule/updateView/{roleModuleId}")
	public ModelAndView updateView(
			@PathVariable("roleModuleId") Integer roleModuleId) {
		SysRole sysRole = sysRoleManage.getSysRole(roleModuleId);
		ModelAndView modelAndView = new ModelAndView(
				"admin/sys/role_module_update");
		modelAndView.addObject("sysRole", sysRole);
		return modelAndView;
	}

	@RequestMapping(value = "roleModule/create", method = RequestMethod.POST)
	@ResponseBody
	public Boolean create(SysRoleModule roleModule,
			HttpServletRequest request) {
		return sysRoleModuleManage.save(roleModule)==1;
	}

	@RequestMapping(value = "roleModules/byRole/{roleId}", method = RequestMethod.POST)
	@ResponseBody
	public List<SysRoleModule> getByRole(@PathVariable("roleId") Integer roleId) {
		return sysRoleModuleManage.getByRole(roleId);
	}

	@RequestMapping(value = "roleModule/deleteByR2M/{roleId}/{moduleId}", method = RequestMethod.POST)
	@ResponseBody
	public Boolean deleteObj(@PathVariable("roleId") Integer roleId,
			@PathVariable("moduleId") Integer moduleId) {
		return sysRoleModuleManage.deleteByRoleAndModule(roleId, moduleId)==1;
	}
}
