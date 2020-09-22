package com.online.college.service.consts.service;

import com.online.college.dao.consts.ConstsSiteCarouselDao;
import com.online.college.service.consts.pojo.ConstsSiteCarousel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConstsSiteCarouselService {

    @Autowired()
    @Qualifier("constsSiteCarouselDao")
    private ConstsSiteCarouselDao constsSiteCarouselDao;

    public List<ConstsSiteCarousel> showCarousels(int count){
        List<ConstsSiteCarousel> result=constsSiteCarouselDao.showCarousels(count);
        return result;
    }
}
