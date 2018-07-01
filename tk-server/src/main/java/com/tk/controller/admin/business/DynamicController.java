package com.tk.controller.admin.business;

import com.tk.controller.admin.AdminBaseController;
import com.tk.manage.DynamicManage;
import com.tk.util.CommonUtils;
import com.tk.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态管理
 *
 * @author Administrator
 */

@Controller
@RequestMapping(value = "/admin/business")
public class DynamicController extends AdminBaseController {

    @Autowired
    DynamicManage dynamicManage;


    @RequestMapping(value = "dynamic")
    public ModelAndView basOrganization(String currentpage) {
        ModelAndView modelAndView = new ModelAndView("admin/business/dynamic");
        modelAndView.addObject("currentpage", CommonUtils.isNull(currentpage) ? "1" : currentpage);
        return modelAndView;
    }

    /**
     * 医院列表
     *
     * @param page
     * @param length
     * @return
     */
    @RequestMapping(value = "dynamic/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(String nickname, String uid, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "length", defaultValue = "20") Integer length, HttpServletRequest request) {
        Map<String, Object> regMsg = new HashMap<String, Object>();
//        Long _uid = null;
//        if (!CommonUtils.isNull(uid)) {
//            try {
//                _uid = Long.valueOf(uid);
//            } catch (Exception e) {
//                regMsg.put("data", null);
//                regMsg.put("total", 0);
//                regMsg.put("code", ResultCode.SUCCESS);
//            }
//        }
        Long _uid = null;
        regMsg.put("data", dynamicManage.list(nickname, _uid, page, length));
        regMsg.put("total", dynamicManage.count(nickname, _uid));
        regMsg.put("code", ResultCode.SUCCESS);
        return regMsg;
    }


    @RequestMapping(value = "dynamic/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Boolean delete(@PathVariable("id") Long id) {
        return dynamicManage.delete(id);

    }
//
//    @RequestMapping(value = "activity/detail/{id}", method = RequestMethod.POST)
//    @ResponseBody
//    public Activity detail(@PathVariable("id") Long id) {
//        return activityManage.getById(id);
//    }

}
