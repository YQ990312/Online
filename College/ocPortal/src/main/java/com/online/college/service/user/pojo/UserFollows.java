package com.online.college.service.user.pojo;

import com.online.college.common.orm.BaseEntity;

public class UserFollows extends BaseEntity {
    private static final long serialVersionUID = -275116124638094439L;

    /**
     *用户id
     **/
    private Integer userId;

    /**
     *关注的用户id
     **/
    private Integer followId;

    public Integer getUserId(){
        return userId;
    }
    public void setUserId(Integer userId){
        this.userId = userId;
    }

    public Integer getFollowId(){
        return followId;
    }
    public void setFollowId(Integer followId){
        this.followId = followId;
    }

}
