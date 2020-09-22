package com.online.college.service.course.service;

import com.online.college.common.page.TailPage;
import com.online.college.dao.course.CourseDao;
import com.online.college.service.course.pojo.Course;
import com.online.college.service.course.pojo.CourseQueryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseDao courseDao;

    public List<Course> queryList(CourseQueryDto dto){
        return courseDao.queryList(dto);
    }

    public TailPage<Course> queryPage(Course course,TailPage<Course> tailPage){
        int itemSum=this.getCourseItemSum(course);
        System.err.println("查询数据的总数"+itemSum);
        tailPage.setItemSum(itemSum);
        List<Course> courseList=courseDao.queryPage(course,tailPage);
        tailPage.setCourseList(courseList);
        return tailPage;
    }

    public Integer getCourseItemSum(Course course){
        return courseDao.queryCourseCount(course);
    }

    public Course queryById(Integer courseId){
        return courseDao.queryById(courseId);
    }

    public List<Course> recommendCourse(String recommentType){
        return courseDao.recommendCourse(recommentType);
    }
}
