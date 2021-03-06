package com.online.operation.service.constsclassify;


import com.online.operation.common.page.TailPage1;
import com.online.operation.pojo.ConstsCollege;

import java.util.List;

public interface IConstsCollegeService {

	/**
	*根据id获取
	**/
	public ConstsCollege getById(Long id);
	
	/**
	 * 根据code获取
	 */
	public ConstsCollege getByCode(String code);

	/**
	*获取所有
	**/
	public List<ConstsCollege> queryAll(ConstsCollege queryEntity);

	/**
	*分页获取
	**/
	public TailPage1<ConstsCollege> queryPage(ConstsCollege queryEntity, TailPage1<ConstsCollege> page);
	
	/**
	*创建
	**/
	public void create(ConstsCollege entity);
	
	/**
	 * 创建网校
	 */
	public void createSelectivity(ConstsCollege entity);

	/**
	*根据id更新
	**/
	public void update(ConstsCollege entity);

	/**
	*根据id 进行可选性更新
	**/
	public void updateSelectivity(ConstsCollege entity);

	/**
	*物理删除
	**/
	public void delete(ConstsCollege entity);

	/**
	*逻辑删除
	**/
	public void deleteLogic(ConstsCollege entity);



}

