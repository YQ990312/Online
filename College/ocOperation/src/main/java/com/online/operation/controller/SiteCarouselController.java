package com.online.operation.controller;

import com.online.operation.common.page.TailPage;
import com.online.operation.pojo.ConstsSiteCarousel;
import com.online.operation.service.constsSite.ConstsSiteCarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

@Controller
@RequestMapping("/carousel")
public class SiteCarouselController {

    @Autowired
    private ConstsSiteCarouselService siteCarouselService;

    @RequestMapping(value = "/queryPage")
    public ModelAndView queryPage(){
        ConstsSiteCarousel queryEntity=new ConstsSiteCarousel() ;
        TailPage<ConstsSiteCarousel> page=new TailPage<ConstsSiteCarousel>();


        ModelAndView mv = new ModelAndView("pagelist");
        mv.addObject("curNav", "carousel");
        page.setPageSize(5);//每页5条数据
        page = siteCarouselService.queryPage(queryEntity,page);
        mv.addObject("page",page);
        mv.addObject("queryEntity",queryEntity);
        return mv;
    }

    @RequestMapping(value = "/toMerge")
    public ModelAndView toMerge(Integer id){
        ModelAndView mv = new ModelAndView("merge");
        mv.addObject("curNav", "carousel");

        ConstsSiteCarousel entity=new ConstsSiteCarousel();
        entity.setId(id);
        if(entity.getId() != null){
            entity = siteCarouselService.getById(entity.getId());
        }
        mv.addObject("entity",entity);
        return mv;
    }

    @RequestMapping(value = "/doMerge")
    public ModelAndView doMerge(ConstsSiteCarousel entity,@RequestParam MultipartFile pictureImg){
        String fileName=pictureImg.getOriginalFilename();
        if(fileName!=null&&fileName!=""){
            System.out.println("上传的文件名"+fileName);

            String filePath="F:\\Images";
            File newFile=new File(filePath);

            if(!newFile.exists()){
                newFile.mkdir();
            }

            System.out.println("文件保存路径"+filePath);

            try{
                InputStream input=pictureImg.getInputStream();
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
                return new ModelAndView("pagelist");
            }

            String networkLocation="http://localhost:8080/Images/"+fileName;
            System.out.println("图片的网络路径"+networkLocation);

            entity.setPicture(networkLocation);
        }

        if(entity.getId() == null){
            siteCarouselService.createSelectivity(entity);
        }else{
            siteCarouselService.updateSelectivity(entity);
        }
        return new ModelAndView("redirect:/carousel/queryPage.html");
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(ConstsSiteCarousel entity){
        String code=null;
        String message=null;
        if(entity.getId()==null){
            code="400";
            message="上传失败";
            return "{\"code\":\"" + code + "\",\"message\":\"" + message + "\"}";
        }
        siteCarouselService.delete(entity);
        code="200";
        message="删除失败";
        return "{\"code\":\"" + code + "\",\"message\":\"" + message + "\"}";
    }
}
