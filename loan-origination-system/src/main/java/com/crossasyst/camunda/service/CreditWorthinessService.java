package com.crossasyst.camunda.service;

import com.crossasyst.camunda.bank.entity.AccountEntity;
import com.crossasyst.camunda.bank.repository.AccountRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class CreditWorthinessService {

    private final AccountRepository accountRepository;

    @Autowired
    public CreditWorthinessService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public boolean checkCreditWorthiness(Long accountNumber) {
        log.info("Checking account worthiness of account id {} ", accountNumber);

        AccountEntity accountEntity = accountRepository.findById(accountNumber)
                .orElseThrow(() ->new RuntimeException("Account id not found"));

        boolean isWorthy = accountEntity.getCurrentBalance() >= 50000.0;

        if (isWorthy) {
            log.info("This account holder is a credit worthy.");
        } else {
            log.warn("Current balance is less than worthiness limit.");
        }
        return isWorthy;
    }

}
