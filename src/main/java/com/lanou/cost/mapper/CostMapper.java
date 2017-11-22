package com.lanou.cost.mapper;

import com.lanou.util.PageBean;
import com.lanou.cost.domain.Cost;

import java.util.List;

/**
 * Created by dllo on 17/11/13.
 */
public interface CostMapper {
    /**
     * 分页查询所有资费
     * @param pageBean
     * @return
     */
    List<Cost> findAll(PageBean<Cost> pageBean);

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
     * 资费总条数
     * @return
     */
    int findCount();

    /**
     * 根据基费或是时长进行排序
     * @param pageBean
     * @return
     */
    List<Cost> findByOrder(PageBean<Cost> pageBean);
}
