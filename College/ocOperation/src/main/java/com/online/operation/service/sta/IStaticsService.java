package com.online.operation.service.sta;


import com.online.operation.pojo.CourseStudyStaticsDto;
import com.online.operation.pojo.StaticsVO;

/**
 * 报表统计
 */
public interface IStaticsService {
	/**
	*统计课程学习情况
	**/
	public StaticsVO queryCourseStudyStatistics(CourseStudyStaticsDto queryEntity);
	
}
