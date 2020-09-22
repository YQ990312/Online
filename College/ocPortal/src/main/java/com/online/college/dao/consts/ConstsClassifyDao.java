package com.online.college.dao.consts;

import com.online.college.service.consts.pojo.ConstsClassify;

import java.util.List;

public interface ConstsClassifyDao {

    List<ConstsClassify> queryAll();

    ConstsClassify getByCode(String Code);

}
