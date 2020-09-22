package com.online.college.controller;

import com.online.college.service.auth.pojo.AuthUser;
import com.online.college.service.auth.service.AuthUserService;
import com.online.college.service.course.pojo.Course;
import com.online.college.service.course.pojo.CourseSection;
import com.online.college.service.course.service.CourseSectionBussess;
import com.online.college.service.course.service.CourseSectionService;
import com.online.college.service.course.service.CourseService;
import com.online.college.service.course.vo.CourseSectionVo;
import com.online.college.service.user.pojo.UserCourseSection;
import com.online.college.service.user.pojo.UserFollows;
import com.online.college.service.user.service.UserCourseSectionService;
import com.online.college.service.user.service.UserFollowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseSectionController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseSectionBussess courseSectionBussess;

    @Autowired
    private AuthUserService authUserService;

    @Autowired
    private UserCourseSectionService userCourseSectionService;

    @Autowired
    private CourseSectionService courseSectionService;

    @Autowired
    private UserFollowsService userFollowsService;

    @RequestMapping("/learn/{courseId}")
    public ModelAndView learn(@PathVariable Integer courseId, HttpSession session){
        System.out.println("查询课程的id"+courseId);
        if(courseId==null){
            return new ModelAndView("error/404");
        }
        //用户id
        Integer UserId=null;
        if(session.getAttribute("user")!=null){
            UserId=authUserService.queryByUserName(session.getAttribute("user").toString()).getId();
        }
        //加载的课程
        Course course=courseService.queryById(courseId);
        if(course==null) return new ModelAndView("error/404");

        ModelAndView modelAndView=new ModelAndView("learn");
        modelAndView.addObject("course",course);
        //加载章节
        List<CourseSectionVo> courseSectionVoList= courseSectionBussess.queryCourseSectionVo(courseId);
        modelAndView.addObject("courseSection",courseSectionVoList);
        //加载老师信息
        System.out.println("课程的老师信息"+course.getUsername());
        AuthUser authUser=authUserService.queryByUserName(course.getUsername());
        modelAndView.addObject("courseAuther",authUser);

        //添加是否关注
        if(session.getAttribute("user")!=null){
            UserFollows userFollows=userFollowsService.isFollows(UserId,authUser.getId());
            System.out.println("用户id"+UserId+"   关注的id"+authUser.getId());
            if(userFollows!=null){
                modelAndView.addObject("follow",userFollows);
            }
        }

        //加载推荐课程
        List<Course> courseList=courseService.recommendCourse(course.getSubClassify());
        modelAndView.addObject("courseRecommond",courseList);

        //添加上次学习的进度
        UserCourseSection userCourseSection=new UserCourseSection();
        if(session.getAttribute("user")!=null){

            System.out.println("用户的ID"+UserId);
            userCourseSection.setUserId(authUserService.queryByUserName(session.getAttribute("user").toString()).getId());
            userCourseSection.setCourseId(courseId);
            UserCourseSection userCourseSection1=userCourseSectionService.queryCourseSection(userCourseSection);
            if(userCourseSection1==null){
                //创建学习,从来没有学习过，添加第一次学习就行
                System.out.println("第一次学习"+userCourseSection1);
                userCourseSection1=new UserCourseSection();
                userCourseSection1.setUserId(UserId);
                userCourseSection1.setCourseId(courseId);
                userCourseSectionService.UpdateCourseSection(userCourseSection1);
                modelAndView.addObject("firstLean",courseSectionVoList.get(0).getCourseSectionList().get(0));
            }else{
                //学习过修改学习时间
                userCourseSection1.setUpdateTime(new Date());
                userCourseSectionService.UpdateNewTime(userCourseSection1);
                modelAndView.addObject("userCourseSection",userCourseSection1);
                UserCourseSection userCourseSection2=new UserCourseSection();
                userCourseSection2.setSectionId(userCourseSection1.getSectionId());
                for(CourseSectionVo vo:courseSectionVoList){
                    for(CourseSection section:vo.getCourseSectionList()){
                        if(section.getId()==userCourseSection1.getSectionId()){
                            System.out.println("找到了课程"+section.getName());
                            modelAndView.addObject("userCourseSectionName",section);
                        }
                    }
                }
            }

        }

        return modelAndView;
    }

    @RequestMapping("/viedo/{sectionId}")
    public ModelAndView learnViedo(@PathVariable Integer sectionId, HttpSession session){
        if(sectionId==null){
            return new ModelAndView("error/404");
        }
        //查找课程的章节
        CourseSection courseSection=courseSectionService.queryById(sectionId);
        if(courseSection==null){
            return new ModelAndView("error/404");
        }
        ModelAndView modelAndView=new ModelAndView("video");
        List<CourseSectionVo> courseSectionVoList= courseSectionBussess.queryCourseSectionVo(courseSection.getCourseId());
        modelAndView.addObject("courseSection",courseSection);
        modelAndView.addObject("courseSectionVoList",courseSectionVoList);

        //加载学习记录
        UserCourseSection userCourseSection=new UserCourseSection();
        if(session.getAttribute("user")!=null){
            Integer UserId=authUserService.queryByUserName(session.getAttribute("user").toString()).getId();
            System.out.println("用户的ID"+UserId);
            userCourseSection.setUserId(authUserService.queryByUserName(session.getAttribute("user").toString()).getId());
            userCourseSection.setCourseId(courseSection.getCourseId());
            UserCourseSection userCourseSection1=userCourseSectionService.queryCourseSection(userCourseSection);
            if(userCourseSection1==null){
                //没有学习
                userCourseSection1=new UserCourseSection();
                userCourseSection1.setUserId(UserId);
                userCourseSection1.setCourseId(courseSection.getCourseId());
                userCourseSection1.setSectionId(sectionId);
                userCourseSectionService.UpdateCourseSection(userCourseSection1);
            }else{
                userCourseSection1.setUpdateTime(new Date());
                userCourseSection1.setSectionId(sectionId);
                userCourseSectionService.UpdateNewTime(userCourseSection1);
            }
        }
        return modelAndView;
    }
}
