package com.online.operation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.operation.controller.bussness.ICourseSectionBusiness;
import com.online.operation.controller.vo.CourseSectionVO;
import com.online.operation.pojo.CourseSection;
import com.online.operation.service.course.ICourseSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("/courseSection")
public class CourseSectionController {


    @Autowired
    private ICourseSectionService entityService;

    @Autowired
    private ICourseSectionBusiness courseSectionBusiness;

    @RequestMapping(value = "/getById")
    @ResponseBody
    public String getById(Integer id){
        ObjectMapper objectMapper=new ObjectMapper();
        String returnData=null;
        try{
            returnData=objectMapper.writeValueAsString(entityService.getById(id));
        }catch(Exception e){
            e.printStackTrace();
            returnData="{\"code\":\"500\",\"message\":\"服务器错误\"}";
        }

        return returnData;
    }

    @RequestMapping(value = "/doMerge")
    @ResponseBody
    public String doMerge(CourseSection entity){
        entityService.updateSelectivity(entity);
        return "{\"code\":\"200\",\"message\":\"成功\"}";
    }

    //交换排序位置
    @RequestMapping(value = "/sortSection")
    @ResponseBody
    public String sortSection(CourseSection entity, Integer sortType){
        CourseSection curCourseSection = entityService.getById(entity.getId());
        if(null != curCourseSection){
            CourseSection tmpCourseSection = null;
            if(Integer.valueOf(1).equals(sortType)){//降序
                //比当前sort大的，正序排序的第一个
                tmpCourseSection = entityService.getSortSectionMax(curCourseSection);
            }else{
                //比当前sort小的，倒序排序的第一个
                tmpCourseSection = entityService.getSortSectionMin(curCourseSection);
            }

            if(null != tmpCourseSection){
                Integer tmpSort = curCourseSection.getSort();
                curCourseSection.setSort(tmpCourseSection.getSort());
                entityService.updateSelectivity(curCourseSection);

                tmpCourseSection.setSort(tmpSort);
                entityService.updateSelectivity(tmpCourseSection);
            }

        }
        return "{\"code\":\"200\",\"message\":\"成功\"}";
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(CourseSection entity){
        entityService.delete(entity);
        return "{\"code\":\"200\",\"message\":\"成功\"}";
    }

    @RequestMapping(value = "/deleteLogic")
    @ResponseBody
    public String deleteLogic(CourseSection entity){
        entityService.deleteLogic(entity);
        return "{\"code\":\"200\",\"message\":\"成功\"}";
    }

    //批量添加章节
    @RequestMapping(value = "/batchAdd")
    @ResponseBody
    public String batchAdd(@RequestBody List<CourseSectionVO> batchSections){
        for(CourseSectionVO vo:batchSections){
            System.out.println("章"+vo.getName());
            for(CourseSection courseSection:vo.getSections()){
                System.out.println("节"+courseSection.getName());
            }
        }
        courseSectionBusiness.batchAdd(batchSections);
        return "{\"code\":\"200\",\"message\":\"成功\"}";
    }

    /**
     * 导入excel
     */
    @RequestMapping("/doImport")
    @ResponseBody
    public String doImport(Long courseId ,@RequestParam(value="courseSectionExcel",required=true) MultipartFile excelFile){
        try {
            if (null != excelFile && excelFile.getBytes().length > 0) {
                InputStream is = excelFile.getInputStream();
                courseSectionBusiness.batchImport(courseId, is);//批量导入
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "{\"code\":\"200\",\"message\":\"成功\"}";
    }
}
