package com.lanou.cost.service.impl;

import com.lanou.util.PageBean;
import com.lanou.cost.domain.Cost;
import com.lanou.cost.mapper.CostMapper;
import com.lanou.cost.service.CostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/11/13.
 */
@Service("costService")
public class CostServiceImpl implements CostService{

    @Resource
    private CostMapper costMapper;


    public PageBean<Cost> findAll(Integer pageNum, Integer pageSize) {
        int totalCount = costMapper.findCount();
        PageBean<Cost> pageBean = new PageBean(pageNum,pageSize,totalCount);
        List<Cost> costs = costMapper.findAll(pageBean);
        pageBean.setData(costs);
        return pageBean;
    }

    public int save(Cost cost) {
        return costMapper.save(cost);
    }

    public Cost findById(int id) {
        return costMapper.findById(id);
    }

    public int updateById(Cost cost) {
       return costMapper.updateById(cost);
    }

    public int deleteById(int id) {
        return costMapper.deleteById(id);
    }

    public int startCost(Cost cost) {
        return costMapper.startCost(cost);
    }

    public PageBean<Cost> findByOrder(Integer pageNum, int pageSize, String condition, String column) {
        int totalCount = costMapper.findCount();
        PageBean<Cost> pageBean = new PageBean<Cost>(pageNum,pageSize,totalCount,condition,column);
        List<Cost> costs = costMapper.findByOrder(pageBean);
        pageBean.setData(costs);
        return pageBean;
    }


}
