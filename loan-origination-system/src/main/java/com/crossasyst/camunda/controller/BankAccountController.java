package com.crossasyst.camunda.controller;

import com.crossasyst.camunda.bank.model.Account;
import com.crossasyst.camunda.exception.AccountNotFoundException;
import com.crossasyst.camunda.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }
    @GetMapping(value = "/accounts/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> getBankAccounts(@PathVariable Long accountNumber) {
        Account account = bankAccountService.getAccounts(accountNumber);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
}
