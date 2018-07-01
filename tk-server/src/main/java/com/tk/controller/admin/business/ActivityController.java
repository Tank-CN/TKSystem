package com.tk.controller.admin.business;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tk.controller.admin.AdminBaseController;
import com.tk.manage.ActivityManage;
import com.tk.manage.ActivitySignupManage;
import com.tk.model.Activity;
import com.tk.util.CommonUtils;
import com.tk.util.DateUtils;
import com.tk.util.HttpPostUploadUtil;
import com.tk.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 活动管理
 *
 * @author Administrator
 */

@Controller
@RequestMapping(value = "/admin/business")
public class ActivityController extends AdminBaseController {

    @Autowired
    ActivityManage activityManage;

    @Autowired
    ActivitySignupManage activitySignupManage;


    @Autowired
    HttpPostUploadUtil imageUploadService;


    @RequestMapping(value = "activity")
    public ModelAndView basOrganization(String currentpage) {
        ModelAndView modelAndView = new ModelAndView("admin/business/activity");
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
    @RequestMapping(value = "activity/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(String title, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "length", defaultValue = "20") Integer length,  HttpServletRequest request) {
        Map<String, Object> regMsg = new HashMap<String, Object>();
        List<Activity> list = activityManage.list(title, page, length);
        if (null != list && list.size() > 0) {
            for (Activity activity : list) {
                activity.setContent(null);
            }
            regMsg.put("data", list);
        }
        regMsg.put("total", activityManage.count(title));
        regMsg.put("code", ResultCode.SUCCESS);
        return regMsg;
    }


    @RequestMapping(value = "activity/updateView")
    public ModelAndView updateView(Long id, String currentpage) {
        ModelAndView modelAndView = new ModelAndView("admin/business/activity_update");
        modelAndView.addObject("id", id);
        modelAndView.addObject("currentpage", currentpage);
        return modelAndView;
    }

    @RequestMapping(value = "activity/addView")
    public String addView() {
        return "admin/business/activity_add";
    }

    @RequestMapping(value = "activity/saveorupdate", method = RequestMethod.POST)
    public void insertOrUpdate(Activity activity, @RequestParam(value = "file", required = false) MultipartFile file, String sdate, String edate, String sh, String eh, String sm, String em, HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject map = new JSONObject();
        activity.setStime(DateUtils.dateFormate(sdate + " " + sh + ":" + sm, "yyyy-MM-dd HH:mm"));
        activity.setEtime(DateUtils.dateFormate(edate + " " + eh + ":" + em, "yyyy-MM-dd HH:mm"));
        boolean flag = false;
        if (file != null) {
            String ret = imageUploadService.formUpload(file, "1200x800", "750x500");
            if (!CommonUtils.isNull(ret)) {
                Map jmap = JSON.parseObject(ret, Map.class);
                if ("1".equals(jmap.get("code").toString())) {
                    flag = true;
                    activity.setImgurl(imageUploadService.getNetServiceUrl() + jmap.get("url"));
                }
            }
        }
        if (flag || file == null) {
            if (activity.getId() != null) {
                map.put("code", activityManage.update(activity));
                map.put("data", activity.getId());
                response.getWriter().println(map.toJSONString());
            } else {
                activity.setCreatedate(new Date());
                map.put("code", ResultCode.SUCCESS);
                map.put("data", activityManage.insertBackId(activity));
                response.getWriter().println(map.toJSONString());
            }
        } else {
            map.put("code", "0");
            response.getWriter().println(map.toJSONString());
        }
    }

    @RequestMapping(value = "activity/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Boolean delete(@PathVariable("id") Long id) {
        return activityManage.delete(id) == 1;

    }

    @RequestMapping(value = "activity/detail/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Activity detail(@PathVariable("id") Long id) {
        return activityManage.getById(id);
    }

}
