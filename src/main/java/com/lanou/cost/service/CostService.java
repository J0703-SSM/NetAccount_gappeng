package com.lanou.cost.service;

import com.lanou.util.PageBean;
import com.lanou.cost.domain.Cost;
import sun.jvm.hotspot.debugger.Page;

import java.util.List;

/**
 * Created by dllo on 17/11/13.
 */
public interface CostService {
    PageBean<Cost> findAll(Integer pageNum,Integer pageSize);

    int save(Cost cost);

    Cost findById(int id);

    int updateById(Cost cost);

    int deleteById(int id);

    int startCost(Cost cost);


    PageBean<Cost> findByOrder(Integer pageNum, int pageSize, String condition, String column);
}
