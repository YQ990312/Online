package com.online.operation.service.course;

import com.online.operation.common.page.TailPage1;
import com.online.operation.pojo.CourseSection;

import java.util.List;


public interface ICourseSectionService {

	/**
	*根据id获取
	**/
	public CourseSection getById(Integer id);

	/**
	*获取所有
	**/
	public List<CourseSection> queryAll(CourseSection queryEntity);
	
	/**
	 * 获取课程章最大的sort
	 */
	public Integer getMaxSort(Integer courseId);
	
	/**
	*分页获取
	**/
	public TailPage1<CourseSection> queryPage(CourseSection queryEntity, TailPage1<CourseSection> page);

	/**
	*创建
	**/
	public void createSelectivity(CourseSection entity);
	
	/**
	*批量创建
	**/
	public void createList(List<CourseSection> entityList);

	/**
	*根据id更新
	**/
	public void update(CourseSection entity);

	/**
	*根据id 进行可选性更新
	**/
	public void updateSelectivity(CourseSection entity);

	/**
	*物理删除
	**/
	public void delete(CourseSection entity);

	/**
	*逻辑删除
	**/
	public void deleteLogic(CourseSection entity);

	/**
	 * 比当前sort大的，正序排序的第一个
	 * @param curCourseSection
	 * @return
	 */
	public CourseSection getSortSectionMax(CourseSection curCourseSection);
	
	/**
	 * 比当前sort小的，倒序排序的第一个
	 * @param curCourseSection
	 * @return
	 */
	public CourseSection getSortSectionMin(CourseSection curCourseSection);
}

