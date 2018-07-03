package com.tk.controller.api;

import com.tk.manage.DynamicManage;
import com.tk.manage.NewsManage;
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
    DynamicManage dynamicManage;

    @Autowired
    NewsManage newsManage;

//    @RequestMapping(value = "index")
//    @ResponseBody
//    public Map index() {
//        Map<String, Object> resMap = new HashMap<>();
//        Map<String, Object> dataMap = new HashMap<>();
//        //banner
//        dataMap.put("adBannerVos", basADBannerManage.listVo(1, 6));
//        //广告栏
//        dataMap.put("adItemVos", basADItemManage.listVo(1, 6));
//        //头条
//        dataMap.put("newsVo", newsManage.getHotestNews());
//        //热门商家
//        dataMap.put("businessVos", basBusinessManage.listByType(null, null, 1, 8));
//        resMap.put("data", dataMap);
//        resMap.put("code", ResultCode.SUCCESS);
//        return resMap;
//    }




    @RequestMapping(value = "news/list")
    @ResponseBody
    public Map<String, Object> newslist(@RequestParam(value = "pageno", defaultValue = "1") Integer pageno, @RequestParam(value = "pagesize", defaultValue = "20") Integer pagesize, HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("data", newsManage.listNews(pageno, pagesize));
        resMap.put("code", ResultCode.SUCCESS);
        return resMap;
    }






}
