package com.lanou.account.mapper;

import com.lanou.account.domain.Account;
import com.lanou.util.PageBean;

import java.util.List;

/**
 * Created by dllo on 17/11/15.
 */
public interface AccountMapper {
    int findCount();

    List<Account> findAll(PageBean<Account> pageBean);

    int save(Account account);

    int deleteById(Integer id);

    Account findById(Integer id);

    Account findByIdCard(String id);

    Account findByRecommenderId(Integer recommender_id);

    int update(Account account);
}

