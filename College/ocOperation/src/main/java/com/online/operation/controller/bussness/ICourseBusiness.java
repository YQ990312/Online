package com.online.operation.controller.bussness;

import com.online.operation.controller.vo.CourseSectionVO;

import java.util.List;


public interface ICourseBusiness {

	/**
	 * 获取课程章节
	 */
	List<CourseSectionVO> queryCourseSection(Integer courseId);
	
}
