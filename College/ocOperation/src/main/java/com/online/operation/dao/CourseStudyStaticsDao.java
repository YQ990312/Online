package com.online.operation.dao;

import com.online.operation.pojo.CourseStudyStaticsDto;

import java.util.List;

public interface CourseStudyStaticsDao {
	
	/**
	*统计课程学习情况
	**/
	public List<CourseStudyStaticsDto> queryCourseStudyStatistics(CourseStudyStaticsDto queryEntity);
	
}

