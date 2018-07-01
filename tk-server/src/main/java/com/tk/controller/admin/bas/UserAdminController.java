package com.tk.controller.admin.bas;

import com.tk.controller.admin.AdminBaseController;
import com.tk.manage.UserManage;
import com.tk.model.User;
import com.tk.util.CommonUtils;
import com.tk.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin/bas")
public class UserAdminController extends AdminBaseController {

    @Autowired
    UserManage userManage;


    @RequestMapping(value = "user")
    public ModelAndView basOrganization(String currentpage) {
        ModelAndView modelAndView = new ModelAndView("admin/bas/user");
        modelAndView.addObject("currentpage", CommonUtils.isNull(currentpage) ? "1" : currentpage);
        return modelAndView;
    }

    @RequestMapping(value = "user/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(String nickname,String phone, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "length", defaultValue = "20") Integer length, HttpServletRequest request) {
        Map<String, Object> regMsg = new HashMap<String, Object>();
        List<User> list = null;
        if (CommonUtils.isNull(nickname)&&CommonUtils.isNull(phone)) {
            list = userManage.list(page, length);
        } else {
            list = userManage.search(nickname,phone, page, length);
        }
        if (null != list && list.size() > 0) {
            regMsg.put("data", list);
            if (CommonUtils.isNull(nickname)&&CommonUtils.isNull(phone)) {
                regMsg.put("total", userManage.count());
            } else {
                regMsg.put("total", userManage.searchCount(nickname,phone));
            }
            regMsg.put("code", ResultCode.SUCCESS);
        } else {
            regMsg.put("data", null);
            regMsg.put("total", 0);
            regMsg.put("code", ResultCode.SUCCESS);
        }
        return regMsg;
    }


    @RequestMapping(value = "user/vip/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Boolean vip(@PathVariable("id") Long id) {
        return userManage.vip(id);

    }


    @RequestMapping(value = "user/unvip/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Boolean unvip(@PathVariable("id") Long id) {
        return userManage.unvip(id);

    }
}
