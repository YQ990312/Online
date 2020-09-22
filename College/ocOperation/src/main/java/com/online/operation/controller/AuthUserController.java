package com.online.operation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.operation.common.page.TailPage1;
import com.online.operation.common.value.JsonEntity;
import com.online.operation.pojo.AuthUser;
import com.online.operation.service.auther.AuthUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class AuthUserController {

    @Autowired
    private AuthUserServiceImpl service;

    @RequestMapping(value = "/userPageList")
    public ModelAndView queryPage(AuthUser queryEntity, TailPage1<AuthUser> page) {
        ModelAndView mv = new ModelAndView("userPageList");
        mv.addObject("curNav", "user");

        if (!StringUtils.isEmpty(queryEntity.getUsername())) {
            queryEntity.setUsername(queryEntity.getUsername().trim());
        } else {
            queryEntity.setUsername(null);
        }

        if (Integer.valueOf(-1).equals(queryEntity.getStatus())) {
            queryEntity.setStatus(null);
        }

        page = service.queryPage(queryEntity, page);
        mv.addObject("page", page);
        mv.addObject("queryEntity", queryEntity);

        return mv;
    }

    @RequestMapping(value = "/doMerge")
    @ResponseBody
    public String doMerge(AuthUser entity) {
        String code = null;
        String message = null;
        System.out.println("数据"+entity.getRealname());
        System.out.println("数据"+entity.getUsername());
        if (entity.getUsername() == null) {
            code = "400";
            message = "修改失败";
            return "{\"code\":\"" + code + "\",\"message\":\"" + message + "\"}";
        }
        entity.setUsername(null);//不更新
        entity.setRealname(null);//不更新
        service.updateSelectivity(entity);
        code = "200";
        message = "修改成功";
        return "{\"code\":\"" + code + "\",\"message\":\"" + message + "\"}";
    }

    @RequestMapping(value = "/getById")
    @ResponseBody
    public String getById(Integer id) {
        System.out.println("查询到id");
        ObjectMapper objectMapper = new ObjectMapper();
        String returnData = null;
        JsonEntity jsonEntity = new JsonEntity();
        AuthUser user = service.getById(id);
        jsonEntity.setCode("200");
        jsonEntity.setMessage("成功");
        jsonEntity.setData(user);
        try {
            returnData = objectMapper.writeValueAsString(jsonEntity);
        } catch (Exception e) {
            returnData = "{\"code\":\"500\",\"message\":\"服务器异常\"}";
        }
        System.out.println("数据" + returnData);
        return returnData;
    }
}
