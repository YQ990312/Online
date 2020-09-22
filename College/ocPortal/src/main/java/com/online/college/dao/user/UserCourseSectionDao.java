package com.online.college.dao.user;

import com.online.college.service.user.pojo.UserCourseSection;

public interface UserCourseSectionDao {

    UserCourseSection queryCourseSection(UserCourseSection userCourseSection);

    void updateCourseSection(UserCourseSection userCourseSection);

    void UpdateNewTime(UserCourseSection userCourseSection);
}
