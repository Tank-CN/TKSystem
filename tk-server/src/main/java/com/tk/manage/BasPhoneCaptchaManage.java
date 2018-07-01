package com.tk.manage;

import com.tk.mapper.BasPhoneCaptchaMapper;
import com.tk.model.BasPhoneCaptcha;
import com.tk.model.BasPhoneCaptchaExample;
import com.tk.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
@Transactional
public class BasPhoneCaptchaManage {

	// 验证码过期时间
	public static final Integer PHONE_CAPTCHA_EXPIRE_INTERVAL = 15;

	@Autowired
	private BasPhoneCaptchaMapper phoneCaptchaMapper;

	/**
	 * 验证码持久化
	 * 
	 * @param phoneCaptcha
	 * @return
	 */
	public Boolean addPhoneCode(BasPhoneCaptcha phoneCaptcha) {
		phoneCaptchaMapper.deleteByPrimaryKey(phoneCaptcha.getMobile());
		Integer temp = phoneCaptchaMapper.insert(phoneCaptcha);
		if (temp == 1) {
			return true;
		}
		return false;
	}

	/**
	 * 验证code是否正确
	 * 
	 * @param phoneNum
	 *            手机号码
	 * @param code
	 *            验证码
	 * @return
	 */
	public Map<String, Object> verifyCode(String phoneNum, String code) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		BasPhoneCaptchaExample example = new BasPhoneCaptchaExample();
		BasPhoneCaptchaExample.Criteria criteria = example.createCriteria();
		criteria.andMobileEqualTo(phoneNum);
		criteria.andCodeEqualTo(code);
		List<BasPhoneCaptcha> list = phoneCaptchaMapper
				.selectByExample(example);
		if (list.size() > 0) {
			BasPhoneCaptcha captcha = list.get(0);
			int i = DateUtils.dateCompare(DateUtils.dateFormate(
					captcha.getCreatedate(), "yyyy-MM-dd HH:mm:ss"), DateUtils
					.dateFormate(DateUtils.caclDate(new Date(), Calendar.MINUTE,
							-PHONE_CAPTCHA_EXPIRE_INTERVAL),
							"yyyy-MM-dd HH:mm:ss"));
			if (i < 0) {
				resMap.put("success", false);
				resMap.put("msg", "验证码失效,请重新获取!");
				return resMap;
			}
			// return true;
		} else {
			resMap.put("success", false);
			resMap.put("msg", "验证码错误");
			return resMap;
			// return false;
		}
		resMap.put("success", true);
		return resMap;
	}
}
