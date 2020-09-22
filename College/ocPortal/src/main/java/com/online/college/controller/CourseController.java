package com.online.college.controller;

import com.online.college.common.page.TailPage;
import com.online.college.service.consts.pojo.ConstsClassify;
import com.online.college.service.consts.service.ConstsClassifyService;
import com.online.college.service.consts.service.PortalBusiness;
import com.online.college.service.consts.vo.ConstsClassifyVO;
import com.online.college.service.course.pojo.Course;
import com.online.college.service.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private PortalBusiness portalBusiness;

    @Autowired
    private ConstsClassifyService classifyService;

    @Autowired
    private CourseService courseService;

    @RequestMapping("/tolist")
    public ModelAndView getList(@Nullable  String c,@Nullable Integer pageNum,@Nullable String pageSort){
        System.out.println("c  "+c+"pageSort   "+pageSort+"pageNum   "+pageNum);
        if(pageNum==null) pageNum=1;
        ModelAndView modelAndView=new ModelAndView("list");
        String curCode="-1";   //当前方向code  -1表示全部
        String curSubCode="-2"; //当前分类code -2表示全部
        //加载所有的课程
        Map<String, ConstsClassifyVO> classifyVOMap=portalBusiness.getClassifyMap();
        List<ConstsClassify> classifyList=new ArrayList<ConstsClassify>();
        //得到一级分类
        for(ConstsClassifyVO vo:classifyVOMap.values()){
            classifyList.add(vo);
        }
        modelAndView.addObject("classify",classifyList);

        ConstsClassify classify=classifyService.getByCode(c);

        if(classify==null){   //为空，加载所有的二级分类

            List<ConstsClassify> constsClassifyList=new ArrayList<ConstsClassify>();
            for(ConstsClassifyVO vo:classifyVOMap.values()){
                constsClassifyList.addAll(vo.getSubClassifyList());
            }
            modelAndView.addObject("classifgSub",constsClassifyList);
        }else{
            System.out.println("查询到的数据    "+classify.getName());
            System.out.println("数据的parentCode   "+classify.getParentCode());
            System.out.println("数据的code     "+classify.getCode());
            if(classify.getParentCode().equals("0")){
                curCode=classify.getCode();
                modelAndView.addObject("classifgSub",classifyVOMap.get(classify.getCode()).getSubClassifyList());
            }else{
                curCode=classify.getParentCode();
                curSubCode=classify.getCode();
                modelAndView.addObject("classifgSub",classifyVOMap.get(classify.getParentCode()).getSubClassifyList());
            }
        }
        modelAndView.addObject("curCode",curCode);
        modelAndView.addObject("curSubCode",curSubCode);
        System.out.println("返回的数据"+curCode+"   "+curSubCode);


        //添加分页的数据
        Course courseTailPage=new Course();
        TailPage<Course> tailPage=new TailPage<Course>();

        if(!"-1".equals(curCode)) courseTailPage.setClassify(curCode);
        if(!"-2".equals(curSubCode)) courseTailPage.setSubClassify(curSubCode);
                    //排序参数
        if("pop".equals(pageSort)){//最热
            tailPage.setSortField("study_count");
        }else{
            pageSort = "last";
            tailPage.setSortField("id");
        }
        modelAndView.addObject("sort", pageSort);

        tailPage.setPageNum(pageNum);
        tailPage=courseService.queryPage(courseTailPage,tailPage);
        modelAndView.addObject("tailcourse",tailPage);
        return modelAndView;
    }
}
