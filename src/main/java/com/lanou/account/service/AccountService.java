package com.lanou.account.service;

import com.lanou.account.domain.Account;
import com.lanou.util.PageBean;

/**
 * Created by dllo on 17/11/15.
 */
public interface AccountService {

    PageBean<Account> findAll(Integer pageNum, int pageSize);

    int save(Account account);

    Account findById(Integer account_id);

    Account findByIdCard(String id);

    Account findByRecommenderId(Integer recommender_id);

    int update(Account account);

    int deleteAccount(Account account);

    int setStauts(Account account);

    PageBean<Account> findAllAccount(Integer pageNum, int pageSize, Account account);
}
