package com.online.college.service.course.service;

import com.online.college.service.course.CourseEnum;
import com.online.college.service.course.pojo.CourseSection;
import com.online.college.service.course.vo.CourseSectionVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseSectionBussess {


    @Autowired
    private CourseSectionService courseSectionService;

    public List<CourseSectionVo> queryCourseSectionVo(int courseId){

        List<CourseSectionVo> courseSectionVoList=new ArrayList<CourseSectionVo>();
        CourseSection courseSection=new CourseSection();
        courseSection.setCourseId(courseId);
        courseSection.setOnsale(CourseEnum.ONSALE.value());

        Map<Integer,CourseSectionVo> courseSectionVoMap=new HashMap<Integer,CourseSectionVo>();

        Iterator<CourseSection> iterator=courseSectionService.queryAll(courseSection).iterator();
        while(iterator.hasNext()){
            CourseSection courseSection1=iterator.next();
            if(courseSection1.getParentId()==0){
                //章
                CourseSectionVo vo=new CourseSectionVo();
                BeanUtils.copyProperties(courseSection1,vo);
                courseSectionVoMap.put(vo.getId(),vo);
            }else{
                //节
                courseSectionVoMap.get(courseSection1.getParentId()).getCourseSectionList().add(courseSection1);
            }
        }

        courseSectionVoList.addAll(courseSectionVoMap.values());

        return courseSectionVoList;
    }
}
