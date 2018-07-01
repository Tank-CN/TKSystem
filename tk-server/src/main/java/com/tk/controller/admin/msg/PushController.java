package com.tk.controller.admin.msg;

import com.tk.controller.admin.AdminBaseController;
import com.tk.push.PushInfo;
import com.tk.push.impl.JPushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 活动管理
 *
 * @author Administrator
 */

@Controller
@RequestMapping(value = "/admin/msg")
public class PushController extends AdminBaseController {


    @Autowired
    JPushService jPushService;


    @RequestMapping(value = "push")
    public ModelAndView push() {
        ModelAndView modelAndView = new ModelAndView("admin/msg/push");
        return modelAndView;
    }

    @RequestMapping(value = "pushview")
    public ModelAndView pushview(String uid) {
        ModelAndView modelAndView = new ModelAndView("admin/msg/push");
        modelAndView.addObject("uid",uid);
        return modelAndView;
    }

    /**
     * @param uid
     * @param title
     * @param des
     * @param kinds
     * @param type
     * @param isAlert 是否是通知
     * @param request
     * @return
     */
    @RequestMapping(value = "pushuid")
    @ResponseBody
    public Map<String, Object> pushuid(Long uid, String title, String des, Integer kinds, Integer type, Integer isAlert, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        PushInfo pushInfo = new PushInfo();
        pushInfo.setTitle(title);
        pushInfo.setDescription(des);
        pushInfo.setKinds(kinds);
        pushInfo.setType(type);
        if (isAlert == 1) {
            map.put("code", jPushService.pushAlertSigle(uid, pushInfo) ? 1 : 0);
        } else {
            map.put("code", jPushService.pushMsgSigle(uid, pushInfo) ? 1 : 0);
        }
        return map;
    }



}
