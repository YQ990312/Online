package com.online.operation.service.constsclassify.impl;

import java.util.List;

import com.online.operation.common.page.TailPage1;
import com.online.operation.dao.ConstsCollegeDao;
import com.online.operation.pojo.ConstsCollege;
import com.online.operation.service.constsclassify.IConstsCollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ConstsCollegeServiceImpl implements IConstsCollegeService {

	@Autowired
	private ConstsCollegeDao entityDao;

	/**
	 * 根据id获取
	 */
	public ConstsCollege getById(Integer id){
		return entityDao.getById(id);
	}

	public ConstsCollege getById(Long id) {
		return null;
	}

	/**
	 * 根据code获取
	 */
	public ConstsCollege getByCode(String code){
		return entityDao.getByCode(code);
	}

//	public TailPage1<ConstsCollege> queryPage(ConstsCollege queryEntity, TailPage1<ConstsCollege> page) {
//		return null;
//	}

	public List<ConstsCollege> queryAll(ConstsCollege queryEntity){
		return entityDao.queryAll(queryEntity);
	}

	public TailPage1<ConstsCollege> queryPage(ConstsCollege queryEntity , TailPage1<ConstsCollege> page){
		Integer itemsTotalCount = entityDao.getTotalItemsCount(queryEntity);
		List<ConstsCollege> items = entityDao.queryPage(queryEntity,page);
		page.setItemsTotalCount(itemsTotalCount);
		page.setItems(items);
		return page;
	}

	public void create(ConstsCollege entity){
		entityDao.create(entity);
	}
	
	/**
	 * 创建网校
	 */
	public void createSelectivity(ConstsCollege entity){
		this.entityDao.createSelectivity(entity);
	}

	public void update(ConstsCollege entity){
		entityDao.update(entity);
	}

	public void updateSelectivity(ConstsCollege entity){
		entityDao.updateSelectivity(entity);
	}

	public void delete(ConstsCollege entity){
		entityDao.delete(entity);
	}

	public void deleteLogic(ConstsCollege entity){
		entityDao.deleteLogic(entity);
	}



}

