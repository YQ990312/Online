package com.online.operation.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class LoginController {

    @RequestMapping("login")
    public ModelAndView toLogin(HttpSession session){
        session.setAttribute("root","system");
        return new ModelAndView("/index");
    }
}
