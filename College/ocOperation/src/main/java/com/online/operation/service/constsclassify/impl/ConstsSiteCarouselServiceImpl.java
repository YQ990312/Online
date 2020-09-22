package com.online.operation.service.constsclassify.impl;

import java.util.List;

import com.online.operation.common.page.TailPage1;
import com.online.operation.dao.ConstsSiteCarouselDao;
import com.online.operation.pojo.ConstsSiteCarousel;
import com.online.operation.service.constsclassify.IConstsSiteCarouselService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;



@Service
public class ConstsSiteCarouselServiceImpl implements IConstsSiteCarouselService {

	@Autowired
	private ConstsSiteCarouselDao entityDao;

	public ConstsSiteCarousel getById(Integer id){
		return entityDao.getById(id);
	}

	public ConstsSiteCarousel getById(Long id) {
		return null;
	}

	@Override
	public List<ConstsSiteCarousel> queryCarousels(Integer count){
		List<ConstsSiteCarousel> resultList = entityDao.queryCarousels(count);
		//处理为七牛图片链接
//		for(ConstsSiteCarousel item : resultList){
//			item.setPicture(QiniuStorage.getUrl(item.getPicture()));
//		}
		return resultList;
	}

	@Override
	public TailPage1<ConstsSiteCarousel> queryPage(ConstsSiteCarousel queryEntity , TailPage1<ConstsSiteCarousel> page){
		Integer itemsTotalCount = entityDao.getTotalItemsCount(queryEntity);
		List<ConstsSiteCarousel> items = entityDao.queryPage(queryEntity,page);
		if(CollectionUtils.isNotEmpty(items)){
//			for(ConstsSiteCarousel item : items){
//				item.setPicture(pictureUrl);
//			}
		}
		page.setItemsTotalCount(itemsTotalCount);
		page.setItems(items);
		return page;
	}

	@Override
	public void create(ConstsSiteCarousel entity){
		entityDao.create(entity);
	}
	
	/**
	 * 创建新记录
	 */
	public void createSelectivity(ConstsSiteCarousel entity){
		this.entityDao.createSelectivity(entity);
	}

	@Override
	public void update(ConstsSiteCarousel entity){
		entityDao.update(entity);
	}

	@Override
	public void updateSelectivity(ConstsSiteCarousel entity){
		entityDao.updateSelectivity(entity);
	}

	@Override
	public void delete(ConstsSiteCarousel entity){
		entityDao.delete(entity);
	}

	@Override
	public void deleteLogic(ConstsSiteCarousel entity){
		entityDao.deleteLogic(entity);
	}



}

