package com.online.operation.service.college;

import com.online.operation.common.page.TailPage;
import com.online.operation.dao.ConstsCollegeDao;
import com.online.operation.pojo.ConstsCollege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeService {


    @Autowired
    private ConstsCollegeDao entityDao;

    /**
     * 根据id获取
     */
    public ConstsCollege getById(Integer id){
        return entityDao.getById(id);
    }

    /**
     * 根据code获取
     */
    public ConstsCollege getByCode(String code){
        return entityDao.getByCode(code);
    }

    public List<ConstsCollege> queryAll(ConstsCollege queryEntity){
        return entityDao.queryAll(queryEntity);
    }

    public TailPage<ConstsCollege> queryPage(ConstsCollege queryEntity , TailPage<ConstsCollege> page){
        Integer itemsTotalCount = entityDao.getTotalItemsCount(queryEntity);
        page.setItemSum(itemsTotalCount);
        List<ConstsCollege> items = entityDao.queryPage(queryEntity,page);
        page.setCourseList(items);
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
