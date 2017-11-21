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

    public int deleteAccount(Account account) {
        return accountMapper.deleteAccount(account);
    }

    public int setStauts(Account account) {
        return accountMapper.setStauts(account);
    }

    public PageBean<Account> findAllAccount(Integer pageNum, int pageSize, Account account) {
        int totalCount = accountMapper.findAccountCount(account);
        PageBean<Account> pageBean = new PageBean<Account>(pageNum,pageSize,totalCount);
        pageBean.setT(account);
        List<Account> accounts = accountMapper.findAllAccount(pageBean);
        pageBean.setData(accounts);
        return pageBean;
    }
}
