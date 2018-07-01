package com.tk.controller.api;

import com.tk.manage.BasTipManage;
import com.tk.model.BasTip;
import com.tk.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 举报
 */
@Controller
@RequestMapping(value = "/api/auth/tip")
public class TipApiController extends ApiBaseController {

    @Autowired
    BasTipManage basTipManage;


    /**
     * 1 用户 2 动态 3 商家
     * @return
     */
    @RequestMapping(value = "")
    @ResponseBody
    public Map<String, Object> del(BasTip basTip,HttpServletRequest request) {
        basTip.setCreatedate(new Date());
        basTip.setStatus(Byte.valueOf("0"));
        Map<String, Object> resMap = new HashMap<String, Object>();
        if(basTipManage.save(basTip)){
            resMap.put("code", ResultCode.SUCCESS);
        }else{
            resMap.put("code", ResultCode.ERROR);
        }
        return resMap;
    }



}
