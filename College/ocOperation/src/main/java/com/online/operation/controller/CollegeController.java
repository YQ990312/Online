package com.online.operation.controller;

import com.online.operation.common.page.TailPage;
import com.online.operation.pojo.ConstsClassify;
import com.online.operation.pojo.ConstsCollege;
import com.online.operation.service.college.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/college")
public class CollegeController {

    @Autowired
    private CollegeService entityService;

    @RequestMapping(value = "/queryPageList")
    public ModelAndView queryPage(){
        ModelAndView mv = new ModelAndView("collegePageList");
        ConstsCollege queryEntity=new ConstsCollege();
        TailPage<ConstsCollege> page=new TailPage<ConstsCollege>();

        if(!StringUtils.isEmpty(queryEntity.getName())){
            queryEntity.setName(queryEntity.getName().trim());
        }else{
            queryEntity.setName(null);
        }

        page = entityService.queryPage(queryEntity,page);

        mv.addObject("page",page);
        return mv;
    }

    @RequestMapping(value = "/deleteLogic")
    @ResponseBody
    public String deleteLogic(String collegeCode){
        String code=null;
        String message=null;
        ConstsCollege constsCollege=new ConstsCollege();
        constsCollege.setCode(collegeCode);
        entityService.deleteLogic(constsCollege);
        code="200";
        message="删除成功";
        return "{\"code\":\"" + code + "\",\"message\":\"" + message + "\"}";
    }

    @RequestMapping("/save")
    @ResponseBody
    public String toSave(String collegeName,String collegeCode){
        String code=null;
        String message=null;
        System.out.println("名称"+collegeName+"   编码"+collegeCode);
        if(collegeCode==null||collegeName==null){
            code="400";
            message="输入有错误";
            return "{\"code\":\"" + code + "\",\"message\":\"" + message + "\"}";
        }
        ConstsCollege constsCollege = new ConstsCollege();
        constsCollege.setName(collegeName);
        constsCollege.setCode(collegeCode);
        ConstsCollege tmpEntity = entityService.getByCode(constsCollege.getCode());
        if(tmpEntity==null){
            System.out.println("编码不存在");
            entityService.createSelectivity(constsCollege);
            code="200";
            message="保存成功";
            return "{\"code\":\"" + code + "\",\"message\":\"" + message + "\"}";
        }else{
            System.out.println("编码存在");
            ConstsCollege constsCollege1 = new ConstsCollege();
            constsCollege1.setName(collegeName);
            constsCollege1.setCode(collegeCode);
            entityService.updateSelectivity(constsCollege1);
            code = "200";
            message = "修改成功";
            return "{\"code\":\"" + code + "\",\"message\":\"" + message + "\"}";
        }
    }

}
