package com.tk.controller.admin.sys;

import com.tk.controller.admin.AdminBaseController;
import com.tk.manage.SysModuleManage;
import com.tk.manage.SysRoleModuleManage;
import com.tk.model.Admin;
import com.tk.model.SysModule;
import com.tk.model.SysRoleModule;
import com.tk.util.CommonUtils;
import com.tk.util.ResultCode;
import com.tk.vo.ModuleVo;
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
public class SysModuleController extends AdminBaseController {

	@Autowired
	SysModuleManage sysModuleManage;

	@Autowired
	private SysRoleModuleManage sysRoleModuleManage;


	@RequestMapping(value = "module")
	public String module() {
		return "admin/sys/module";
	}

	@RequestMapping(value = "module/allNode", method = RequestMethod.POST)
	@ResponseBody
	public List getAllNode() {
		List resList = new ArrayList();
		List<SysModule> modules = sysModuleManage.getAll();
		for (SysModule module : modules) {
			Map<String, String> map = new HashMap<String, String>();
			String pid = module.getPcode().equals("0") ? "#" : module
					.getPcode();
			map.put("id", module.getCode());
			map.put("parent", pid);
			map.put("text", module.getTitle());
			map.put("uuid", module.getId().toString());
			resList.add(map);
		}
		return resList;
	}

	@RequestMapping(value = "module/detail/{moduleId}", method = RequestMethod.POST)
	@ResponseBody
	public SysModule get(@PathVariable("moduleId") Integer moduleId) {
		return sysModuleManage.get(moduleId);
	}

	@RequestMapping(value = "module/create", method = RequestMethod.POST)
	@ResponseBody
	public Boolean create(SysModule module, HttpServletRequest request) {
		Admin admin=getAdmin(request);
		module.setFlag(Byte.valueOf("1"));
		module.setCreatedate(new Date());
		module.setCreateuser(admin.getId());
		updateSessionModule(admin,request);
		return sysModuleManage.saveSysModule(module) > 0;
	}

	@RequestMapping(value = "module/update", method = RequestMethod.POST)
	@ResponseBody
	public Boolean update(SysModule module, HttpServletRequest request) {
		Admin admin=getAdmin(request);
		module.setModifydate(new Date());
		module.setModifyuser(admin.getId());
		sysModuleManage.updateSysModule(module);
		updateSessionModule(admin,request);
		return true;
	}

	@RequestMapping(value = "module/delete/{moduleId}", method = RequestMethod.POST)
	@ResponseBody
	public Boolean delete(@PathVariable("moduleId") Integer moduleId) {
		return sysModuleManage.deleteSysModule(moduleId);
	}

	/**
	 * 角色权限添加及查看时
	 * 
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "module/all", method = RequestMethod.POST)
	@ResponseBody
	public Map getAll(Integer roleId) {
		Map map = new HashMap();
		if (CommonUtils.isNull(roleId)) {
			// 角色ID为空，直接返回所有模块
			map.put("code", ResultCode.SUCCESS);
			map.put("data", sysModuleManage.getAllByGroup(null));
		} else {
			// 返回对应角色对应的模块字段（该角色是否含有该模块字段）
			List<SysRoleModule> sysRoles = sysRoleModuleManage
					.getByRole(roleId);
			map.put("code", ResultCode.SUCCESS);
			map.put("data", sysModuleManage.getAllByGroup(sysRoles));
		}
		return map;
	}



	void updateSessionModule(Admin account , HttpServletRequest request){
		List<ModuleVo> moduleList = null;
		if (account.getUtype() == 0) {
			// 后台管理员
			if (isSuperAdmin(account)) {
				// 超级管理员
				moduleList = sysModuleManage.getByAdmin();
			} else {
				moduleList = sysModuleManage.getByUid(account.getId());
			}
		} else if (account.getUtype() == 2) {
			// 商家角色
			moduleList = sysModuleManage.getByRole((long) 2);
		}
		request.getSession().setAttribute("modules", moduleList);
	}
}
