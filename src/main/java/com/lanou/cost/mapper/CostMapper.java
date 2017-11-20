package com.lanou.cost.mapper;

import com.lanou.util.PageBean;
import com.lanou.cost.domain.Cost;

import java.util.List;

/**
 * Created by dllo on 17/11/13.
 */
public interface CostMapper {
    List<Cost> findAll(PageBean<Cost> pageBean);

    int save(Cost cost);

    Cost findById(int id);

    int updateById(Cost cost);

    int deleteById(int id);

    int startCost(Cost cost);

    int findCount();


    List<Cost> findByOrder(PageBean<Cost> pageBean);
}
