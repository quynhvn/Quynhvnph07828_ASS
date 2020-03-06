package com.codelean.responsitory;

import com.codelean.model.Account;

public interface AccountResponsitory extends Responsitory<Account> {
    public Account findByUsernameAndPassword(String user, String pass);

}
