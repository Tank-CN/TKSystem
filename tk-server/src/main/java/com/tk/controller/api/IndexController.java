package com.tk.controller.api;

import com.tk.manage.*;
import com.tk.util.CommonUtils;
import com.tk.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/api")
public class IndexController extends ApiBaseController {


    @Autowired
    BasADBannerManage basADBannerManage;

    @Autowired
    BasADItemManage basADItemManage;

    @Autowired
    BasBusinessManage basBusinessManage;

    @Autowired
    DynamicManage dynamicManage;


    @Autowired
    ActivityManage activityManage;

    @Autowired
    NewsManage newsManage;

    @RequestMapping(value = "index")
    @ResponseBody
    public Map index() {
        Map<String, Object> resMap = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();
        //banner
        dataMap.put("adBannerVos", basADBannerManage.listVo(1, 6));
        //广告栏
        dataMap.put("adItemVos", basADItemManage.listVo(1, 6));
        //头条
        dataMap.put("newsVo", newsManage.getHotestNews());
        //热门商家
        dataMap.put("businessVos", basBusinessManage.listByType(null, null, 1, 8));
        resMap.put("data", dataMap);
        resMap.put("code", ResultCode.SUCCESS);
        return resMap;
    }


    @RequestMapping(value = "dynamic/list")
    @ResponseBody
    public Map<String, Object> dynamiclist(@RequestParam(value = "pageno", defaultValue = "1") Integer pageno, @RequestParam(value = "pagesize", defaultValue = "20") Integer pagesize, HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("data", dynamicManage.listAll(pageno, pagesize));
        resMap.put("code", ResultCode.SUCCESS);
        return resMap;
    }

    @RequestMapping(value = "activity/list")
    @ResponseBody
    public Map<String, Object> activitylist(@RequestParam(value = "pageno", defaultValue = "1") Integer pageno, @RequestParam(value = "pagesize", defaultValue = "20") Integer pagesize, HttpServletRequest request) {
        String uid = request.getParameter("id");
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("data", activityManage.listAll(null != uid ? Long.valueOf(uid) : null, pageno, pagesize));
        resMap.put("code", ResultCode.SUCCESS);
        return resMap;
    }

    @RequestMapping(value = "news/list")
    @ResponseBody
    public Map<String, Object> newslist(@RequestParam(value = "pageno", defaultValue = "1") Integer pageno, @RequestParam(value = "pagesize", defaultValue = "20") Integer pagesize, HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("data", newsManage.listNews(pageno, pagesize));
        resMap.put("code", ResultCode.SUCCESS);
        return resMap;
    }


    //商户
    @RequestMapping(value = "business/list")
    @ResponseBody
    public Map<String, Object> list(Long typeid, Long typeiid, @RequestParam(value = "pageno", defaultValue = "1") Integer pageno, @RequestParam(value = "pagesize", defaultValue = "20") Integer pagesize, HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("list", basBusinessManage.listByType(typeid, typeiid, pageno, pagesize));
        //只要第一次请求时才带上类别
        if (pageno == 1) {
            dataMap.put("types", basBusinessManage.listType(typeid));
        }
        resMap.put("data", dataMap);
        resMap.put("code", ResultCode.SUCCESS);
        return resMap;
    }


    @RequestMapping(value = "business/detail")
    @ResponseBody
    public Map<String, Object> detail(Long id, HttpServletRequest request) {
        String uid = request.getParameter("uid");
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (CommonUtils.isNull(id)) {
            resMap.put("code", ResultCode.PARAMETERS_EMPTY);
            resMap.put("msg", "传入参数不能为空");
            return resMap;
        }
        Map<String, Object> dataMap = new HashMap<String, Object>();
        if (!CommonUtils.isNull(uid)) {
            dataMap.put("isCollect", basBusinessManage.isCollect(Long.valueOf(uid), id));
        }
        dataMap.put("replyVos", basBusinessManage.listReplyByBid(id, 1, 10));
        resMap.put("data", dataMap);
        resMap.put("code", ResultCode.SUCCESS);
        return resMap;
    }

}
