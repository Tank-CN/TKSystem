package com.tk.controller.admin.bas;

import com.tk.manage.BasAdviseManage;
import com.tk.model.BasAppAdvise;
import com.tk.util.JsonResultDO;
import com.tk.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 意见反馈
 *
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/admin/advice")
public class BasAdviceController {

	@Autowired
	BasAdviseManage basAdviseManage;

	@RequestMapping()
	public String index() {
		return "admin/bas/advice";
	}

	@RequestMapping(value = "updateView")
	public ModelAndView updateView(Integer id, String currentpage) {
		ModelAndView modelAndView = new ModelAndView("admin/bas/advice_update");
		modelAndView.addObject("id", id);
		modelAndView.addObject("currentpage", currentpage);
		return modelAndView;
	}

	@RequestMapping(value = "list")
	@ResponseBody
	public JsonResultDO list(Date startDate, Date endDate, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "length", defaultValue = "20") Integer length, HttpServletRequest servletRequest) {
		JsonResultDO jsonResultDO = new JsonResultDO();
		List<BasAppAdvise> list = basAdviseManage.queryAdvices(startDate, endDate, page, length);
		jsonResultDO.setCode(ResultCode.SUCCESS);
		jsonResultDO.setData(list);
		jsonResultDO.setTotal(basAdviseManage.countAdvices(startDate, endDate));
		return jsonResultDO;
	}

	@RequestMapping(value = "detail/{id}", method = RequestMethod.POST)
	@ResponseBody
	public BasAppAdvise detail(@PathVariable("id") long id) {
		return basAdviseManage.getAdviceById(id);
	}
}
