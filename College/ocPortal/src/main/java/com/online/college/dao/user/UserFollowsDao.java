package com.online.college.dao.user;

import com.online.college.service.user.pojo.UserFollows;

public interface UserFollowsDao {

    UserFollows isFollows(Integer userId,Integer followId);

    void toFollow(UserFollows userFollows);
}
