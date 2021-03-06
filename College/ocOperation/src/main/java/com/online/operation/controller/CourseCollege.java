package com.online.operation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.operation.common.page.TailPage1;
import com.online.operation.common.value.JsonEntity;
import com.online.operation.controller.bussness.IPortalBusiness;
import com.online.operation.controller.vo.ConstsClassifyVO;
import com.online.operation.controller.vo.CourseSectionVO;
import com.online.operation.pojo.*;
import com.online.operation.service.auther.AuthUserServiceImpl;
import com.online.operation.service.constsclassify.IConstsClassifyService;
import com.online.operation.service.course.ICourseService;
import com.online.operation.service.sta.IStaticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/course")
public class CourseCollege {
    @Autowired
    private ICourseService courseService;

    @Autowired
    private IPortalBusiness portalBusiness;

    @Autowired
    private IConstsClassifyService constsClassifyService;

    @Autowired
    private AuthUserServiceImpl authUserService;

    @Autowired
    private IStaticsService staticsService;

    /**
     * 课程管理
     */
    @RequestMapping("/pagelist")
    public ModelAndView list(Course queryEntity, TailPage1<Course> page){
        ModelAndView mv = new ModelAndView("course/pagelist");

        if(!StringUtils.isEmpty(queryEntity.getName())){
            queryEntity.setName(queryEntity.getName().trim());
        }else{
            queryEntity.setName(null);
        }

        page.setPageSize(5);
        page = courseService.queryPage(queryEntity, page);
        mv.addObject("page", page);
        mv.addObject("queryEntity", queryEntity);
        mv.addObject("curNav", "course");
        return mv;
    }
    /**
     * 课程上下架
     */
    @RequestMapping("/doSale")
    @ResponseBody
    public String doSale(Course entity){
        courseService.updateSelectivity(entity);
        //更新课程总时长
        String code="200";
        String message="成功";

        return "{\"code\":\"" + code + "\",\"message\":\"" + message + "\"}";
    }

    /**
     * 课程删除
     */
    @RequestMapping("/doDelete")
    @ResponseBody
    public String doDelete(Course entity){
        courseService.delete(entity);
        String code="200";
        String message="成功";

        return "{\"code\":\"" + code + "\",\"message\":\"" + message + "\"}";
    }

    /**
     * 根据id获取
     */
    @RequestMapping("/getById")
    @ResponseBody
    public String getById(Integer id){
        String code="200";
        String message="成功";
        ObjectMapper objectMapper=new ObjectMapper();
        if(id==null){
            code="404";
            message="失败";
            return "{\"code\":\"" + code + "\",\"message\":\"" + message + "\"}";
        }
        JsonEntity jsonEntity=new JsonEntity();
        jsonEntity.setMessage("成功");
        jsonEntity.setCode("200");
        jsonEntity.setData(courseService.getById(id));
        String returnData=null;
        try{
            returnData=objectMapper.writeValueAsString(jsonEntity);
        }catch(Exception e){
            e.printStackTrace();
            returnData="{\"code\":\"" + code + "\",\"message\":\"" + message + "\"}";
        }

        return returnData;
    }

