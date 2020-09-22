package com.online.college.service.user.service;

import com.online.college.dao.auth.AuthUserDao;
import com.online.college.dao.user.UserCollectionsDao;
import com.online.college.service.auth.pojo.AuthUser;
import com.online.college.service.user.pojo.UserCollections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private AuthUserDao authUserDao;

    @Autowired
    private UserCollectionsDao userCollectionsDao;

    public AuthUser getUserInfo(String userID){
        return authUserDao.queryByUserName(userID);
    }

    public void updateUserInfo(AuthUser authUser){
        authUserDao.updateUserInfo(authUser);
    }

    public List<UserCollections> queryAll(UserCollections queryEntity){
        return userCollectionsDao.queryAll(queryEntity);
    }
}
