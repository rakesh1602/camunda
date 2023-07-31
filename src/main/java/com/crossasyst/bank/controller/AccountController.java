package com.crossasyst.bank.controller;

import com.crossasyst.bank.model.Account;
import com.crossasyst.bank.response.UserResponse;
import com.crossasyst.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = "/users/{userid}/accounts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> createAccount(@PathVariable Long userid, @RequestBody @Valid Account account){
        UserResponse userResponse = accountService.createAccount(account, userid);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PutMapping("/accounts/{accountId}/status")
    public ResponseEntity<Boolean> updateAccountStatus(@PathVariable Long accountId, @RequestBody @Valid boolean isActive) {
        boolean updatedStatus = accountService.processAccount(accountId, isActive);
        return ResponseEntity.ok(updatedStatus);
    }
}
