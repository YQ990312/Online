package com.online.college.service.consts.service;


import com.online.college.dao.consts.ConstsClassifyDao;
import com.online.college.dao.course.CourseDao;
import com.online.college.service.consts.pojo.ConstsClassify;
import com.online.college.service.consts.vo.ConstsClassifyVO;
import com.online.college.service.course.pojo.Course;
import com.online.college.service.course.pojo.CourseQueryDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class PortalBusiness {

    @Autowired
    @Qualifier("constsClassifyDao")
    private ConstsClassifyDao constsClassifyDao;

    @Autowired
    private CourseDao courseDao;

    public List<ConstsClassifyVO> queryAllClassify(){
        List<ConstsClassifyVO> resultList=new LinkedList<ConstsClassifyVO>();
        for(ConstsClassifyVO vo:getClassifyMap().values()){
            resultList.add(vo);
        }
        return resultList;
    }

    public Map<String,ConstsClassifyVO> getClassifyMap(){
        Map<String,ConstsClassifyVO> resultMap=new HashMap<String, ConstsClassifyVO>();
        Iterator<ConstsClassify> iterator=constsClassifyDao.queryAll().iterator();
        while(iterator.hasNext()){
            ConstsClassify classify= iterator.next();
            if("0".equals(classify.getParentCode())){
                //一级分类
                ConstsClassifyVO vo=new ConstsClassifyVO();
                BeanUtils.copyProperties(classify,vo);
                resultMap.put(vo.getCode(),vo);
            }else{
                //二级分类
                if(resultMap.get(classify.getParentCode())!=null){
                    resultMap.get(classify.getParentCode()).getSubClassifyList().add(classify);
                }
            }
        }
        return resultMap;
    }

    //查询课程推荐
    public void prepareRecomdCourses(List<ConstsClassifyVO> constsClassifyVOList){
        if(!CollectionUtils.isEmpty(constsClassifyVOList)){
            for(ConstsClassifyVO vo:constsClassifyVOList){
                CourseQueryDto dto=new CourseQueryDto();
                dto.setCount(6);
                dto.setClassify(vo.getCode());
                dto.descSortField("weight");

                List<Course> templeCourseList=courseDao.queryList(dto);
                if(!CollectionUtils.isEmpty(templeCourseList))
                    vo.setRecomdCourseList(templeCourseList);
            }
        }
    }
}
