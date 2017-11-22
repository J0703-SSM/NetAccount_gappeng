package com.lanou.account.mapper;

import com.lanou.account.domain.Account;
import com.lanou.util.PageBean;

import java.util.List;

/**
 * Created by dllo on 17/11/15.
 */
public interface AccountMapper {
    /**
     * 查询条数
     * @return
     */
    int findCount();

    /**
     * 分页查询所有
     * @param pageBean
     * @return
     */
    List<Account> findAll(PageBean<Account> pageBean);

    /**
     * 保存
     * @param account
     * @return
     */
    int save(Account account);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    Account findById(Integer id);

    /**
     * 根据身份id查找
     * @param id
     * @return
     */
    Account findByIdCard(String id);

    /**
     * 根据推荐人id查询
     * @param recommender_id
     * @return
     */
    Account findByRecommenderId(Integer recommender_id);

    /**
     * 修改account
     * @param account
     * @return
     */
    int update(Account account);

    /**
     * 删除account
     * @param account
     * @return
     */
    int deleteAccount(Account account);

    /**
     * 修改状态
     * @param account
     * @return
     */
    int setStauts(Account account);

    /**
     * 找到条数
     * @param account
     * @return
     */
    int findAccountCount(Account account);

    List<Account> findAllAccount(PageBean<Account> pageBean);
}

