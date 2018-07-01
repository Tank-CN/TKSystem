package com.tk.controller.api;

import com.tk.manage.AppNoticeSettingManage;
import com.tk.model.AppNoticeSetting;
import com.tk.util.CommonUtils;
import com.tk.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 消息设置
 * 
 * @author Administrator
 *
 */

@Controller
@RequestMapping(value = "/api/auth/appnotice")
public class AppNoticeController extends ApiBaseController {

	@Autowired
	AppNoticeSettingManage appNoticeSettingManage;

	@RequestMapping(value = "get")
	@ResponseBody
	public Map<String, Object> get(HttpServletRequest request) throws ParserConfigurationException, SAXException, IOException {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Long uid = getUid(request);
		resMap.put("code", ResultCode.SUCCESS);
		AppNoticeSetting set = appNoticeSettingManage.findByUid(uid);
		if (null != set) {
			resMap.put("data", set.getText());
		}
		return resMap;
	}

	@RequestMapping(value = "set")
	@ResponseBody
	public Map<String, Object> set(AppNoticeSetting vo) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		if (CommonUtils.isNull(vo.getText()) || CommonUtils.isNull(vo.getUid())) {
			resMap.put("code", ResultCode.PARAMETERS_EMPTY);
			resMap.put("msg", "传入参数不能为空");
			return resMap;
		} else {
			AppNoticeSetting set = appNoticeSettingManage.findByUid(vo.getUid());
			if (null != set) {
				//更新
				appNoticeSettingManage.update(vo);
			} else {
				//新增
				appNoticeSettingManage.save(vo);
			}
			resMap.put("code", ResultCode.SUCCESS);
			return resMap;
		}
	}
}
