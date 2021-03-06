package com.online.operation.controller.bussness.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.online.operation.controller.bussness.ICourseBusiness;
import com.online.operation.controller.vo.CourseSectionVO;
import com.online.operation.pojo.CourseSection;
import com.online.operation.service.constsclassify.CourseEnum;
import com.online.operation.service.course.ICourseSectionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * 课程业务层
 */
@Service
public class CourseBusinessImpl implements ICourseBusiness {
	
	@Autowired
	private ICourseSectionService courseSectionService;
	
	/**
	 * 获取课程章节
	 */
	@Override
	public List<CourseSectionVO> queryCourseSection(Integer courseId){
		List<CourseSectionVO> resultList = new ArrayList<CourseSectionVO>();
		CourseSection queryEntity = new CourseSection();
		queryEntity.setCourseId(courseId);
		queryEntity.setOnsale(CourseEnum.ONSALE.value());//上架
		
		Map<Integer,CourseSectionVO> tmpMap = new LinkedHashMap<Integer,CourseSectionVO>();
		Iterator<CourseSection> it = courseSectionService.queryAll(queryEntity).iterator();
		while(it.hasNext()){
			CourseSection item = it.next();
			if(Long.valueOf(0).equals(item.getParentId())){//章
				CourseSectionVO vo = new CourseSectionVO();
				BeanUtils.copyProperties(item, vo);
				tmpMap.put(vo.getId(), vo);
			}else{
				tmpMap.get(item.getParentId()).getSections().add(item);//小节添加到大章中
			}
		}
		for(CourseSectionVO vo : tmpMap.values()){
			resultList.add(vo);
		}
		return resultList;
	}
}
