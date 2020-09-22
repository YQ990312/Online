package com.online.college.dao.course;

import com.online.college.service.course.pojo.CourseSection;

import java.util.List;

public interface CourseSectionDao {
    CourseSection getById(Integer id);

    List<CourseSection> queryAll(CourseSection courseSection);

}
