package com.online.operation.controller.bussness;

import com.online.operation.controller.vo.CourseSectionVO;

import java.io.InputStream;
import java.util.List;


public interface ICourseSectionBusiness {

	/**
	 * 批量添加
	 * @param courseSections
	 */
	void batchAdd(List<CourseSectionVO> courseSections);
	
	/**
	 * 批量导入
	 */
	void batchImport(Long courseId, InputStream is);
	
}
