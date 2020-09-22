package com.online.college.service.course.vo;

import com.online.college.service.course.pojo.Course;
import com.online.college.service.course.pojo.CourseSection;

import java.util.ArrayList;
import java.util.List;

public class CourseSectionVo extends CourseSection {
    private List<CourseSection> courseSectionList=new ArrayList<CourseSection>();

    public List<CourseSection> getCourseSectionList() {
        return courseSectionList;
    }

    public void setCourseSectionList(List<CourseSection> courseSectionList) {
        this.courseSectionList = courseSectionList;
    }
}
