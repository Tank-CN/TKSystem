package com.tk.controller.admin.bas;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tk.controller.admin.AdminBaseController;
import com.tk.manage.BasADItemManage;
import com.tk.model.Admin;
import com.tk.model.BasAdItem;
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
public class BasADItemController extends AdminBaseController {

    @Autowired
    BasADItemManage basADItemManage;


    @Autowired
    HttpPostUploadUtil imageUploadService;

    @RequestMapping(value = "aditem")
    public ModelAndView basOrganization(String currentpage) {
        ModelAndView modelAndView = new ModelAndView("admin/bas/aditem");
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
    @RequestMapping(value = "aditem/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "length", defaultValue = "20") Integer length, HttpServletRequest request) {

        Map<String, Object> regMsg = new HashMap<String, Object>();
        regMsg.put("data", basADItemManage.list(page, length));
        regMsg.put("total", basADItemManage.count());
        regMsg.put("code", ResultCode.SUCCESS);
        return regMsg;
    }


    @RequestMapping(value = "aditem/updateView")
    public ModelAndView updateView(Long id, String currentpage) {
        ModelAndView modelAndView = new ModelAndView("admin/bas/aditem_update");
        modelAndView.addObject("id", id);
        modelAndView.addObject("currentpage", currentpage);
        return modelAndView;
    }

    @RequestMapping(value = "aditem/addView")
    public String addView() {
        return "admin/bas/aditem_add";
    }

    @RequestMapping(value = "aditem/saveorupdate", method = RequestMethod.POST)
    public void insertOrUpdate(BasAdItem organization, @RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Admin account = getAdmin(request);
        JSONObject map = new JSONObject();
        boolean flag = false;
        if (file != null) {
            String ret = imageUploadService.formUpload(file, "200x200", "100x100");
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
                map.put("code", basADItemManage.update(organization));
                map.put("data", organization.getId());
                response.getWriter().println(map.toJSONString());
            } else {
                organization.setCreateuser(account.getId());
                organization.setCreatedate(new Date());
                map.put("code", ResultCode.SUCCESS);
                map.put("data", basADItemManage.insertBackId(organization));
                response.getWriter().println(map.toJSONString());
            }
        } else {
            map.put("code", "0");
            response.getWriter().println(map.toJSONString());
        }
    }

    @RequestMapping(value = "aditem/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Boolean delete(@PathVariable("id") Long id) {
        return basADItemManage.delete(id) == 1;

    }

    @RequestMapping(value = "aditem/detail/{id}", method = RequestMethod.POST)
    @ResponseBody
    public BasAdItem detail(@PathVariable("id") Long id) {
        return basADItemManage.getById(id);
    }

}
