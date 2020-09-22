package com.online.college.dao.auth;

import com.online.college.service.auth.pojo.AuthUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthUserDao {

    List<AuthUser> queryRecomd();

    AuthUser userLogin(@Param("userName") String name,@Param("userPassword")String pwd);

    AuthUser queryByUserName(@Param("userName") String name);

    Integer createUser(AuthUser authUser);

    void updateUserInfo(AuthUser authUser);
}
