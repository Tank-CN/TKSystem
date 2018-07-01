package com.tk.controller.admin.business;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tk.controller.admin.AdminBaseController;
import com.tk.manage.NewsManage;
import com.tk.model.News;
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

@Controller
@RequestMapping(value = "/admin/business")
public class NewsController extends AdminBaseController {

    @Autowired
    NewsManage newsManage;


    @Autowired
    HttpPostUploadUtil imageUploadService;

    @RequestMapping(value = "news")
    public ModelAndView basOrganization(String currentpage) {
        ModelAndView modelAndView = new ModelAndView("admin/business/news");
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
    @RequestMapping(value = "news/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "length", defaultValue = "20") Integer length,  HttpServletRequest request) {

        Map<String, Object> regMsg = new HashMap<String, Object>();
        regMsg.put("data", newsManage.list(page, length));
        regMsg.put("total", newsManage.count());
        regMsg.put("code", ResultCode.SUCCESS);
        return regMsg;
    }


    @RequestMapping(value = "news/updateView")
    public ModelAndView updateView(Long id, String currentpage) {
        ModelAndView modelAndView = new ModelAndView("admin/business/news_update");
        modelAndView.addObject("id", id);
        modelAndView.addObject("currentpage", currentpage);
        return modelAndView;
    }

    @RequestMapping(value = "news/addView")
    public String addView() {
        return "admin/business/news_add";
    }

    @RequestMapping(value = "news/saveorupdate", method = RequestMethod.POST)
    public void insertOrUpdate(News organization, @RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject map = new JSONObject();
        boolean flag = false;
        if (file != null) {
            String ret = imageUploadService.formUpload(file, "200x200");
            if (!CommonUtils.isNull(ret)) {
                Map jmap = JSON.parseObject(ret, Map.class);
                if ("1".equals(jmap.get("code").toString())) {
                    flag = true;
                    organization.setImgurl(imageUploadService.getNetServiceUrl() + jmap.get("url"));
                }
            }
        }
        if (flag || file == null) {
            if (organization.getId() != null) {
                map.put("code", newsManage.update(organization));
                map.put("data", organization.getId());
                response.getWriter().println(map.toJSONString());
            } else {
                organization.setCreatedate(new Date());
                map.put("code", ResultCode.SUCCESS);
                map.put("data", newsManage.insertBackId(organization));
                response.getWriter().println(map.toJSONString());
            }
        } else {
            map.put("code", "0");
            response.getWriter().println(map.toJSONString());
        }
    }

    @RequestMapping(value = "news/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Boolean delete(@PathVariable("id") Long id) {
        return newsManage.delete(id) == 1;

    }

    @RequestMapping(value = "news/detail/{id}", method = RequestMethod.POST)
    @ResponseBody
    public News detail(@PathVariable("id") Long id) {
        return newsManage.getById(id);
    }

}
