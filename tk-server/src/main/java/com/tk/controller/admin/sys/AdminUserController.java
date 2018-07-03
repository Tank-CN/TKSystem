package com.tk.controller.admin.sys;

import com.tk.manage.AdminUserManage;
import com.tk.model.AdminUser;
import com.tk.util.CommonUtils;
import com.tk.util.ResultCode;
import com.tk.util.encryption.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin/sys")
public class AdminUserController {

	@Autowired
	AdminUserManage adminUserManage;

	@RequestMapping(value = "adminUser")
	public String basAccount() {
		return "admin/sys/admin_account";
	}

	@RequestMapping(value = "adminUser/list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> search(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "length", defaultValue = "10") Integer length, ServletRequest servletRequest) {
		List<AdminUser> list = adminUserManage.getList(page, length);

		Map<String, Object> regMsg = new HashMap<String, Object>();
		if (null != list && list.size() > 0) {
		    regMsg.put("code", ResultCode.SUCCESS);
			regMsg.put("data", list);
			regMsg.put("total", adminUserManage.count());
			return regMsg;
		}
		regMsg.put("code", ResultCode.ERROR);
		regMsg.put("msg", "数据为空");

		return regMsg;
	}

	/**
	 * 管理用户与角色的对应关系
	 *
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "manageRoleView/{userId}")
	public ModelAndView basAccount(@PathVariable("userId") Long userId) {
		AdminUser admin = adminUserManage.getAdminUserById(userId);
		ModelAndView modelAndView = new ModelAndView("admin/sys/manage_role_view");
		modelAndView.addObject("VUser", admin);
		return modelAndView;
	}

	@RequestMapping(value = "adminUser/update/{userId}", method = RequestMethod.POST)
	@ResponseBody
	public AdminUser getVUserById(@PathVariable("userId") Long userId) {
		return adminUserManage.getAdminUserById(userId);
	}

	@RequestMapping(value = "adminUser/update", method = RequestMethod.POST)
	@ResponseBody
	public Boolean update(AdminUser vUser,  HttpServletRequest request) {
		return null != adminUserManage.updateAdminUser(vUser);
	}

	@RequestMapping(value = "adminUser/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Boolean delete(@PathVariable("id") Long id) {
		return adminUserManage.delete(id) == 1;
	}

	@RequestMapping(value = "adminUser/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> create(AdminUser vUser) {
		Map<String, Object> map = new HashMap<String, Object>();

		vUser.setUtype(Byte.valueOf("1"));
		vUser.setPassword(MD5Utils.getMD5(vUser.getPassword()));
		vUser = adminUserManage.save(vUser);
		if (null != vUser) {
			map.put("code", ResultCode.SUCCESS);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("id", vUser.getId());
			map.put("data", data);
			return map;
		} else {
			map.put("code", ResultCode.ERROR);
			return map;
		}
	}

	/**
	 * 重复返回false
	 * @return
	 */
	@RequestMapping(value = "adminUser/sameName", method = RequestMethod.POST)
	@ResponseBody
	public Boolean havaSameUsername(String username) {
		if (CommonUtils.isNull(username)) {
			return false;
		}
		return adminUserManage.haveSameUsername(username);
	}




}
