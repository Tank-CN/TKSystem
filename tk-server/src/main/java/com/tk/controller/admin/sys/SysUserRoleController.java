package com.tk.controller.admin.sys;

import com.tk.controller.admin.AdminBaseController;
import com.tk.manage.SysModuleManage;
import com.tk.manage.SysRoleManage;
import com.tk.manage.SysUserRoleManage;
import com.tk.model.Admin;
import com.tk.model.SysModule;
import com.tk.model.SysRole;
import com.tk.model.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Controller
@RequestMapping(value = "/admin/sys")
public class SysUserRoleController extends AdminBaseController {

	@Autowired
	SysModuleManage sysModuleManage;

	@Autowired
	SysUserRoleManage sysUserRoleManage;

	@Autowired
	SysRoleManage sysRoleManage;

	@RequestMapping(value = "userRole/roleByUser/{userId}", method = RequestMethod.POST)
	@ResponseBody
	public List<SysRole> getByUser(@PathVariable("userId") Integer userId) {
		List<SysRole> roles = new LinkedList<SysRole>();
		List<SysUserRole> userRoles = sysUserRoleManage.getByUserId(userId);
		for (SysUserRole userRole : userRoles) {
			roles.add(sysRoleManage.getSysRole(userRole.getRid()));
		}
		return roles;
	}

	@RequestMapping(value = "userRole/add", method = RequestMethod.POST)
	@ResponseBody
	public Boolean createObj(SysUserRole sysUserRole,
			HttpServletRequest request) {
		Admin admin =getAdmin(request);
		sysUserRole.setCreatedate(new Date());
		sysUserRole.setCreateuser(admin.getId());
		return sysUserRoleManage.save(sysUserRole) == 1;
	}

	@RequestMapping(value = "userRole/delete/{userId}/{roleId}", method = RequestMethod.POST)
	@ResponseBody
	public Boolean removeObj(@PathVariable("userId") Integer userId,
			@PathVariable("roleId") Integer roleId) {
		return sysUserRoleManage.removeRoleByUserAndRole(userId, roleId) == 1;
	}

	// /**
	// * 首页调用，获取权限对应的module
	// *
	// * @param request
	// * @return
	// * @throws NoSuchMethodException
	// * @throws InvocationTargetException
	// * @throws IllegalAccessException
	// */
	// @RequestMapping(value = "userRole/urls", method = RequestMethod.GET)
	// @ResponseBody
	// public Map getUrls(HttpServletRequest request)
	// throws IllegalAccessException, InvocationTargetException,
	// NoSuchMethodException {
	// Map res = new HashMap();
	// AdminAccount account = (AdminAccount) request.getSession()
	// .getAttribute("account");
	// List<SysModule> modules = sysModuleManage.getByUser(account.getId());
	// Map<String,RoleUrlsVo> data = new HashMap<String,RoleUrlsVo>();
	// List<RoleUrlsVo> list=new ArrayList<RoleUrlsVo>();
	// for (SysModule module : modules) {
	// if ("0".equals(module.getPcode())) {
	// if (null == data.get(module.getCode())) {
	// RoleUrlsVo vo = new RoleUrlsVo();
	// PropertyUtils.copyProperties(vo, module);
	// data.put(module.getCode(), vo);
	// }
	// } else {
	// if (null != data.get(module.getPcode())) {
	// if (null != data.get(module.getPcode()).getArrs()) {
	// data.get(module.getPcode()).getArrs().add(module);
	// } else {
	// List<SysModule> arrs = new ArrayList<SysModule>();
	// arrs.add(module);
	// data.get(module.getPcode()).setArrs(arrs);
	// }
	//
	// }
	// }
	// }
	// //排序
	// //fuck
	// for (SysModule module : modules) {
	// if ("0".equals(module.getPcode())) {
	// list.add(data.get(module.getCode()));
	// }
	// }
	// res.put("data",list);
	// res.put("code", 1);
	// return res;
	// }

	/**
	 * 首页调用，获取权限对应的module
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "userRole/urls", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, List<SysModule>> getUrls(HttpServletRequest request) {

		Admin admin =getAdmin(request);

		List<SysModule> modules = sysModuleManage.getByUser(admin.getId());

		Map res = new HashMap();
		for (SysModule module : modules) {
			if (res.get(module.getPcode()) == null) {
				List<SysModule> list = new ArrayList<>();
				list.add(module);
				res.put(module.getPcode(), list);
			} else {
				List<SysModule> list = (List<SysModule>) res.get(module
						.getPcode());
				list.add(module);
				res.put(module.getPcode(), list);
			}
		}
		return res;
	}
}
