package com.online.college.service.user.service;

import com.online.college.dao.user.UserCourseSectionDao;
import com.online.college.service.user.pojo.UserCourseSection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCourseSectionService {

    @Autowired
    private UserCourseSectionDao userCourseSectionDao;

    public UserCourseSection queryCourseSection(UserCourseSection userCourseSection){
        return userCourseSectionDao.queryCourseSection(userCourseSection);
    }

    public void UpdateCourseSection(UserCourseSection userCourseSection){
        userCourseSectionDao.updateCourseSection(userCourseSection);
    }

    public void UpdateNewTime(UserCourseSection userCourseSection){
        userCourseSectionDao.UpdateNewTime(userCourseSection);
    }


}
