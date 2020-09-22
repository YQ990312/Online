package com.online.operation.service.course.impl;

import java.util.List;

import com.online.operation.common.page.TailPage1;
import com.online.operation.dao.CourseSectionDao;
import com.online.operation.pojo.CourseSection;
import com.online.operation.service.course.ICourseSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CourseSectionServiceImpl implements ICourseSectionService {

	@Autowired
	private CourseSectionDao entityDao;

	public CourseSection getById(Integer id){
		return entityDao.getById(id);
	}

	public List<CourseSection> queryAll(CourseSection queryEntity){
		return entityDao.queryAll(queryEntity);
	}



	/**
	 * 获取课程章最大的sort
	 */
	@Override
	public Integer getMaxSort(Integer courseId){
		return entityDao.getMaxSort(courseId);
	}

	public TailPage1<CourseSection> queryPage(CourseSection queryEntity , TailPage1<CourseSection> page){
		Integer itemsTotalCount = entityDao.getTotalItemsCount(queryEntity);
		List<CourseSection> items = entityDao.queryPage(queryEntity,page);
		page.setItemsTotalCount(itemsTotalCount);
		page.setItems(items);
		return page;
	}

	public void createSelectivity(CourseSection entity){
		entityDao.createSelectivity(entity);
	}
	
	/**
	*批量创建
	**/
	public void createList(List<CourseSection> entityList){
		entityDao.createList(entityList);
	}

	public void update(CourseSection entity){
		entityDao.update(entity);
	}

	public void updateSelectivity(CourseSection entity){
		entityDao.updateSelectivity(entity);
	}

	public void delete(CourseSection entity){
		entityDao.delete(entity);
	}

	public void deleteLogic(CourseSection entity){
		entityDao.deleteLogic(entity);
	}

	/**
	 * 比当前sort大的，正序排序的第一个
	 * @param curCourseSection
	 * @return
	 */
	public CourseSection getSortSectionMax(CourseSection curCourseSection){
		return entityDao.getSortSectionMax(curCourseSection);
	}
	
	/**
	 * 比当前sort小的，倒序排序的第一个
	 * @param curCourseSection
	 * @return
	 */
	public CourseSection getSortSectionMin(CourseSection curCourseSection){
		return entityDao.getSortSectionMin(curCourseSection);
	}

}

