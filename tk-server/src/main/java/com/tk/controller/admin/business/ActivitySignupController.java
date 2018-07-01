package com.tk.controller.admin.business;

import com.tk.controller.admin.AdminBaseController;
import com.tk.manage.ActivitySignupManage;
import com.tk.util.CommonUtils;
import com.tk.util.HttpPostUploadUtil;
import com.tk.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 活动报名管理
 *
 * @author Administrator
 */

@Controller
@RequestMapping(value = "/admin/business")
public class ActivitySignupController extends AdminBaseController {

    @Autowired
    ActivitySignupManage activitySignupManage;


    @Autowired
    HttpPostUploadUtil imageUploadService;


    @RequestMapping(value = "activitysignup")
    public ModelAndView basOrganization(String currentpage) {
        ModelAndView modelAndView = new ModelAndView("admin/business/activitysignup");
        modelAndView.addObject("currentpage", CommonUtils.isNull(currentpage) ? "1" : currentpage);
        return modelAndView;
    }

    @RequestMapping(value = "activitysignup_user")
    public ModelAndView activitysignup_user(String id,String currentpage) {
        ModelAndView modelAndView = new ModelAndView("admin/business/activitysignup_user");
        modelAndView.addObject("currentpage", CommonUtils.isNull(currentpage) ? "1" : currentpage);
        modelAndView.addObject("id", id);
        return modelAndView;
    }


    @RequestMapping(value = "activitysignup/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "length", defaultValue = "20") Integer length, HttpServletRequest request) {
        Map<String, Object> regMsg = new HashMap<String, Object>();
        regMsg.put("data", activitySignupManage.getSignCounts(page, length));
        regMsg.put("total", activitySignupManage.countSignCounts());
        regMsg.put("code", ResultCode.SUCCESS);
        return regMsg;
    }

    @RequestMapping(value = "activitysignup/listuser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> listuser(Long id,@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "length", defaultValue = "20") Integer length, HttpServletRequest request) {
        Map<String, Object> regMsg = new HashMap<String, Object>();
        regMsg.put("data", activitySignupManage.listByAid(id,page, length));
        regMsg.put("total", activitySignupManage.countByAid(id));
        regMsg.put("code", ResultCode.SUCCESS);
        return regMsg;
    }



}
