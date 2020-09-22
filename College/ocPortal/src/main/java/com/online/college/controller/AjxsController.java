package com.online.college.controller;

import com.online.college.common.value.JsonEntity;
import com.online.college.service.auth.pojo.AuthUser;
import com.online.college.service.auth.service.AuthUserService;
import com.online.college.service.user.pojo.UserFollows;
import com.online.college.service.user.service.UserFollowsService;
import com.online.college.service.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class AjxsController {

    @Autowired
    private UserFollowsService followsService;

    @Autowired
    private AuthUserService authUserService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/follows",produces="application/json;charset=utf-8")
    public String userFollows(Integer followId, HttpSession session){
        System.out.println("关注   "+followId);
        UserFollows userFollows=new UserFollows();
        JsonEntity jsonEntity=new JsonEntity();
        String code=null; String message=null;
        if(session.getAttribute("user")!=null){
            Integer userId=authUserService.queryByUserName(session.getAttribute("user").toString()).getId();
            userFollows.setFollowId(followId);
            userFollows.setUserId(userId);
            userFollows.setCreateTime(new Date());
            userFollows.setCreateUser(authUserService.queryByUserName(session.getAttribute("user").toString()).getUsername());
            followsService.toFollows(userFollows);
            message="成功";
            code="200";

        }else{
            code="404";
            message="关注失败，需要登入";
        }
        return "{\"code\":\""+code+"\",\"message\":\""+message+"\"}";
    }


    @RequestMapping(value="/savainfo",produces="application/json;charset=utf-8")
    public String  savaInfo(AuthUser userInfo, @RequestParam("pictureImg") CommonsMultipartFile file){
        System.out.println("用户昵称"+userInfo.getUsername()+"用户账号"+userInfo.getNickname()+"真实名称"+ userInfo.getRealname());
        System.out.println("文件名"+file.getOriginalFilename());
        String fileName=file.getOriginalFilename();
        String code=null;
        String message=null;
        if(fileName==null){
           code="500";
           message="文件格式错误";
           return "{\"code\":\""+code+"\",\"message\":\""+message+"\"}";
        }

        System.out.println("上传的文件名"+fileName);

        String filePath="F:\\Images";
        File newFile=new File(filePath);

        if(!newFile.exists()){
            newFile.mkdir();
        }
        System.out.println("文件保存路径"+filePath);

        try{
            InputStream input=file.getInputStream();
            File newFileOutput=new File(filePath, fileName);
            OutputStream output = new FileOutputStream(newFileOutput);
            System.out.println("文件"+newFileOutput.getName()+"路径"+newFileOutput.getAbsolutePath());
            int length=0;
            byte[] buffer=new byte[2048];
            while((length=input.read(buffer))!=-1){
                output.write(buffer,0,length);
                output.flush();
            }
            input.close();
            output.close();
        }catch(Exception e){
            e.printStackTrace();
            code="500";
            message="文件流出问题";
            return "{\"code\":\""+code+"\",\"message\":\""+message+"\"}";
        }
        userInfo.setHeader("http:localhost:8080/images"+fileName);
        userService.updateUserInfo(userInfo);
        code="200";
        message="上传成功";
        return "{\"code\":\""+code+"\",\"message\":\""+message+"\"}";
    }
}
