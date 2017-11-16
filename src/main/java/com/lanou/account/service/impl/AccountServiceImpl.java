package com.lanou.account.service.impl;

import com.lanou.account.domain.Account;
import com.lanou.account.mapper.AccountMapper;
import com.lanou.account.service.AccountService;
import com.lanou.util.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/11/15.
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService{
    @Resource
    private AccountMapper accountMapper;

    public PageBean<Account> findAll(Integer pageNum, int pageSize) {
        int totalCount= accountMapper.findCount();
        PageBean<Account> pageBean = new PageBean<Account>(pageNum,pageSize,totalCount);
        List<Account> accounts = accountMapper.findAll(pageBean);
        pageBean.setData(accounts);
        return pageBean;
    }

    public int save(Account account) {
       return accountMapper.save(account);

    }

    public int deleteById(Integer id) {
        return accountMapper.deleteById(id);
    }

    public Account findById(Integer id) {
        return accountMapper.findById(id);
    }

    public Account findByIdCard(String id) {
        return accountMapper.findByIdCard(id);
    }

    public Account findByRecommenderId(Integer recommender_id) {
        return accountMapper. findByRecommenderId(recommender_id);
    }

    public int update(Account account) {
        return accountMapper.update(account);
    }
}
