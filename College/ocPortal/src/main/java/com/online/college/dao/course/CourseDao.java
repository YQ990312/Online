package com.online.college.dao.course;

import com.online.college.common.page.TailPage;
import com.online.college.service.course.pojo.Course;
import com.online.college.service.course.pojo.CourseQueryDto;

import java.util.List;

public interface CourseDao {

    List<Course> queryList(CourseQueryDto dto);

    int queryCourseCount(Course course);

    List<Course> queryPage(Course course, TailPage<Course> tailPage);

    Course queryById(Integer courseId);

    List<Course> recommendCourse(String type);
}
