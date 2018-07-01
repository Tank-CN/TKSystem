package com.tk.controller.api;

import com.tk.manage.ActivityManage;
import com.tk.model.ActivitySignup;
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
@RequestMapping(value = "/api/auth/activity")
public class ActivityApiController extends ApiBaseController {


    @Autowired
    ActivityManage activityManage;









    @RequestMapping(value = "listbyuid")
    @ResponseBody
    public Map<String, Object> listbyuid(@RequestParam(value = "pageno", defaultValue = "1") Integer pageno, @RequestParam(value = "pagesize", defaultValue = "20") Integer pagesize, HttpServletRequest request) {
        Long uid = getUid(request);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("data", activityManage.listByUid(uid,pageno, pagesize));
        resMap.put("code", ResultCode.SUCCESS);
        return resMap;
    }


    @RequestMapping(value = "sign")
    @ResponseBody
    public Map<String, Object> sign(ActivitySignup activitySignup, HttpServletRequest request) {
        Long uid = getUid(request);
        Map<String, Object> resMap = new HashMap<String, Object>();
        if(activityManage.sign(activitySignup)){
            resMap.put("msg", "报名成功");
            resMap.put("code", ResultCode.SUCCESS);
        }else{
            resMap.put("msg", "报名失败");
            resMap.put("code", ResultCode.ERROR);
        }
        return resMap;
    }

}
