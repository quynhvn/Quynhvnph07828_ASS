package com.codelean.service;

import com.codelean.model.Account;

import java.util.List;

public interface AccountService {
    List<Account> findAll();
    void save(Account account);
    Account findByUsernameAndPassword(String user, String pass);


}
