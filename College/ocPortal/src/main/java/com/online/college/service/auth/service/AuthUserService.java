package com.online.college.service.auth.service;

import com.online.college.dao.auth.AuthUserDao;
import com.online.college.service.auth.pojo.AuthUser;
import com.online.college.service.course.pojo.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;

@Service
public class AuthUserService {

    @Autowired
    private AuthUserDao authUserDao;

    @Autowired
    private AuthUser authUser;

    public AuthUser userLogin(String name, String pwd){
        AuthUser loginCode=authUserDao.userLogin(name,pwd);
        return loginCode;
    }

    public List<AuthUser> queryRecomd(){
        return authUserDao.queryRecomd();
    }

    public AuthUser queryByUserName(String name){
        return authUserDao.queryByUserName(name);
    }

    public Integer createUser(String username,String nickname,String password,String gender,
                              String mobile,String birthday,String education,String certNo,String collegeCode){

        authUser.setUsername(username);
        authUser.setNickname(nickname);
        authUser.setPassword(password);
        authUser.setGender(new Integer(gender));
        authUser.setMobile(mobile);
        birthday=birthday.replace("T"," ");
        authUser.setBirthday(birthday);

        authUser.setStatus(0);



        if(education.equals("1")){
            authUser.setEducation("大专");
        }else if(education.equals("2")){
            authUser.setEducation("本科");
        }else if(education.equals("3")){
            authUser.setEducation("硕士");
        }else if(education.equals("4")){
            authUser.setEducation("博士");
        }else {
            authUser.setEducation("博士后");
        }


        authUser.setCertNo(certNo);
        if(certNo.equals("0")){
            authUser.setWeight(0);
        }else if(certNo.equals("2")){
            authUser.setWeight(1);
        }else {
            authUser.setWeight(2);
        }

        authUser.setTitle("普通学者");
        authUser.setSign("这个没有留下东西");


        authUser.setCollegeCode(collegeCode);
        if(collegeCode.equals("1")){
            authUser.setCollegeName("东华理工大学");
        }else if(collegeCode.equals("2")){
            authUser.setCollegeName("华东交通大学");
        }else if(collegeCode.equals("3")){
            authUser.setCollegeName("南昌大学");
        }else if(collegeCode.equals("4")){
            authUser.setCollegeName("江西中医药大学");
        }else if(collegeCode.equals("5")){
            authUser.setCollegeName("江西师范法学");
        }else if(collegeCode.equals("6")){
            authUser.setCollegeName("江西科技学院");
        }else{
            authUser.setCollegeName("没有大学");
        }


        int numCode=0;

        try{
            authUserDao.createUser(authUser);

        }catch(Exception e){
            e.printStackTrace();
            numCode=1;
        }

        return numCode;
    }

}
