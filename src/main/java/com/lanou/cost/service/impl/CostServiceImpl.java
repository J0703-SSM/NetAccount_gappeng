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

    /**
     * 分页查询所有
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageBean<Cost> findAll(Integer pageNum, Integer pageSize) {
        int totalCount = costMapper.findCount();
        PageBean<Cost> pageBean = new PageBean(pageNum,pageSize,totalCount);
        List<Cost> costs = costMapper.findAll(pageBean);
        pageBean.setData(costs);
        return pageBean;
    }
    /**
     * 保存资费
     * @param cost
     * @return
     */
    public int save(Cost cost) {
        return costMapper.save(cost);
    }
    /**
     * 根据id找到资费
     * @param id
     * @return
     */
    public Cost findById(int id) {
        return costMapper.findById(id);
    }
    /**
     * 根据id修改资费
     * @param cost
     * @return
     */
    public int updateById(Cost cost) {
       return costMapper.updateById(cost);
    }
    /**
     * 根据id删除资费
     * @param id
     * @return
     */
    public int deleteById(int id) {
        return costMapper.deleteById(id);
    }

    /**
     * 启用资费
     * @param cost
     * @return
     */
    public int startCost(Cost cost) {
        return costMapper.startCost(cost);
    }

    /**
     * 根据基费或是时长进行排序
     * @param pageNum
     * @param pageSize
     * @param condition 条件
     * @param column order by
     * @return
     */
    public PageBean<Cost> findByOrder(Integer pageNum, int pageSize, String condition, String column) {
        int totalCount = costMapper.findCount();
        PageBean<Cost> pageBean = new PageBean<Cost>(pageNum,pageSize,totalCount,condition,column);
        List<Cost> costs = costMapper.findByOrder(pageBean);
        pageBean.setData(costs);
        return pageBean;
    }


}
