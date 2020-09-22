package com.online.operation.service.auther;


import com.online.operation.common.page.TailPage1;
import com.online.operation.dao.AuthUserDao;
import com.online.operation.pojo.AuthUser;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuthUserServiceImpl{

	@Autowired
	private AuthUserDao entityDao;

	public void createSelectivity(AuthUser entity){
		entityDao.createSelectivity(entity);
	}
	
	/**
	*根据username获取
	**/
	public AuthUser getByUsername(String username){
		return entityDao.getByUsername(username);
	}
	
	
	
	public AuthUser getById(Integer id){
		return entityDao.getById(id);
	}
	
	/**
	*根据username和password获取
	**/
	public AuthUser getByUsernameAndPassword(AuthUser authUser){
		return entityDao.getByUsernameAndPassword(authUser);
	}

	/**
	*获取首页推荐5个讲师
	**/
	public List<AuthUser> queryRecomd(){
		List<AuthUser> recomdList = entityDao.queryRecomd();
		if(CollectionUtils.isNotEmpty(recomdList)){
			for(AuthUser item : recomdList){
//				if(StringUtils.isNotEmpty(item.getHeader())){
//					item.setHeader(QiniuStorage.getUrl(item.getHeader()));
//				}
			}
		}
		return recomdList;
	}

	public TailPage1<AuthUser> queryPage(AuthUser queryEntity , TailPage1<AuthUser> page){
		Integer itemsTotalCount = entityDao.getTotalItemsCount(queryEntity);
		List<AuthUser> items = entityDao.queryPage(queryEntity,page);
		page.setItemsTotalCount(itemsTotalCount);
		page.setItems(items);
		return page;
	}

	

	public void update(AuthUser entity){
		entityDao.update(entity);
	}

	public void updateSelectivity(AuthUser entity){
		entityDao.updateSelectivity(entity);
	}

	public void delete(AuthUser entity){
		entityDao.delete(entity);
	}

	public void deleteLogic(AuthUser entity){
		entityDao.deleteLogic(entity);
	}



}

