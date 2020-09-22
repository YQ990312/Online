package com.online.college.controller;

import com.online.college.service.auth.pojo.AuthUser;
import com.online.college.service.auth.service.AuthUserService;
import com.online.college.service.consts.pojo.ConstsSiteCarousel;
import com.online.college.service.consts.service.ConstsSiteCarouselService;
import com.online.college.service.consts.service.PortalBusiness;
import com.online.college.service.consts.vo.ConstsClassifyVO;
import com.online.college.service.course.CourseEnum;
import com.online.college.service.course.pojo.Course;
import com.online.college.service.course.pojo.CourseQueryDto;
import com.online.college.service.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping()
public class IndexController {

    @Autowired
    private ConstsSiteCarouselService constsSiteCarouselService;

    @Autowired
    private PortalBusiness portalBusiness;

    @Autowired
    private CourseService courseService;

    @Autowired
    @Qualifier("authUserService")
    private AuthUserService authUserService;

    @RequestMapping("/index")
    public ModelAndView index(ModelAndView modelAndView, HttpSession session){

        System.out.println("主页");
        modelAndView.setViewName("index");
        //加载个人信息
        if(session.getAttribute("user")!=null){
            System.out.println("登入成功");
            AuthUser authUser=authUserService.queryByUserName(session.getAttribute("user").toString());
            modelAndView.addObject("userSession",authUser);
        }
        //加载轮播
        List<ConstsSiteCarousel> constsSiteCarouselList=constsSiteCarouselService.showCarousels(4);
        modelAndView.addObject("constssitecarouse",constsSiteCarouselList);

        //加载课程一级分类(包括二级）
        List<ConstsClassifyVO> constsClassifyVOList=portalBusiness.queryAllClassify();

        //加载推荐课程
        portalBusiness.prepareRecomdCourses(constsClassifyVOList);
        //第一板块添加完毕
        modelAndView.addObject("courseTopList",constsClassifyVOList);

        //添加5门推荐实战
        CourseQueryDto courseQueryDto=new CourseQueryDto();
        courseQueryDto.descSortField("desc");
        courseQueryDto.setCount(5);
        courseQueryDto.ascSortField("weight");
        courseQueryDto.setFree(CourseEnum.FREE_NOT.value());
        List<Course> CourseNotFreeList=courseService.queryList(courseQueryDto);
        System.out.println("实战课"+CourseNotFreeList.size());
        modelAndView.addObject("notFreeCourseList",CourseNotFreeList);

        //添加5门免费的课程
        courseQueryDto.descSortField("desc");
        courseQueryDto.setCount(5);
        courseQueryDto.ascSortField("weight");
        courseQueryDto.setFree(CourseEnum.FREE.value());
        List<Course> CourseFreeList=courseService.queryList(courseQueryDto);
        System.out.println("免费"+CourseFreeList.size());
        modelAndView.addObject("freeCourseList",CourseFreeList);

        //获取7门java课程，根据权重（学习数量studyCount）进行排序
        courseQueryDto.setCount(7);
        courseQueryDto.setFree(null);//不分实战和免费类别
        courseQueryDto.setSubClassify("java");//java分类
        courseQueryDto.descSortField("study_count");//按照studyCount降序排列
        List<Course> javaCourseList = this.courseService.queryList(courseQueryDto);
        System.out.println("java课"+javaCourseList.size());
        modelAndView.addObject("javaCourseList", javaCourseList);

        //得到推荐的讲师
        List<AuthUser> authUserList=authUserService.queryRecomd();
        modelAndView.addObject("authUserList",authUserList);

        return modelAndView;
    }

    @RequestMapping("/toIndex")
    public ModelAndView toIndex(){
        return null;
    }
}
