package com.tk.controller.api;

import com.tk.manage.BasAppVersionManage;
import com.tk.model.BasAppVersion;
import com.tk.util.CommonUtils;
import com.tk.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 版本模块
 * 
 * @author Administrator
 *
 */

@Controller
@RequestMapping(value = "/api/version")
public class AppVersionController {

	@Autowired
	BasAppVersionManage appVersionManage;

	/**
	 * 当前版本号
	 * 
	 * @param appcode
	 * @return
	 */
	@RequestMapping(value = "")
	@ResponseBody
	public Map<String, Object> version(String appcode, Integer appversion) {
		Map<String, Object> map = new HashMap<>();
		if (CommonUtils.isNull(appcode) || CommonUtils.isNull(appversion)) {
			map.put("code", ResultCode.PARAMETERS_EMPTY);
			map.put("msg", "传入参数不能为空");
			return map;
		}
		BasAppVersion version = appVersionManage.getByCode(appcode);
		if (null == version) {
			map.put("code", ResultCode.ERROR);
			return map;
		} else {
			if (appversion >= version.getAppversion()) {
				map.put("code", ResultCode.ERROR);
				return map;
			} else {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("appversion", version.getAppversion());
				data.put("appurl", version.getAppurl());
				data.put("type", version.getType());
				data.put("des", version.getDes());
				map.put("code", ResultCode.SUCCESS);
				map.put("data", data);
				return map;
			}
		}
	}

//	/**
//	 * 检查app是否需要更新
//	 *
//	 * 如果appCode数据库中没有那么是不需要更新的 app版本号超出数据库内存储的范围需要更新值最新版本 app版本低于需要强制更新的版本，需要更新
//	 *
//	 * @param appCode
//	 *            app的code
//	 * @param appVeresion
//	 *            app版本号
//	 * @return
//	 */
//	@RequestMapping(value = "checkeVersion", method = RequestMethod.POST)
//	@ResponseBody
//	public Map<String, Object> checkVersion(String appCode, Integer appVeresion) {
//		Map<String, Object> map = new HashMap<>();
//		if (CommonUtils.isNull(appCode) || null == appVeresion) {
//			map.put("code", ResultCode.PARAMETERS_EMPTY);
//			map.put("msg", "传入参数不能为空");
//			return map;
//		}
//		Map<String, Object> updateMap = appVersionManage.checkLastUpgradeVersion(appCode, appVeresion);
//		String updateCode = (String) updateMap.get("code");
//		if ("S01".equals(updateCode)) {
//			Map<String, String> data = new HashMap<String, String>();
//			data.put("code", "401");
//			data.put("msg", "不需要更新");
//			map.put("code", "0");
//			map.put("msg", "");
//			map.put("data", data);
//			return map;
//		} else if ("S02".equals(updateCode)) {
//			BasAppVersion appVersion = (BasAppVersion) updateMap.get("data");
//
//			Map<String, String> data = new HashMap<String, String>();
//			data.put("code", "402");
//			data.put("msg", "只需要更新");
//			data.put("url", "http://ecard.zgjkw.cn" + appVersion.getAppurl());
//			data.put("descr", appVersion.getDes());
//
//			map.put("code", "0");
//			map.put("msg", "");
//			map.put("data", data);
//			return map;
//		} else {
//			BasAppVersion appVersion = (BasAppVersion) updateMap.get("data");
//			Map<String, String> data = new HashMap<String, String>();
//			data.put("code", "403");
//			data.put("msg", "需要强制更新");
//			data.put("url", "http://ecard.zgjkw.cn" + appVersion.getAppurl());
//			data.put("descr", appVersion.getDes());
//
//			map.put("code", "0");
//			map.put("msg", "");
//			map.put("data", data);
//			return map;
//		}
//	}
//
//	/**
//	 * 检查app是否需要更新
//	 *
//	 * 如果appCode数据库中没有那么是不许要更新的 app版本号超出数据库内存储的范围需要更新值最新版本 app版本低于需要强制更新的版本，需要更新
//	 *
//	 * @param appcode
//	 *            app的code
//	 * @param appveresion
//	 *            app版本号
//	 * @return
//	 */
//	@RequestMapping(value = "checkdVersion", method = RequestMethod.POST)
//	@ResponseBody
//	public Map<String, Object> checkedVersion(String appcode, Integer appveresion) {
//		Map<String, Object> map = new HashMap<>();
//		if (CommonUtils.isNull(appcode) || null == appveresion) {
//			map.put("code", ResultCode.PARAMETERS_EMPTY);
//			map.put("msg", "传入参数不能为空");
//			return map;
//		}
//		Map<String, Object> updateMap = appVersionManage.checkLastVersion(appcode, appveresion);
//		return updateMap;
//	}

}
