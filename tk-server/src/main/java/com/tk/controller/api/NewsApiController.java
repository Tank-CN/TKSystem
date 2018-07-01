package com.tk.controller.api;

import com.tk.manage.NewsManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/api/auth/news")
public class NewsApiController extends ApiBaseController {

    @Autowired
    NewsManage newsManage;


}
