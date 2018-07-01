package com.tk.controller.api;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/6/7.
 */
public class ApiBaseController {

    @InitBinder
    public void InitBinder(WebDataBinder dataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 获取UID，用户ID
     *
     * @param request
     * @return
     */
    public Long getUid(HttpServletRequest request) {
        Object uid = request.getAttribute("id");
        return null != uid ? Long.valueOf(uid.toString()) : null;
    }
}
