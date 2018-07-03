package com.tk.controller.admin;


import com.tk.manage.AdminUserManage;
import com.tk.model.AdminUser;
import com.tk.util.CommonUtils;
import com.tk.util.ResultCode;
import com.tk.util.encryption.MD5Utils;
import com.tk.vo.admin.ModuleVo;
import com.tk.vo.admin.SubModuleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class LoginController extends AdminBaseController {

    @Autowired
    AdminUserManage adminUserManage;


    static List<ModuleVo> moduleList = null;

    static {
        moduleList = new ArrayList<>();
        ModuleVo vo1=new ModuleVo("c1","系统管理");
        vo1.addSub(new SubModuleVo("字典管理","/admin/index"));
        vo1.addSub(new SubModuleVo("广告管理","/admin/index"));
        vo1.addSub(new SubModuleVo("c13","区域管理","/admin/index"));

        ModuleVo vo2=new ModuleVo("c2","用户管理");
        vo2.addSub(new SubModuleVo("管理员用户","/admin/index"));
        vo2.addSub(new SubModuleVo("注册用户","/admin/index"));

        ModuleVo vo3=new ModuleVo("c3","日志管理");
        vo3.addSub(new SubModuleVo("后台日志","/admin/index"));
        vo3.addSub(new SubModuleVo("操作日志","/admin/index"));

        moduleList.add(vo1);
        moduleList.add(vo2);
        moduleList.add(vo3);
    }


    @RequestMapping(value = "adminlogin")
    public String login() {
        return "admin/adminlogin";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();

        if (CommonUtils.isNull(username) || CommonUtils.isNull(password)) {
            resMap.put("code", ResultCode.PARAMETERS_EMPTY);
            resMap.put("msg", "传入参数不能为空");
            return resMap;
        }

        AdminUser admin = adminUserManage.getAdminUserByUsername(username);
        if (admin == null) {
            resMap.put("code", ResultCode.ERROR);
            resMap.put("msg", "未找到该用户或该用户已经注销");
            return resMap;
        }

        if (!(admin.getUtype().intValue() == 1)) {
            resMap.put("code", ResultCode.ERROR);
            resMap.put("msg", "该用户无权限登录本系统");
            return resMap;
        }


        password = MD5Utils.getMD5(password);
        if (password.equals(admin.getPassword())) {
            request.getSession().setAttribute("admin", admin);
            request.getSession().setAttribute("modules", moduleList);
            resMap.put("code", ResultCode.SUCCESS);
            resMap.put("msg", "登录成功");
            return resMap;
        } else {
            resMap.put("code", ResultCode.ERROR);
            resMap.put("msg", "密码不对");
            return resMap;
        }
    }

    @RequestMapping(value = "logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("account");
        request.getSession().removeAttribute("modules");
        return "adminlogin";
    }
}
