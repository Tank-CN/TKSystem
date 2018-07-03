package com.tk.controller;

import com.tk.manage.NewsManage;
import com.tk.model.News;
import com.tk.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/view")
public class ViewController {


    @Autowired
    NewsManage newsManage;


    @RequestMapping(value = "newsdetail/{id}")
    public ModelAndView newsdetail(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("h5/newsdetail");
        News vo=newsManage.getById(id);
        modelAndView.addObject("vo", vo);
        modelAndView.addObject("time", DateUtils.dateFormate(vo.getCreatedate(),"yyyy-MM-dd"));
        return modelAndView;
    }


}
