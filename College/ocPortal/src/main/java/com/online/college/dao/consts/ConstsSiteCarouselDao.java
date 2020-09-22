package com.online.college.dao.consts;

import com.online.college.service.consts.pojo.ConstsSiteCarousel;

import java.util.List;

public interface ConstsSiteCarouselDao {
    List<ConstsSiteCarousel> showCarousels(Integer count);
}
