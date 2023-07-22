package com.anmv.service;

import com.anmv.createForm.CreatingAccountForm;
import com.anmv.entity.Account;

import java.util.List;

public interface IAccountService {
    List<Account> getAllAccounts(String search, String departmentName);

    void createNewAccount(CreatingAccountForm form);
}
