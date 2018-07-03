package com.tk.controller.api;

import com.tk.manage.DynamicManage;
import com.tk.util.HttpPostUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/api/auth/dynamic")
public class DynamicApiController extends ApiBaseController {

    @Autowired
    DynamicManage dynamicManage;

    @Autowired
    HttpPostUploadUtil imageUploadService;



//
//    @RequestMapping(value = "listbyuid")
//    @ResponseBody
//    public Map<String, Object> listbyuid(Long uid, @RequestParam(value = "pageno", defaultValue = "1") Integer pageno, @RequestParam(value = "pagesize", defaultValue = "20") Integer pagesize, HttpServletRequest request) {
//        Map<String, Object> resMap = new HashMap<String, Object>();
//        if (CommonUtils.isNull(uid)) {
//            resMap.put("code", ResultCode.PARAMETERS_EMPTY);
//            resMap.put("msg", "传入参数不能为空");
//            return resMap;
//        }
//        resMap.put("data", dynamicManage.listByUid(uid, pageno, pagesize));
//        resMap.put("code", ResultCode.SUCCESS);
//        return resMap;
//    }
//
//
//    @RequestMapping(value = "del")
//    @ResponseBody
//    public Map<String, Object> del(Long id, HttpServletRequest request) {
//        Map<String, Object> resMap = new HashMap<String, Object>();
//        if (CommonUtils.isNull(id)) {
//            resMap.put("code", ResultCode.PARAMETERS_EMPTY);
//            resMap.put("msg", "传入参数不能为空");
//            return resMap;
//        }
//        if(dynamicManage.delete(id)){
//            resMap.put("code", ResultCode.SUCCESS);
//        }else{
//            resMap.put("code", ResultCode.ERROR);
//        }
//        return resMap;
//    }
//
//
//    /**
//     * 评论列表
//     *
//     * @param did
//     * @param pageno
//     * @param pagesize
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "reply/list")
//    @ResponseBody
//    public Map<String, Object> replylist(Long did, @RequestParam(value = "pageno", defaultValue = "1") Integer pageno, @RequestParam(value = "pagesize", defaultValue = "20") Integer pagesize, HttpServletRequest request) {
//        Map<String, Object> resMap = new HashMap<String, Object>();
//        resMap.put("data", dynamicManage.listReplyByDid(did, pageno, pagesize));
//        resMap.put("code", ResultCode.SUCCESS);
//        return resMap;
//    }
//
//
//    @RequestMapping(value = "reply")
//    @ResponseBody
//    public Map<String, Object> reply(Long did, String uname, Long ruid, String runame, String content, HttpServletRequest request) {
//        Long uid = getUid(request);
//        DynamicReply vo = new DynamicReply();
//        vo.setUid(uid);
//        vo.setCreatedate(new Date());
//        vo.setKinds((byte) 1);
//        vo.setUname(uname);
//        vo.setDid(did);
//        vo.setRuid(ruid);
//        vo.setRuname(runame);
//        vo.setContent(content);
//        Map<String, Object> resMap = new HashMap<String, Object>();
//        long id = 0;
//        if ((id = dynamicManage.addReply(vo)) > 0) {
//            resMap.put("data", id);
//            resMap.put("code", ResultCode.SUCCESS);
//        } else {
//            resMap.put("code", ResultCode.ERROR);
//        }
//
//        return resMap;
//    }
//
//
//    /**
//     * 喜欢列表
//     *
//     * @param did
//     * @param pageno
//     * @param pagesize
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "like/list")
//    @ResponseBody
//    public Map<String, Object> likelist(Long did, @RequestParam(value = "pageno", defaultValue = "1") Integer pageno, @RequestParam(value = "pagesize", defaultValue = "20") Integer pagesize, HttpServletRequest request) {
//        Map<String, Object> resMap = new HashMap<String, Object>();
//        resMap.put("data", dynamicManage.listLikeByDid(did, pageno, pagesize));
//        resMap.put("code", ResultCode.SUCCESS);
//        return resMap;
//    }
//
//    @RequestMapping(value = "like")
//    @ResponseBody
//    public Map<String, Object> like(Long did, String uname, Long ruid, String runame, HttpServletRequest request) {
//        Long uid = getUid(request);
//        DynamicReply vo = new DynamicReply();
//        vo.setUid(uid);
//        vo.setCreatedate(new Date());
//        vo.setKinds((byte) 2);
//        vo.setUname(uname);
//        vo.setDid(did);
//        vo.setRuid(ruid);
//        vo.setRuname(runame);
//        Map<String, Object> resMap = new HashMap<String, Object>();
//        resMap.put("data", dynamicManage.addLike(vo));
//        resMap.put("code", ResultCode.SUCCESS);
//        return resMap;
//    }
//
//    @RequestMapping(value = "unreply")
//    @ResponseBody
//    public Map<String, Object> unreply(Long id, HttpServletRequest request) {
//        Map<String, Object> resMap = new HashMap<String, Object>();
//        if (dynamicManage.delectReply(id)) {
//            resMap.put("code", ResultCode.SUCCESS);
//        } else {
//            resMap.put("code", ResultCode.ERROR);
//        }
//        return resMap;
//    }
//
//
//    @RequestMapping(value = "unlike")
//    @ResponseBody
//    public Map<String, Object> unlike(Long id, HttpServletRequest request) {
//        Map<String, Object> resMap = new HashMap<String, Object>();
//        if (dynamicManage.delectLike(id)) {
//            resMap.put("code", ResultCode.SUCCESS);
//        } else {
//            resMap.put("code", ResultCode.ERROR);
//        }
//        return resMap;
//    }


}
