package com.online.college.controller;

import com.online.college.service.auth.pojo.AuthUser;
import com.online.college.service.auth.service.AuthUserService;
import com.online.college.service.course.pojo.Course;
import com.online.college.service.course.service.CourseService;
import com.online.college.service.user.pojo.UserCollections;
import com.online.college.service.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthUserService authUserService;

    @Autowired
    private CourseService courseService;

    @RequestMapping("/info")
    public ModelAndView toMyInfo(HttpSession session){
        ModelAndView modelAndView=new ModelAndView("info");
        if(session.getAttribute("user")!=null){
            AuthUser authUser= userService.getUserInfo(session.getAttribute("user").toString());
            modelAndView.addObject("userInfo",authUser);
        }else{
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }

    @RequestMapping("/collection")
    public ModelAndView showCollection(HttpSession session){
        if(session.getAttribute("user")==null){
            return new ModelAndView("login");
        }
        ModelAndView modelAndView=new ModelAndView("collection");
        UserCollections userCollections=new UserCollections();
        userCollections.setUserId(authUserService.queryByUserName(session.getAttribute("user").toString()).getId());
        List<UserCollections> userCollectionsList=userService.queryAll(userCollections);
        List<Course> courseList=new ArrayList<Course>();
        for(UserCollections userCollections1:userCollectionsList){
            courseList.add(courseService.queryById(userCollections1.getClassify()));
        }
        modelAndView.addObject("collectionCourse",courseList);
        return modelAndView;
    }
}
