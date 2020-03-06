package com.codelean.service;

import com.codelean.model.Account;
import com.codelean.responsitory.AccountResponsitory;
import com.codelean.responsitory.AccountResponsitoryPaging;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountResponsitory accountResponsitory;

    @Autowired
    private AccountResponsitoryPaging accountResponsitoryPaging;

    @Override
    public List<Account> findAll() {
        return accountResponsitory.findAll();
    }

    @Override
    public void save(Account account) {
        accountResponsitoryPaging.save(account);

    }

    @Override
    public Account findByUsernameAndPassword(String user, String pass) {
        return accountResponsitory.findByUsernameAndPassword(user,pass);
    }

}
