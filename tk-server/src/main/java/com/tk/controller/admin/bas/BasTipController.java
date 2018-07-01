package com.tk.controller.admin.bas;

import com.tk.controller.admin.AdminBaseController;
import com.tk.manage.BasTipManage;
import com.tk.util.CommonUtils;
import com.tk.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 举报信息
 *
 * @author Administrator
 */

@Controller
@RequestMapping(value = "/admin/bas")
public class BasTipController extends AdminBaseController {

    @Autowired
    BasTipManage basTipManage;




    @RequestMapping(value = "tip")
    public ModelAndView basOrganization(String currentpage) {
        ModelAndView modelAndView = new ModelAndView("admin/bas/tip");
        modelAndView.addObject("currentpage", CommonUtils.isNull(currentpage) ? "1" : currentpage);
        return modelAndView;
    }

    /**
     * 举报列表
     *
     * @param page
     * @param length
     * @return
     */
    @RequestMapping(value = "tip/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(Byte status, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "length", defaultValue = "20") Integer length, HttpServletRequest request) {

        Map<String, Object> regMsg = new HashMap<String, Object>();
        regMsg.put("data", basTipManage.list(status, page, length));
        regMsg.put("total", basTipManage.count(status));
        regMsg.put("code", ResultCode.SUCCESS);
        return regMsg;
    }


    @RequestMapping(value = "tip/statue", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> statue(Long id, HttpServletRequest request) {
        Map<String, Object> regMsg = new HashMap<String, Object>();
        regMsg.put("code", basTipManage.statue(id)?ResultCode.SUCCESS:ResultCode.ERROR);
        return regMsg;
    }

}
