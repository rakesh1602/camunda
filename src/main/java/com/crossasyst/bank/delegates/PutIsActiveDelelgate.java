package com.crossasyst.bank.delegates;

import com.crossasyst.bank.service.AccountService;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class PutIsActiveDelelgate implements JavaDelegate {

    private final AccountService accountService;

    @Autowired
    public PutIsActiveDelelgate(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        log.info("Inside the put isActive delegate method");

        Boolean isActiveStatus = (Boolean) execution.getVariable("isActive");
        Long accountId = (Long) execution.getVariable("userResponse");
        log.info("Retrieving isActive status {}", isActiveStatus);

        accountService.processAccount(accountId, isActiveStatus);
        log.info("Account service process account method executed using java delegates.");

        execution.setVariable("accountStatus", isActiveStatus);
    }
}
