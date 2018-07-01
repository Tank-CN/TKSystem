package com.tk.controller.admin;


import com.tk.model.Admin;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminBaseController {

    @InitBinder
    public void InitBinder(WebDataBinder dataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    public boolean isSuperAdmin(Admin admin) {
        return admin.getId() == 1;
    }


    public Admin getAdmin(HttpServletRequest request) {
        return (Admin) request.getSession().getAttribute("admin");
    }



//
//    public boolean isOrgAdmin(Account account){
//        boolean flag = false;
//
//        return flag;
//    }
}
