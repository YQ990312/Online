package com.online.college.service.consts.service;

import com.online.college.dao.consts.ConstsClassifyDao;
import com.online.college.service.consts.pojo.ConstsClassify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConstsClassifyService {

    @Autowired
    private ConstsClassifyDao constsClassifyDao;


    public ConstsClassify getByCode(String code){

        return constsClassifyDao.getByCode(code);
    }
}
