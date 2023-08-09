package com.crossasyst.camunda.service;

import com.crossasyst.camunda.bank.model.Account;
import com.crossasyst.camunda.bank.service.AccountService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class BankAccountService {

    private final AccountService accountService;

    @Autowired
    public BankAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public Account getAccounts(Long accountNumber) {
        log.info("Retrieving account details.");
        return accountService.getAccountDetails(accountNumber);
    }
}
