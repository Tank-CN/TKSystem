package com.tk.controller.admin.bas;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tk.controller.admin.AdminBaseController;
import com.tk.manage.BasADBannerManage;
import com.tk.model.Admin;
import com.tk.model.BasAdBanner;
import com.tk.util.CommonUtils;
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
import java.util.Map;

/**
 * 机构管理（医院管理）
 *
 * @author Administrator
 */

@Controller
@RequestMapping(value = "/admin/bas")
public class BasADBannerController extends AdminBaseController {

    @Autowired
    BasADBannerManage basADBannerManage;


    @Autowired
    HttpPostUploadUtil imageUploadService;

    @RequestMapping(value = "adbanner")
    public ModelAndView basOrganization(String currentpage) {
        ModelAndView modelAndView = new ModelAndView("admin/bas/adbanner");
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
    @RequestMapping(value = "adbanner/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "length", defaultValue = "20") Integer length,  HttpServletRequest request) {

        Map<String, Object> regMsg = new HashMap<String, Object>();
        regMsg.put("data", basADBannerManage.list(page, length));
        regMsg.put("total", basADBannerManage.count());
        regMsg.put("code", ResultCode.SUCCESS);
        return regMsg;
    }


    @RequestMapping(value = "adbanner/updateView")
    public ModelAndView updateView(Long id, String currentpage) {
        ModelAndView modelAndView = new ModelAndView("admin/bas/adbanner_update");
        modelAndView.addObject("id", id);
        modelAndView.addObject("currentpage", currentpage);
        return modelAndView;
    }

    @RequestMapping(value = "adbanner/addView")
    public String addView() {
        return "admin/bas/adbanner_add";
    }

    @RequestMapping(value = "adbanner/saveorupdate", method = RequestMethod.POST)
    public void insertOrUpdate(BasAdBanner organization, @RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Admin account = getAdmin(request);
        JSONObject map = new JSONObject();
        boolean flag = false;
        if (file != null) {
            String ret = imageUploadService.formUpload(file, "1080x360", "720x240", "480x160");
            if (!CommonUtils.isNull(ret)) {
                Map jmap = JSON.parseObject(ret, Map.class);
                if ("1".equals(jmap.get("code").toString())) {
                    flag = true;
                    organization.setPicurl(imageUploadService.getNetServiceUrl() + jmap.get("url"));
                }
            }
        }
        if (flag || file == null) {
            if (organization.getId() != null) {
                organization.setLastmodifyuser(account.getId());
                organization.setLastmodifydate(new Date());
                map.put("code", basADBannerManage.update(organization));
                map.put("data", organization.getId());
                response.getWriter().println(map.toJSONString());
            } else {
                organization.setCreateuser(account.getId());
                organization.setCreatedate(new Date());
                map.put("code", ResultCode.SUCCESS);
                map.put("data", basADBannerManage.insertBackId(organization));
                response.getWriter().println(map.toJSONString());
            }
        } else {
            map.put("code", "0");
            response.getWriter().println(map.toJSONString());
        }
    }

    @RequestMapping(value = "adbanner/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Boolean delete(@PathVariable("id") Long id) {
        return basADBannerManage.delete(id) == 1;

    }

    @RequestMapping(value = "adbanner/detail/{id}", method = RequestMethod.POST)
    @ResponseBody
    public BasAdBanner detail(@PathVariable("id") Long id) {
        return basADBannerManage.getById(id);
    }

}
