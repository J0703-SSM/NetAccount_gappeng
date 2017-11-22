package com.lanou.cost.service;

import com.lanou.util.PageBean;
import com.lanou.cost.domain.Cost;
import sun.jvm.hotspot.debugger.Page;

import java.util.List;

/**
 * Created by dllo on 17/11/13.
 */
public interface CostService {
    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageBean<Cost> findAll(Integer pageNum,Integer pageSize);

    /**
     * 保存资费
     * @param cost
     * @return
     */
    int save(Cost cost);


    /**
     * 根据id找到资费
     * @param id
     * @return
     */
    Cost findById(int id);

    /**
     * 根据id修改资费
     * @param cost
     * @return
     */
    int updateById(Cost cost);

    /**
     * 根据id删除资费
     * @param id
     * @return
     */
    int deleteById(int id);

    /**
     * 启用资费
     * @param cost
     * @return
     */

    int startCost(Cost cost);

    /**
     * 根据基费或是时长进行排序
     * @param pageNum
     * @param pageSize
     * @param condition 条件
     * @param column order by
     * @return
     */
    PageBean<Cost> findByOrder(Integer pageNum, int pageSize, String condition, String column);
}
