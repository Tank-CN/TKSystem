package com.tk.controller.api;

import com.tk.manage.DynamicManage;
import com.tk.manage.UserManage;
import com.tk.model.User;
import com.tk.util.CommonUtils;
import com.tk.util.PassWDUtils;
import com.tk.util.ResultCode;
import com.tk.util.encryption.MD5Utils;
import com.tk.vo.MyInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/auth/user")
public class UserInfoController extends ApiBaseController {

    @Autowired
    UserManage userManage;

    @Autowired
    DynamicManage dynamicManage;


    /**
     * 个人首页
     * 动态数，收藏数
     */
    @RequestMapping(value = "index", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> index(HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        Long uid = getUid(request);
        if (CommonUtils.isNull(uid)) {
            resMap.put("code", ResultCode.PARAMETERS_EMPTY);
            resMap.put("msg", "传入参数不能为空");
            return resMap;
        }
        MyInfoVo vo = new MyInfoVo();
        vo.setDynamicCount(dynamicManage.countByUid(uid));
//        User user = userManage.getUserById(uid);
//        if (null != user) {
//            UserVo userVo = new UserVo();
//            userVo.setNickname(user.getNickname());
//            userVo.setHeader(user.getHeader());
//            userVo.setBirthdate(user.getBirthdate());
//            userVo.setId(user.getId());
//            userVo.setInfo(user.getInfo());
//            userVo.setSexcode(user.getSexcode());
//            userVo.setVip(user.getVip());
//            vo.setUserVo(userVo);
//        }
//        vo.setUserVo(userManage.getUserVo(uid));
        resMap.put("data", vo);
        resMap.put("code", ResultCode.SUCCESS);
        return resMap;
    }


    /**
     * 得到用户信息
     *
     * @param uid
     * @param request
     * @return
     */
    @RequestMapping(value = "infoById", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> infoById(String uid, HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (CommonUtils.isEmpty(uid)) {
            resMap.put("code", ResultCode.PARAMETERS_EMPTY);
            return resMap;
        }
//        resMap.put("data", userManage.getUserVo(Long.valueOf(uid)));
        resMap.put("code", ResultCode.SUCCESS);
        return resMap;
    }


    /**
     * 得到用户信息-环信
     *
     * @param ids
     * @param request
     * @return
     */
    @RequestMapping(value = "info", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> info(String ids, HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (CommonUtils.isEmpty(ids)) {
            resMap.put("code", ResultCode.PARAMETERS_EMPTY);
            return resMap;
        }
//        resMap.put("data", userManage.getUserVoByEB(ids));
        resMap.put("code", ResultCode.SUCCESS);
        return resMap;
    }


    /**
     * 搜索用户
     *
     * @param key
     * @param pageno
     * @param pagesize
     * @param request
     * @return
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> search(String key, @RequestParam(value = "pageno", defaultValue = "1") Integer pageno, @RequestParam(value = "pagesize", defaultValue = "20") Integer pagesize, HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("data", userManage.search(key, pageno, pagesize));
        resMap.put("code", ResultCode.SUCCESS);
        return resMap;
    }


    /**
     * 修改密码
     *
     * @param oldpwd 原始密码
     * @param newpwd 新密码
     * @return
     */
    @RequestMapping(value = "modifypassword", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> modifyPassword(String oldpwd, String newpwd, HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        Long uid = getUid(request);
        if (CommonUtils.isNull(oldpwd) || CommonUtils.isNull(newpwd)) {
            resMap.put("code", ResultCode.PARAMETERS_EMPTY);
            resMap.put("msg", "传入参数不能为空");
            return resMap;
        }
        User account = userManage.getUserById(uid);
        if (account == null) {
            resMap.put("code", ResultCode.ERROR);
            resMap.put("msg", "账户不存在");
            return resMap;
        }

        String encryptionOldPassword = MD5Utils.getMD5(oldpwd);
        if (!encryptionOldPassword.equals(account.getPassword())) {
            resMap.put("code", ResultCode.ERROR);
            resMap.put("msg", "旧密码不正确");
            return resMap;
        }
        if (PassWDUtils.validate(newpwd)) {
            resMap.put("code", ResultCode.ERROR);
            resMap.put("msg", "密码过于简单");
            return resMap;
        }
        account.setPassword(MD5Utils.getMD5(newpwd));
        if (userManage.updateUser(account)) {
            resMap.put("code", ResultCode.SUCCESS);
            resMap.put("msg", "修改成功");
            return resMap;
        } else {
            resMap.put("code", ResultCode.ERROR);
            resMap.put("msg", "修改失败,数据库写入失败,联系开发者");
            return resMap;
        }
    }

    /**
     * 修改用户姓名
     *
     * @param name 姓名
     * @return
     */
    @RequestMapping(value = "modify/name")
    @ResponseBody
    public Map<String, Object> modifyName(String name, HttpServletRequest request) {
        Long uid = getUid(request);
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (CommonUtils.isNull(uid) || CommonUtils.isNull(name)) {
            resMap.put("code", ResultCode.PARAMETERS_EMPTY);
            resMap.put("msg", "参数不能为空");
            return resMap;
        }
        String temp = userManage.modifyName(uid, name);
        if ("S01".equals(temp)) {
            resMap.put("code", ResultCode.ERROR);
            resMap.put("msg", "用户不存在");
            return resMap;
        } else if ("S02".equals(temp)) {
            resMap.put("code", ResultCode.ERROR);
            resMap.put("msg", "修改失败,联系管理员");
            return resMap;
        }
//        userManage.restUserCache(uid);
        resMap.put("code", ResultCode.SUCCESS);
        resMap.put("msg", "修改成功");
        return resMap;

    }


    /**
     * 修改个性签名
     *
     * @param info 个性签名
     * @return
     */
    @RequestMapping(value = "modify/info")
    @ResponseBody
    public Map<String, Object> modifyInfo(String info, HttpServletRequest request) {
        Long uid = getUid(request);
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (CommonUtils.isNull(uid) || CommonUtils.isNull(info)) {
            resMap.put("code", ResultCode.PARAMETERS_EMPTY);
            resMap.put("msg", "参数不能为空");
            return resMap;
        }
        String temp = userManage.modifyInfo(uid, info);
        if ("S01".equals(temp)) {
            resMap.put("code", ResultCode.ERROR);
            resMap.put("msg", "用户不存在");
            return resMap;
        } else if ("S02".equals(temp)) {
            resMap.put("code", ResultCode.ERROR);
            resMap.put("msg", "修改失败,联系管理员");
            return resMap;
        }
//        userManage.restUserCache(uid);
        resMap.put("code", ResultCode.SUCCESS);
        resMap.put("msg", "修改成功");
        return resMap;

    }

    /**
     * 修改生日
     *
     * @param birthday
     * @param request
     * @return
     */
    @RequestMapping(value = "modify/birthday")
    @ResponseBody
    public Map<String, Object> modifyBirthday(Date birthday, HttpServletRequest request) {
        Long uid = getUid(request);
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (uid == null || birthday == null) {
            resMap.put("code", ResultCode.PARAMETERS_EMPTY);
            resMap.put("msg", "参数不能为空");
            return resMap;
        }
        String temp = userManage.modifyBirthday(uid, birthday);
        if ("S01".equals(temp)) {
            resMap.put("code", ResultCode.ERROR);
            resMap.put("msg", "用户不存在");
            return resMap;
        } else if ("S02".equals(temp)) {
            resMap.put("code", ResultCode.ERROR);
            resMap.put("msg", "修改失败,联系管理员");
            return resMap;
        }
//        userManage.restUserCache(uid);
        resMap.put("code", ResultCode.SUCCESS);
        resMap.put("msg", "修改成功");
        return resMap;

    }


    /**
     * 修改用户性别
     *
     * @param sexcode 性别编码
     * @return
     */
    @RequestMapping(value = "modify/sex")
    @ResponseBody
    public Map<String, Object> modifySex(Byte sexcode, HttpServletRequest request) {
        Long uid = getUid(request);
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (uid == null || sexcode == null) {
            resMap.put("code", ResultCode.PARAMETERS_EMPTY);
            resMap.put("msg", "参数不能为空");
            return resMap;
        }
        String temp = userManage.modifySex(uid, sexcode);
        if ("S01".equals(temp)) {
            resMap.put("code", ResultCode.ERROR);
            resMap.put("msg", "用户不存在");
            return resMap;
        } else if ("S02".equals(temp)) {
            resMap.put("code", ResultCode.ERROR);
            resMap.put("msg", "修改失败,联系管理员");
            return resMap;
        }
//        userManage.restUserCache(uid);
        resMap.put("code", ResultCode.SUCCESS);
        resMap.put("msg", "修改成功");
        return resMap;

    }


}
