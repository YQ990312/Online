package com.online.college.controller;

import com.online.college.service.auth.pojo.AuthUser;
import com.online.college.service.auth.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    @Qualifier("authUserService")
    private AuthUserService authUserService;

    /*
    去登入
     */
    @RequestMapping("/login")
    public ModelAndView toLogin(ModelAndView modelAndView){
        modelAndView.setViewName("login");
        return modelAndView;
    }

    /*
       执行登入 axjx登入
        */
    @RequestMapping(value="/dologin")
    public String doLogin(@RequestParam("userName") String name, @RequestParam("userPassword") String pwd, HttpSession session) {
        System.out.println("用户登入"+name+"密码"+pwd);
        ModelAndView modelAndView=new ModelAndView();
        if(name.length()>11&&name.indexOf('a')>=0)  {
           modelAndView.setViewName("login");
        }
        AuthUser authUser=authUserService.userLogin(name,pwd);
        if(authUser==null) {
            modelAndView.setViewName("login");
        }
        session.setAttribute("user",authUser.getUsername());
        System.out.println("登入信息"+session.getAttribute("user"));

        modelAndView.setViewName("index");

        return "redirect:/index.html";
    }

    /*
    去注册
     */
    @RequestMapping("/register")
    public ModelAndView toRegister(ModelAndView view){
        view.setViewName("register");
        return view;
    }

    /**
     * 实现注册
     */

    @RequestMapping("/doRegister")
    public ModelAndView doRegister(ModelAndView modelAndView,
                                   String username,String mobile,
                                   String nickname,String password,
                                   String gender,String birthday,
                                   String education,String collegeCode,
                                   String certNo){
        System.out.println("登入用名"+username+
                "昵称"+nickname+
                "密码"+password+
                "性别"+gender+
                "手机号码"+mobile+
                "生日"+birthday+
                "学历"+education+
                "注册类型"+certNo+
                "大学编号"+collegeCode);
        int createNumber=authUserService.createUser(username,nickname,password,gender,mobile,birthday,education,certNo,collegeCode);
        if(createNumber==1){
            modelAndView.addObject("codo","添加失败");
        }
        modelAndView.setViewName("login");
        return modelAndView;
    }

}
