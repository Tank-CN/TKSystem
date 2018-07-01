package com.tk.controller;

import com.tk.manage.*;
import com.tk.model.*;
import com.tk.push.PushInfo;
import com.tk.push.impl.JPushService;
import com.tk.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/test")
public class TestController {

    String s_url =null;

//    String s_url ="http://114.55.253.28:8090/";

    @Autowired
    JPushService jPushService;

    @Autowired
    ActivityManage activityManage;

    @Autowired
    UserManage userManage;

    @Autowired
    BasADBannerManage basADBannerManage;

    @Autowired
    BasADItemManage basADItemManage;

    @Autowired
    BasBusinessManage basBusinessManage;


    @Autowired
    DynamicManage dynamicManage;

    @Autowired
    NewsManage newsManage;


    @RequestMapping(value = "index")
    @ResponseBody
    public Map<String, Object> index(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        List<Activity> lista = activityManage.list(null,1, 1000);
        for (Activity activity : lista) {
            activity.setImgurl(getURL(activity.getImgurl()));
            activityManage.update(activity);
        }
        List<User> listu = userManage.list(1, 1000);
        for (User activity : listu) {
            if(!StringUtils.isEmpty(activity.getHeader())) {
                activity.setHeader(getURL(activity.getHeader()));
                userManage.update(activity);
            }
        }
        List<BasAdBanner> listbanner = basADBannerManage.list(1, 1000);
        for (BasAdBanner activity : listbanner) {
            activity.setPicurl(getURL(activity.getPicurl()));
            basADBannerManage.update(activity);
        }
        List<BasAdItem> listitem = basADItemManage.list(1, 1000);
        for (BasAdItem activity : listitem) {
            activity.setPicurl(getURL(activity.getPicurl()));
            basADItemManage.update(activity);
        }
        List<BasBusiness> listbus = basBusinessManage.list(1, 1000);
        for (BasBusiness activity : listbus) {
            activity.setPicurl(getURL(activity.getPicurl()));
            basBusinessManage.update(activity);
        }
        List<Dynamic> listd = dynamicManage.list(1, 1000);
        for (Dynamic activity : listd) {
            activity.setImgurl(getURL(activity.getImgurl()));
            dynamicManage.update(activity);
        }
        List<News> listn = newsManage.list(1, 1000);
        for (News activity : listn) {
            activity.setImgurl(getURL(activity.getImgurl()));
            newsManage.update(activity);
        }
        return resMap;
    }


    public String getURL(String url) {
        if(CommonUtils.isEmpty(url)){
            return url;
        }
        return (null==s_url?getIP():s_url) + url.substring(url.indexOf("8090/") + 5);
    }


    @RequestMapping(value = "testpushmsg")
    @ResponseBody
    public Map<String, Object> testpushmsg(BasAppAdvise advise, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        PushInfo pushInfo = new PushInfo();
        pushInfo.setTitle("测试标题");
        pushInfo.setDescription("测试描述");
        pushInfo.setKinds(1);
        pushInfo.setType(1);
        jPushService.pushMsgSigle(1l, pushInfo);
        map.put("data", "");
        return map;
    }

    @RequestMapping(value = "testpushalert")
    @ResponseBody
    public Map<String, Object> testpushalert(BasAppAdvise advise, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        PushInfo pushInfo = new PushInfo();
        pushInfo.setTitle("测试标题");
        pushInfo.setDescription("测试描述");
        pushInfo.setKinds(1);
        pushInfo.setType(1);
        jPushService.pushAlertSigle(1l, pushInfo);
        map.put("data", "");
        return map;
    }


    public String getIP() {
        InetAddress localAddress = null;
        try {
            localAddress = InetAddress.getLocalHost();
            s_url="http://"+localAddress.getHostAddress()+":8090/";
            return s_url;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }
}
