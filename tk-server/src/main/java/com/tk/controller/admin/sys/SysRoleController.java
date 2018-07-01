package com.tk.controller.admin.sys;

import com.tk.controller.admin.AdminBaseController;
import com.tk.manage.SysRoleManage;
import com.tk.model.Admin;
import com.tk.model.SysRole;
import com.tk.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin/sys")
public class SysRoleController extends AdminBaseController {

	@Autowired
	SysRoleManage sysRoleManage;

	@RequestMapping(value = "role")
	public String basRole() {
		return "admin/sys/role";
	}

	@RequestMapping(value = "role/detail/{roleId}", method = RequestMethod.POST)
	@ResponseBody
	public SysRole get(@PathVariable("roleId") Integer roleId) {
		return sysRoleManage.getSysRole(roleId);
	}

	@RequestMapping(value = "role/create", method = RequestMethod.POST)
	@ResponseBody
	public Integer create(SysRole role, HttpServletRequest request) {
		Admin admin=getAdmin(request);
		role.setFlag(Byte.valueOf("1"));
		role.setCreatedate(new Date());
		role.setCreateuser(admin.getId());
		return sysRoleManage.saveSysRole(role);
	}

	@RequestMapping(value = "role/list", method = RequestMethod.POST)
	@ResponseBody
	public Map list(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "length", defaultValue = "20") Integer length) {
		List<SysRole> modules = sysRoleManage.list(page, length);
		Map regMsg = new HashMap();
		regMsg.put("code", ResultCode.SUCCESS);
		regMsg.put("data", modules);
		regMsg.put("total", sysRoleManage.count());
		return regMsg;
	}

	@RequestMapping(value = "role/update", method = RequestMethod.POST)
	public @ResponseBody Boolean update(SysRole role, HttpServletRequest request) {
		Admin admin=getAdmin(request);
		role.setModifydate(new Date());
		role.setModifyuser(admin.getId());
		sysRoleManage.updateSysRole(role);
		return true;
	}

	@RequestMapping(value = "role/disable/{roleId}", method = RequestMethod.POST)
	public @ResponseBody Boolean delete(@PathVariable("roleId") Integer roleId) {
		return sysRoleManage.disable(roleId) == 1;
	}

	@RequestMapping(value = "role/enable/{roleId}", method = RequestMethod.POST)
	public @ResponseBody Boolean enable(@PathVariable("roleId") Integer roleId) {
		return sysRoleManage.enable(roleId) == 1;
	}
}