    /**
     * 课程详情
     */
    @RequestMapping("/read")
    public ModelAndView courseRead(Integer id){
        System.out.println("数据"+id);
        Course course = courseService.getById(id);
        String returnData=null;
        ObjectMapper objectMapper=new ObjectMapper();
        if(null == course){
            return new ModelAndView("error/404");
        }

        ModelAndView mv = new ModelAndView("course/read");
        mv.addObject("curNav", "course");
        mv.addObject("course", course);

        //课程章节
        List<CourseSectionVO> chaptSections = this.portalBusiness.queryCourseSection(id);
        mv.addObject("chaptSections", chaptSections);

        //课程分类
        Map<String, ConstsClassifyVO> classifyMap = portalBusiness.queryAllClassifyMap();
        //所有一级分类
        List<ConstsClassifyVO> classifysList = new ArrayList<ConstsClassifyVO>();
        for(ConstsClassifyVO vo : classifyMap.values()){
            classifysList.add(vo);
        }
        mv.addObject("classifys", classifysList);

        List<ConstsClassify> subClassifys = new ArrayList<ConstsClassify>();
        for(ConstsClassifyVO vo : classifyMap.values()){
            subClassifys.addAll(vo.getSubClassifyList());
        }
        mv.addObject("subClassifys", subClassifys);//所有二级分类

        //获取报表统计信息
        CourseStudyStaticsDto staticsDto = new CourseStudyStaticsDto();
        staticsDto.setCourseId(course.getId());
        staticsDto.setEndDate(new Date());
        staticsDto.setStartDate(new Date());

        StaticsVO staticsVo = staticsService.queryCourseStudyStatistics(staticsDto);
        if(null != staticsVo){
            try {
                returnData=objectMapper.writeValueAsString(staticsVo);
                mv.addObject("staticsVo", returnData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mv;
    }

    /**
     * 添加、修改课程
     */
    @RequestMapping("/doMerge")
    @ResponseBody
    public String doMerge(Course entity,@RequestParam MultipartFile pictureImg){
        String key = null;
        String code=null;
        String message=null;
        String returnData=null;
        ObjectMapper objectMapper=new ObjectMapper();
//        try {
//            if (null != pictureImg && pictureImg.getBytes().length > 0) {
//                key = QiniuStorage.uploadImage(pictureImg.getBytes());//七牛上传图片
//                entity.setPicture(key);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //判断教师
        if(!StringUtils.isEmpty(entity.getUsername())){
            AuthUser user = authUserService.getByUsername(entity.getUsername());
            if(null == user){
                code="404";
                message="老师的信息有误";
                return "{\"code\":\"" + code + "\",\"message\":\"" + message + "\"}";
            }
        }else{
            code="404";
            message="老师的信息有误";
            return "{\"code\":\"" + code + "\",\"message\":\"" + message + "\"}";
        }

        if(null != entity.getId()){
            courseService.updateSelectivity(entity);
        }else{
            //判断获取分类
            if(!StringUtils.isEmpty(entity.getClassify())){
                ConstsClassify classify = this.constsClassifyService.getByCode(entity.getClassify());
                if(null != classify){
                    entity.setClassifyName(classify.getName());
                }
            }
            if(!StringUtils.isEmpty(entity.getSubClassify())){
                ConstsClassify subClassify = this.constsClassifyService.getByCode(entity.getSubClassify());
                if(null != subClassify){
                    entity.setSubClassifyName(subClassify.getName());
                }
            }
            courseService.createSelectivity(entity);
            entity.setId(courseService.queryLast());
        }
        JsonEntity jsonEntity=new JsonEntity();
        jsonEntity.setCode("200");
        jsonEntity.setMessage("保存成功");
        jsonEntity.setData(entity);
        try{
            returnData=objectMapper.writeValueAsString(jsonEntity);
        }catch (Exception e){
            e.printStackTrace();
            return "{\"code\":\"404\",\"message\":\"服务器错误\"}";
        }
        System.out.println("返回的数据"+returnData);
        return returnData;
    }
//
//
    /**
     * 添加课程
     */
    @RequestMapping("/add")
    public ModelAndView add(){
        ModelAndView mv = new ModelAndView("course/add");
        mv.addObject("curNav", "course");
        Map<String,ConstsClassifyVO> classifyMap = portalBusiness.queryAllClassifyMap();
        //所有一级分类
        List<ConstsClassifyVO> classifysList = new ArrayList<ConstsClassifyVO>();
        for(ConstsClassifyVO vo : classifyMap.values()){
            classifysList.add(vo);
        }
        mv.addObject("classifys", classifysList);

        List<ConstsClassify> subClassifys = new ArrayList<ConstsClassify>();
        for(ConstsClassifyVO vo : classifyMap.values()){
            subClassifys.addAll(vo.getSubClassifyList());
        }
        mv.addObject("subClassifys", subClassifys);//所有二级分类
        return mv;
    }
//
    //继续添加章节
    @RequestMapping("/append")
    public ModelAndView appendSection(Integer courseId){
        Course course = courseService.getById(courseId);
        if(null == course)
            return new ModelAndView("error/404");

        ModelAndView mv = new ModelAndView("course/append");
        mv.addObject("curNav", "course");
        mv.addObject("course", course);

        List<CourseSectionVO> chaptSections = this.portalBusiness.queryCourseSection(courseId);
        mv.addObject("chaptSections", chaptSections);

        return mv;
    }
}
