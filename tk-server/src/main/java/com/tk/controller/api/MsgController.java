package com.tk.controller.api;

import com.tk.manage.NewsManage;
import com.tk.util.ResultCode;
import com.tk.vo.MsgVo;
import com.tk.vo.NewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/auth/msg")
public class MsgController extends ApiBaseController{

    @Autowired
    NewsManage newsManage;


    /**
     * 消息列表
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "list")
    @ResponseBody
    public Map<String, Object> list(HttpServletRequest request)  {
        Map<String, Object> resMap = new HashMap<String, Object>();
        Long uid = getUid(request);
        List<MsgVo> list=new ArrayList<>();
        //// TODO: 2016/7/16   系统消息


        //头条新闻
        NewsVo news=newsManage.getHotestNews();
        MsgVo msgVo=new MsgVo();
        msgVo.setContent(news.getDes());
        msgVo.setDate(news.getCreatedate());
        msgVo.setCount(1);
        msgVo.setKinds("2");
        list.add(msgVo);

        resMap.put("data",list);
        resMap.put("code", ResultCode.SUCCESS);
        return resMap;
    }
}
