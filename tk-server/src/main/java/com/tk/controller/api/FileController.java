package com.tk.controller.api;

import com.alibaba.fastjson.JSON;
import com.tk.manage.DynamicManage;
import com.tk.manage.UserManage;
import com.tk.model.Dynamic;
import com.tk.model.User;
import com.tk.util.CommonUtils;
import com.tk.util.HttpPostUploadUtil;
import com.tk.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传
 * 
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/api/upload")
public class FileController extends ApiBaseController {

	@Autowired
	UserManage userManage;

	@Autowired
	DynamicManage dynamicManage;

//	@Autowired
//	MsgRecordManage msgRecordManage;

	@Autowired
	HttpPostUploadUtil imageUploadService;

	/**
	 * 头像上传
	 * 
	 * @param uid
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "header")
	@ResponseBody
	public Map<String, Object> header(Long uid, @RequestParam(value = "file", required = false) CommonsMultipartFile file, HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		if (CommonUtils.isNull(uid) || null == file || file.isEmpty()) {
			resMap.put("code", ResultCode.PARAMETERS_EMPTY);
			resMap.put("msg", "传入参数不能为空");
			return resMap;
		}
		User account = new User();
		boolean flag = false;
		if (file != null) {
			String ret = imageUploadService.formUpload(file, "150x150");
			if (!CommonUtils.isNull(ret)) {
				Map jmap = JSON.parseObject(ret, Map.class);
				if ("1".equals(jmap.get("code").toString())) {
					flag = true;
					account.setHeader(imageUploadService.getNetServiceUrl() + jmap.get("url"));
				}
			}
		}
		if (flag) {
			account.setId(uid);
			if (userManage.updateUser(account)) {
				//修改用户缓存
				userManage.restUserCache(uid);
				resMap.put("code", ResultCode.SUCCESS);
				resMap.put("msg", "头像上传成功");
				resMap.put("data", account.getHeader());
				return resMap;
			} else {
				resMap.put("code", ResultCode.ERROR);
				resMap.put("msg", "头像上传失败");
				return resMap;
			}
		} else {
			resMap.put("code", ResultCode.ERROR);
			resMap.put("msg", "头像存储失败");
			return resMap;
		}

	}


	@RequestMapping(value = "dynamic/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(Dynamic dynamic, @RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> resMap = new HashMap<String, Object>();
		boolean flag = false;
		if (file != null) {
			String ret = imageUploadService.formUpload(file, "720x0","360x0");
			if (!CommonUtils.isNull(ret)) {
				Map jmap = JSON.parseObject(ret, Map.class);
				if ("1".equals(jmap.get("code").toString())) {
					flag = true;
					dynamic.setImgurl(imageUploadService.getNetServiceUrl() + jmap.get("url"));
				}
			}
		}
		if (flag || file == null) {
			dynamic.setCreatedate(new Date());
			resMap.put("code", ResultCode.SUCCESS);
			resMap.put("data", dynamicManage.insertBackId(dynamic));
			return resMap;
		} else {
			resMap.put("code", "0");
			return resMap;
		}
	}

}
