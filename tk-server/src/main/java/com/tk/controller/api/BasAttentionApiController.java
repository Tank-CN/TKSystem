package com.tk.controller.api;

import com.tk.manage.BasAttentionManage;
import com.tk.model.BasAttention;
import com.tk.util.CommonUtils;
import com.tk.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/auth/attention")
public class BasAttentionApiController extends ApiBaseController {

    @Autowired
    BasAttentionManage basAttentionManage;


    /**
     * 关注列表
     *
     * @param pageno
     * @param pagesize
     * @param request
     * @return
     */
    @RequestMapping(value = "my")
    @ResponseBody
    public Map<String, Object> my(Long uid,@RequestParam(value = "pageno", defaultValue = "1") Integer pageno, @RequestParam(value = "pagesize", defaultValue = "20") Integer pagesize, HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (CommonUtils.isNull(uid)) {
            resMap.put("code", ResultCode.PARAMETERS_EMPTY);
            resMap.put("msg", "传入参数不能为空");
            return resMap;
        }
        resMap.put("data", basAttentionManage.listSelf(uid, pageno, pagesize));
        resMap.put("code", ResultCode.SUCCESS);
        return resMap;
    }


    /**
     * 粉丝列表
     *
     * @param pageno
     * @param pagesize
     * @param request
     * @return
     */
    @RequestMapping(value = "fans")
    @ResponseBody
    public Map<String, Object> fans(Long uid,@RequestParam(value = "pageno", defaultValue = "1") Integer pageno, @RequestParam(value = "pagesize", defaultValue = "20") Integer pagesize, HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (CommonUtils.isNull(uid)) {
            resMap.put("code", ResultCode.PARAMETERS_EMPTY);
            resMap.put("msg", "传入参数不能为空");
            return resMap;
        }
        resMap.put("data", basAttentionManage.listAttention(uid, pageno, pagesize));
        resMap.put("code", ResultCode.SUCCESS);
        return resMap;
    }


    @RequestMapping(value = "attention")
    @ResponseBody
    public Map<String, Object> attention(Long auid, HttpServletRequest request) {
        Long uid = getUid(request);
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (CommonUtils.isNull(auid)) {
            resMap.put("code", ResultCode.PARAMETERS_EMPTY);
            resMap.put("msg", "传入参数不能为空");
            return resMap;
        }
        BasAttention basAttention=new BasAttention();
        basAttention.setCreatedate(new Date());
        basAttention.setType(Byte.valueOf("1"));
        basAttention.setUid(uid);
        basAttention.setAuid(auid);
        if (basAttentionManage.save(basAttention)) {
            resMap.put("code", ResultCode.SUCCESS);
        } else {
            resMap.put("code", ResultCode.ERROR);
        }
        return resMap;
    }


    @RequestMapping(value = "unattention")
    @ResponseBody
    public Map<String, Object> unattention(Long auid, HttpServletRequest request) {
        Long uid = getUid(request);
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (CommonUtils.isNull(auid)) {
            resMap.put("code", ResultCode.PARAMETERS_EMPTY);
            resMap.put("msg", "传入参数不能为空");
            return resMap;
        }
        if (basAttentionManage.delete(uid,auid)) {
            resMap.put("code", ResultCode.SUCCESS);
        } else {
            resMap.put("code", ResultCode.ERROR);
        }
        return resMap;
    }


    @RequestMapping(value = "isattention")
    @ResponseBody
    public Map<String, Object> isattention(Long auid, HttpServletRequest request) {
        Long uid = getUid(request);
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (CommonUtils.isNull(auid)) {
            resMap.put("code", ResultCode.PARAMETERS_EMPTY);
            resMap.put("msg", "传入参数不能为空");
            return resMap;
        }
        resMap.put("data", basAttentionManage.isAttention(uid,auid));
        resMap.put("code", ResultCode.SUCCESS);
        return resMap;
    }




}
