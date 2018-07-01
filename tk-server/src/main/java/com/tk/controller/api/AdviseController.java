package com.tk.controller.api;

import com.tk.manage.BasAdviseManage;
import com.tk.model.BasAppAdvise;
import com.tk.util.CommonUtils;
import com.tk.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 建议或者bug
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/api/auth/advise")
public class AdviseController extends ApiBaseController {

	@Autowired
	private BasAdviseManage adviseManage;

	/**
	 * 添加建议
	 * 
	 * @param advise
	 *            kinds 1：建议 2：bug
	 * @return
	 */
	@RequestMapping(value = "add")
	@ResponseBody
	public Map<String, Object> add(BasAppAdvise advise, HttpServletRequest request) {
		Long uid = getUid(request);
		Map<String, Object> map = new HashMap<String, Object>();
		if (CommonUtils.isNull(uid) || (CommonUtils.isNull(advise.getContent())) || (CommonUtils.isNull(advise.getKinds()))) {
			map.put("code", ResultCode.PARAMETERS_EMPTY);
			map.put("msg", "传入参数不能为空");
			return map;
		}
		advise.setUid(uid);
		adviseManage.insert(advise);
		map.put("code", ResultCode.SUCCESS);
		map.put("msg", "保存成功");
		return map;
	}
}
