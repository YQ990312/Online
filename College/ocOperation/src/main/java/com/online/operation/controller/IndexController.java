package com.online.operation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping()
public class IndexController {

    @RequestMapping("/index")
    public ModelAndView toIndex(HttpSession session){
        if(session.getAttribute("root")!=null)
            return new ModelAndView("index");
        else
            return new ModelAndView("login");
    }
}
