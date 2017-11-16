package com.lanou.account.service;

import com.lanou.account.domain.Account;
import com.lanou.util.PageBean;

/**
 * Created by dllo on 17/11/15.
 */
public interface AccountService {

    PageBean<Account> findAll(Integer pageNum, int pageSize);

    int save(Account account);

    int deleteById(Integer id);

    Account findById(Integer account_id);

    Account findByIdCard(String id);

    Account findByRecommenderId(Integer recommender_id);

    int update(Account account);

}
