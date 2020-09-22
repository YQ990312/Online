package com.online.college.service.consts.vo;


import com.online.college.service.consts.pojo.ConstsClassify;
import com.online.college.service.course.pojo.Course;

import java.util.LinkedList;
import java.util.List;

public class ConstsClassifyVO extends ConstsClassify {

    private List<ConstsClassify> subClassifyList=new LinkedList<ConstsClassify>();
    private List<Course> recomdCourseList;


    public List<ConstsClassify> getSubClassifyList() {
        return subClassifyList;
    }

    public void setSubClassifyList(List<ConstsClassify> subClassifyList) {
        this.subClassifyList = subClassifyList;
    }

    public List<Course> getRecomdCourseList() {
        return recomdCourseList;
    }

    public void setRecomdCourseList(List<Course> recomdCourseList) {
        this.recomdCourseList = recomdCourseList;
    }
}
