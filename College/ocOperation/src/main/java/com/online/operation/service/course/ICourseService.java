package com.online.operation.service.course;

import com.online.operation.common.page.TailPage1;
import com.online.operation.pojo.Course;
import com.online.operation.pojo.CourseQueryDto;

import java.util.List;


/**
 * 课程服务层
 */
public interface ICourseService {

	/**
	 * 获取id
	 */
	Integer queryLast();
	/**
	*根据id获取
	**/
	public Course getById(Integer id);

	/**
	*获取所有
	**/
	public List<Course> queryList(CourseQueryDto queryEntity);

	/**
	*分页获取
	**/
	public TailPage1<Course> queryPage(Course queryEntity, TailPage1<Course> page);

	/**
	*创建
	**/
	public void createSelectivity(Course entity);

	/**
	*根据id 进行可选性更新
	**/
	public void updateSelectivity(Course entity);

	/**
	*物理删除
	**/
	public void delete(Course entity);

	/**
	*逻辑删除
	**/
	public void deleteLogic(Course entity);
	
}

