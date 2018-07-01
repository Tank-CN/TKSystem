package com.tk.controller.admin.business;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tk.controller.admin.AdminBaseController;
import com.tk.manage.BasBusinessManage;
import com.tk.manage.BusinessApplyManage;
import com.tk.manage.SysDictionaryManage;
import com.tk.model.Admin;
import com.tk.model.BasBusiness;
import com.tk.model.SysDictionary;
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
import java.util.*;

/**
 * 机构管理（医院管理）
 *
 * @author Administrator
 */

@Controller
@RequestMapping(value = "/admin/business")
public class BussinessController extends AdminBaseController {

    @Autowired
    BasBusinessManage basBusinessManage;

    @Autowired
    BusinessApplyManage businessApplyManage;


    @Autowired
    SysDictionaryManage sysDictionaryManage;


    @Autowired
    HttpPostUploadUtil imageUploadService;


    @RequestMapping(value = "fileupload", method = RequestMethod.POST)
    public void fileupload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject map = new JSONObject();
        if (file != null) {
            String ret = imageUploadService.formUpload(file, "960x0");
            if (!CommonUtils.isNull(ret)) {
                Map jmap = JSON.parseObject(ret, Map.class);
                if ("1".equals(jmap.get("code").toString())) {
                    String url=imageUploadService.getNetServiceUrl() + jmap.get("url");

                    map.put("url",getURL(url,"_960x0"));
                    map.put("filename","uploadfile");
                }
            }
        }
        response.getWriter().println(map.toJSONString());
    }

    String getURL(String url,String ext){
        StringBuffer sb=new StringBuffer();
        if(CommonUtils.isNotEmpty(url)){
            sb.append(url.substring(0,url.lastIndexOf(".")));
            sb.append(ext);
            sb.append(url.substring(url.lastIndexOf(".")));
        }
        return sb.toString();
    }


    @RequestMapping(value = "business/typelist", method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String, Object>> typelist(@RequestParam(value = "pid", defaultValue = "0") Long pid, @RequestParam(value = "children", defaultValue = "true") Boolean children) {
        List<SysDictionary> list = sysDictionaryManage.querySysDictionaryByPId(pid);
        List<Map<String, Object>> resList = new LinkedList<Map<String, Object>>();
        for (SysDictionary dic : list) {
            Map<String, Object> map = new HashMap<String, Object>();
            String parentIdStr = dic.getPid() == 0 ? "#" : dic.getPid().toString();
            map.put("id", dic.getIid());
//            map.put("level", basRegion.getLevel());
            map.put("parent", parentIdStr);
            map.put("text", dic.getTitle());
            map.put("pid", dic.getPid().toString());
            map.put("children", children);
            resList.add(map);
        }
        return resList;
    }


    @RequestMapping(value = "business")
    public ModelAndView basOrganization(String currentpage) {
        ModelAndView modelAndView = new ModelAndView("admin/business/business");
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
    @RequestMapping(value = "business/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(String title, String type, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "length", defaultValue = "20") Integer length, HttpServletRequest request) {
        Map<String, Object> regMsg = new HashMap<String, Object>();
        List<BasBusiness> list = null;
        if (CommonUtils.isNull(title) && CommonUtils.isNull(type)) {
            list = basBusinessManage.list(page, length);
        } else {
            list = basBusinessManage.search(title, type, page, length);
        }
        if (null != list && list.size() > 0) {
            for (BasBusiness b : list) {
                b.setIntroduce(null);
                b.setInfo(null);
            }
            regMsg.put("data", list);

        }
        regMsg.put("total", basBusinessManage.count(title, type));
        regMsg.put("code", ResultCode.SUCCESS);
        return regMsg;
    }


    @RequestMapping(value = "business/updateView")
    public ModelAndView updateView(Long id, String currentpage) {
        ModelAndView modelAndView = new ModelAndView("admin/business/business_update");
        modelAndView.addObject("id", id);
        modelAndView.addObject("currentpage", currentpage);
        return modelAndView;
    }

    @RequestMapping(value = "business/addView")
    public String addView() {
        return "admin/business/business_add";
    }

    @RequestMapping(value = "business/saveorupdate", method = RequestMethod.POST)
    public void insertOrUpdate(BasBusiness organization, @RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Admin account = getAdmin(request);
        JSONObject map = new JSONObject();
        boolean flag = false;
        if (file != null) {
            String ret = imageUploadService.formUpload(file, "240x180");
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
                map.put("code", basBusinessManage.update(organization));
                map.put("data", organization.getId());
                response.getWriter().println(map.toJSONString());
            } else {
                organization.setCreateuser(account.getId());
                organization.setCreatedate(new Date());
                map.put("code", ResultCode.SUCCESS);
                map.put("data", basBusinessManage.insertBackId(organization));
                response.getWriter().println(map.toJSONString());
            }
        } else {
            map.put("code", "0");
            response.getWriter().println(map.toJSONString());
        }
    }

    @RequestMapping(value = "business/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Boolean delete(@PathVariable("id") Long id) {
        return basBusinessManage.delete(id) == 1;

    }

    @RequestMapping(value = "business/detail/{id}", method = RequestMethod.POST)
    @ResponseBody
    public BasBusiness detail(@PathVariable("id") Long id) {
        return basBusinessManage.getById(id);
    }



    @RequestMapping(value = "apply")
    public ModelAndView businessapply(String currentpage) {
        ModelAndView modelAndView = new ModelAndView("admin/business/business_apply");
        modelAndView.addObject("currentpage", CommonUtils.isNull(currentpage) ? "1" : currentpage);
        return modelAndView;
    }


    @RequestMapping(value = "business/applylist", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> applylist(String title, String phone, Byte flag, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "length", defaultValue = "20") Integer length, HttpServletRequest request) {
        Map<String, Object> regMsg = new HashMap<String, Object>();
        regMsg.put("data", businessApplyManage.list(title, phone, flag, page, length));
        regMsg.put("total", businessApplyManage.count(title, phone, flag));
        regMsg.put("code", ResultCode.SUCCESS);
        return regMsg;
    }


    @RequestMapping(value = "business/applychange", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> applychange(Long id, Byte flag,HttpServletRequest request) {
        Map<String, Object> regMsg = new HashMap<String, Object>();
        businessApplyManage.changeStatue(id,flag);
        regMsg.put("code", ResultCode.SUCCESS);
        return regMsg;
    }


}
