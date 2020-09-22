package com.online.operation.service.constsSite;

import com.online.operation.common.page.TailPage;
import com.online.operation.dao.ConstsSiteCarouselDao;
import com.online.operation.pojo.ConstsSiteCarousel;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConstsSiteCarouselService {

    @Autowired
    private ConstsSiteCarouselDao entityDao;

    public ConstsSiteCarousel getById(Integer id){
        return entityDao.getById(id);
    }

    public List<ConstsSiteCarousel> queryCarousels(Integer count){
        List<ConstsSiteCarousel> resultList = entityDao.queryCarousels(count);
        //处理为七牛图片链接
//        for(ConstsSiteCarousel item : resultList){
//            item.setPicture(QiniuStorage.getUrl(item.getPicture()));
//        }
        return resultList;
    }

    public TailPage<ConstsSiteCarousel> queryPage(ConstsSiteCarousel queryEntity , TailPage<ConstsSiteCarousel> page){
        Integer itemsTotalCount = entityDao.getTotalItemsCount(queryEntity);
        page.setItemSum(itemsTotalCount);
        List<ConstsSiteCarousel> items = entityDao.queryPage(queryEntity,page);
        page.setCourseList(items);
        return page;
    }

    public void create(ConstsSiteCarousel entity){
        entityDao.create(entity);
    }

    /**
     * 创建新记录
     */
    public void createSelectivity(ConstsSiteCarousel entity){
        this.entityDao.createSelectivity(entity);
    }

    public void update(ConstsSiteCarousel entity){
        entityDao.update(entity);
    }

    public void updateSelectivity(ConstsSiteCarousel entity){
        entityDao.updateSelectivity(entity);
    }

    public void delete(ConstsSiteCarousel entity){
        entityDao.delete(entity);
    }

    public void deleteLogic(ConstsSiteCarousel entity){
        entityDao.deleteLogic(entity);
    }

}
