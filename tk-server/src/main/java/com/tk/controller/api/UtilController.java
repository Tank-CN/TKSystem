package com.tk.controller.api;

import com.tk.manage.BasPhoneCaptchaManage;
import com.tk.model.BasPhoneCaptcha;
import com.tk.service.SmsService;
import com.tk.util.CommonUtils;
import com.tk.util.RandomCharData;
import com.tk.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;

import javax.annotation.Resource;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/util")
public class UtilController extends ApiBaseController {
    @Autowired
    BasPhoneCaptchaManage basPhoneCaptchaManage;

    @Resource(name = "smsService")
    SmsService smsService;


    /**
     * 需要考虑安全问题 发送验证码
     *
     * @param mobile
     * @return
     */
    @RequestMapping(value = "phonecode")
    @ResponseBody
    public Map<String, Object> sendPhoneCode(String mobile) throws ParserConfigurationException, SAXException, IOException {
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (!CommonUtils.isNull(mobile)) {
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
        } else {
            resMap.put("code", ResultCode.ERROR);
            resMap.put("msg", "手机号码不正确");
            return resMap;
        }
    }

    /**
     * 安全考虑 手机验证码验证
     *
     * @param mobile 手机号码
     * @param code
     * @return
     */
    @RequestMapping(value = "phonecode/verify")
    @ResponseBody
    public Map<String, Object> verifyCode(String mobile, String code) {
        Map<String, Object> resMap = new HashMap<String, Object>();

        if (!CommonUtils.isNull(mobile) && !CommonUtils.isNull(code)) {
            Map<String, Object> captchaMap = basPhoneCaptchaManage.verifyCode(mobile, code);
            if ((Boolean) captchaMap.get("success")) {
                resMap.put("code", ResultCode.SUCCESS);
                resMap.put("msg", "验证码正确");
                return resMap;
            } else {
                resMap.put("code", ResultCode.ERROR);
                resMap.put("msg", captchaMap.get("msg"));
                return resMap;
            }
        } else {
            resMap.put("code", ResultCode.ERROR);
            resMap.put("msg", "手机号码、验证码参数传递错误");
            return resMap;
        }
    }

}
