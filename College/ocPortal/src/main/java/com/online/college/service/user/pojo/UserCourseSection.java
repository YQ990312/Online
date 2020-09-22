package com.online.college.service.user.pojo;

import com.online.college.common.orm.BaseEntity;

public class UserCourseSection extends BaseEntity {
    private static final long serialVersionUID = 5447461555053008202L;

    /**
     *用户id
     **/
    private Integer userId;

    /**
     *课程id
     **/
    private Integer courseId;

    /**
     *章节id
     **/
    private Integer sectionId;

    /**
     *状态学习结束：0-学习中；1-
     **/
    private Integer status;

    /**
     * 进度
     */
    private Integer rate;

    public Integer getUserId(){
        return userId;
    }
    public void setUserId(Integer userId){
        this.userId = userId;
    }

    public Integer getCourseId(){
        return courseId;
    }
    public void setCourseId(Integer courseId){
        this.courseId = courseId;
    }

    public Integer getSectionId(){
        return sectionId;
    }
    public void setSectionId(Integer sectionId){
        this.sectionId = sectionId;
    }

    public Integer getStatus(){
        return status;
    }
    public void setStatus(Integer status){
        this.status = status;
    }
    public Integer getRate() {
        return rate;
    }
    public void setRate(Integer rate) {
        this.rate = rate;
    }

}
