package com.tk.controller.api;

import com.tk.Constants;
import com.tk.manage.BasPhoneCaptchaManage;
import com.tk.manage.UserManage;
import com.tk.model.BasPhoneCaptcha;
import com.tk.model.BasUserDevice;
import com.tk.model.User;
import com.tk.service.SmsService;
import com.tk.util.*;
import com.tk.util.encryption.DESUtils;
import com.tk.util.encryption.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping(value = "/api")
public class UserController extends ApiBaseController{

    @Autowired
    UserManage userManage;

    @Autowired
    BasPhoneCaptchaManage basPhoneCaptchaManage;

    @Resource(name = "smsService")
    SmsService smsService;




    /**
     * 用户注册
     *
     * @param mobile
     * @param password
     * @param code
     * @param request
     * @return
     */
    @RequestMapping(value = "register")
    @ResponseBody
    public Map<String, Object> register(String mobile, String password, String code, HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (CommonUtils.isNull(mobile) || CommonUtils.isNull(password) || CommonUtils.isNull(code)) {
            resMap.put("code", ResultCode.PARAMETERS_EMPTY);
            resMap.put("msg", "传入参数不能为空");
            return resMap;
        }
        User haveAccount = userManage.getUserByMobile(mobile);
        if (haveAccount != null) {
            resMap.put("code", ResultCode.ERROR);
            resMap.put("msg", "账户已经存在");
            return resMap;
        }
        // 验证码
        Map<String, Object> captchaMap = basPhoneCaptchaManage.verifyCode(mobile, code);
        if (!(Boolean) captchaMap.get("success")) {
            resMap.put("code", ResultCode.ERROR);
            resMap.put("msg", captchaMap.get("msg"));
            return resMap;
        }

        if (PassWDUtils.validate(password)) {
            resMap.put("code", ResultCode.ERROR);
            resMap.put("msg", "密码过于简单");
            return resMap;
        }

        User account = null;
        try {
            account = userManage.create(mobile, password, RequestUtils.getRemoteAddress(request));
            resMap.put("code", ResultCode.SUCCESS);
            resMap.put("msg", "用户注册成功");
            resMap.put("data", createByAccount(account, getSN(account)));
            return resMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        resMap.put("code", ResultCode.ERROR);
        resMap.put("msg", "用户创建失败");
        return resMap;
    }

    /**
     * 账户登陆
     *
     * @param username 用户名(在这里其实是电话号码)
     * @param password 密码
     * @return
     */
    @RequestMapping(value = "login")
    @ResponseBody
    public Map<String, Object> login(@RequestParam("username") String username, @RequestParam("password") String password,  String uniquecode,
                                     String uniquecode1, String deviceId, Integer type, String devicename,
                                     String devicesysversion, HttpServletRequest request) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (CommonUtils.isNull(username) || CommonUtils.isNull(password)) {
            resMap.put("code", ResultCode.PARAMETERS_EMPTY);
            resMap.put("msg", "传入参数不能为空");
            return resMap;
        }

        User account = userManage.getUserByMobile(username);
        if (account == null) {
            resMap.put("code", ResultCode.ERROR);
            resMap.put("msg", "账户或密码不正确");
            return resMap;
        }
        String encryptPassworStr = MD5Utils.getMD5(password);
        if (encryptPassworStr.equals(account.getPassword())) {
            String token = UUID.randomUUID().toString();
            token = token.replace("-", "");
            account.setToken(token);
            //// TODO: 2016/6/29  更新用户信息  上次登录时间等
            userManage.updateUser(account);
            resMap.put("code", ResultCode.SUCCESS);
            resMap.put("data", createByAccount(account, getSN(account)));
            // 设备绑定处理
            if (!CommonUtils.isNull(type)) {
                deviceBind(account.getId(), uniquecode, uniquecode1, deviceId,
                        String.valueOf(type), devicename, devicesysversion);
            }
            resMap.put("msg", "登陆成功");
            return resMap;
        } else {
            resMap.put("code", ResultCode.ERROR);
            resMap.put("msg", "账户或密码不正确");
            return resMap;
        }
    }

    /**
     * 绑定设备
     * @param uid
     * @param uniquecode
     * @param uniquecode1
     * @param deviceId
     * @param type
     * @param devicename
     * @param devicesysversion
     */
    void deviceBind(Long uid, String uniquecode, String uniquecode1,
                    String deviceId, String type, String devicename,
                    String devicesysversion) {
        BasUserDevice device = new BasUserDevice();
        device.setUid(uid);
        device.setApptype((byte)1);
        device.setUniquecode(uniquecode);
        device.setDeviceId(deviceId);
        device.setType(Byte.valueOf(type));
        device.setDeviceName(devicename);
        device.setDeviceSysVersion(devicesysversion);
        device.setModifydate(new Date());
//        deviceCacheManage.deviceInfo(device);
    }



