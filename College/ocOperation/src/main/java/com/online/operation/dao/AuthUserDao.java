package com.online.operation.dao;


import com.online.operation.common.page.TailPage;
import com.online.operation.common.page.TailPage1;
import com.online.operation.pojo.AuthUser;

import java.util.List;

public interface AuthUserDao {

	/**
	*根据id获取
	**/
	public AuthUser getById(Integer id);
	
	/**
	 * 根据username获取
	 */
	public AuthUser getByUsername(String username);

	/**
	 * 根据username 和 password获取
	 * @param authUser
	 * @return
	 */
	public AuthUser getByUsernameAndPassword(AuthUser authUser);
	
	/**
	*获取首页推荐5个讲师
	**/
	public List<AuthUser> queryRecomd();

	/**
	*获取总数量
	**/
	public Integer getTotalItemsCount(AuthUser queryEntity);

	/**
	*分页获取
	**/
	public List<AuthUser> queryPage(AuthUser queryEntity, TailPage1<AuthUser> page);

	/**
	*创建新记录
	**/
	public void createSelectivity(AuthUser entity);

	/**
	*根据id更新
	**/
	public void update(AuthUser entity);

	/**
	*根据id选择性更新自动
	**/
	public void updateSelectivity(AuthUser entity);

	/**
	*物理删除
	**/
	public void delete(AuthUser entity);

	/**
	*逻辑删除
	**/
	public void deleteLogic(AuthUser entity);



}

