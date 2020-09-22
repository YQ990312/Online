package com.online.college.service.user.service;

import com.online.college.dao.user.UserFollowsDao;
import com.online.college.service.user.pojo.UserFollows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFollowsService {

    @Autowired
    private UserFollowsDao userFollowsDao;

    public UserFollows isFollows(Integer userId,Integer followId){
        return userFollowsDao.isFollows(userId,followId);
    }

    public void toFollows(UserFollows userFollows){
        userFollowsDao.toFollow(userFollows);
    }
}
