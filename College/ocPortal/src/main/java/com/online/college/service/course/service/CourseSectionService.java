package com.online.college.service.course.service;

import com.online.college.dao.course.CourseSectionDao;
import com.online.college.service.course.pojo.CourseSection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseSectionService {

    @Autowired
    private CourseSectionDao courseSectionDao;

    public List<CourseSection> queryAll(CourseSection courseSection){
        return courseSectionDao.queryAll(courseSection);
    }

    public CourseSection queryById(Integer cousreId){
        return courseSectionDao.getById(cousreId);
    }
}