    /**
     * 消息推送绑定
     *
     * @return
     */
    @RequestMapping(value = "auth/device")
    @ResponseBody
    public Map<String, Object> device(Long uid, String uniquecode, String uniquecode1,
                                      String deviceId, String type, String devicename,
                                      String devicesysversion, HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (CommonUtils.isNull(uid) || CommonUtils.isNull(deviceId)
                || CommonUtils.isNull(type)) {
            resMap.put("code", ResultCode.PARAMETERS_PUSH_EMPTY);
            resMap.put("msg", "传入参数不能为空");
            return resMap;
        }

        // 登陆设备信息
        BasUserDevice device = new BasUserDevice();
        device.setUid(uid);
        device.setApptype((byte)1);
        device.setUniquecode(uniquecode);
        device.setDeviceId(deviceId);
        device.setType(Byte.valueOf(type));
        device.setDeviceName(devicename);
        device.setDeviceSysVersion(devicesysversion);
        device.setModifydate(new Date());
//        deviceCacheManage.deviceInfo(device);
        resMap.put("code", ResultCode.SUCCESS);
        resMap.put("msg", "绑定成功");
        return resMap;
    }


    /**
     * 退出
     *
     *            用户名
     * @return
     */
    @RequestMapping(value = "logout")
    @ResponseBody
    public Map<String, Object> logout(Long uid,HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (CommonUtils.isNull(uid)) {
            resMap.put("code", ResultCode.PARAMETERS_EMPTY);
            resMap.put("msg", "传入参数不能为空");
            return resMap;
        }
        // 删除设备绑定信息
        //// TODO: 2016/7/4
//        deviceCacheManage.logout(uid);
        resMap.put("code", ResultCode.SUCCESS);
        resMap.put("msg", "退出成功");
        return resMap;
    }


    /**
     * 检查手机号码是否注册[密码找回]
     *
     * @param mobile
     * @return
     */
    @RequestMapping(value = "findpsw/mobileisexists")
    @ResponseBody
    public Map<String, Object> mobileIsExists(String mobile) throws ParserConfigurationException, SAXException, IOException {
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (CommonUtils.isNull(mobile)) {
            resMap.put("code", ResultCode.PARAMETERS_EMPTY);
            resMap.put("msg", "传入参数不能为空");
            return resMap;
        }


        User account = userManage.getUserByMobile(mobile);
        if (account == null) {
            resMap.put("code", ResultCode.ERROR);
            resMap.put("msg", "手机号未注册");
            return resMap;
        } else {
            String code = RandomCharData.createData();
            BasPhoneCaptcha phoneCaptcha = new BasPhoneCaptcha(mobile, code, new Date());
            Boolean temp = basPhoneCaptchaManage.addPhoneCode(phoneCaptcha);
            if (temp) {
                return smsService.sendSmsCode(mobile, code, 1);
            } else {
                resMap.put("code", ResultCode.ERROR);
                resMap.put("msg", "验证码存储失败");
                return resMap;
            }
        }
    }

    /**
     * 手机验证码验证[密码找回]
     *
     * @param mobile 手机号码
     * @param code
     * @return
     */
    @RequestMapping(value = "findpsw/verifycode")
    @ResponseBody
    public Map<String, Object> verifyCode(String mobile, String code) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (CommonUtils.isNull(mobile) || CommonUtils.isNull(code)) {
            resMap.put("code", ResultCode.PARAMETERS_EMPTY);
            resMap.put("msg", "传入参数不能为空");
            return resMap;
        }


        Map<String, Object> captchaMap = basPhoneCaptchaManage.verifyCode(mobile, code);
        if ((Boolean) captchaMap.get("success")) {
            Map<String, Object> data = new HashMap<String, Object>();
            resMap.put("code", ResultCode.SUCCESS);
            resMap.put("msg", "验证码正确");
            resMap.put("data", data);
            return resMap;
        } else {
            resMap.put("code", ResultCode.ERROR);
            resMap.put("msg", captchaMap.get("msg"));
            return resMap;
        }
    }

    /**
     * 修改密码通过手机找回[密码找回]
     *
     * @param mobile   手机号码
     * @param password 新密码
     * @return
     */
    @RequestMapping(value = "findpsw/modifypwd")
    @ResponseBody
    public Map<String, Object> modifyPasswordByMobile(String mobile, String password) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (CommonUtils.isNull(mobile) || CommonUtils.isNull(password)) {
            resMap.put("code", ResultCode.PARAMETERS_EMPTY);
            resMap.put("msg", "传入参数不能为空");
            return resMap;
        }


        if (PassWDUtils.validate(password)) {
            resMap.put("code", ResultCode.ERROR);
            resMap.put("msg", "密码过于简单");
            return resMap;
        }

        User account = userManage.getUserByMobile(mobile);
        account.setPassword(MD5Utils.getMD5(password));
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

    public Map<String, Object> createByAccount(User user, String sn) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", user.getId());
        map.put("token", user.getToken());
        map.put("mobile", user.getMobile());
        map.put("username", user.getUsername());
        map.put("nickname", user.getNickname());
        map.put("info", user.getInfo());
        map.put("sexcode", user.getSexcode());
        map.put("vip", user.getVip());
        map.put("viptime", user.getViptime());
        map.put("sn", sn);
        map.put("birthdate", null == user.getBirthdate() ? null : user.getBirthdate().getTime());
        map.put("header", user.getHeader());
        return map;
    }

    public String getSN(User user) {
        StringBuffer sb = new StringBuffer();
        sb.append(user.getId()).append(";").append(user.getMobile()).append(";").append(user.getPassword()).append(";");
        sb.append(System.currentTimeMillis());
        return new DESUtils(Constants.DES_KEY).encryptStr(sb.toString());
    }
}
