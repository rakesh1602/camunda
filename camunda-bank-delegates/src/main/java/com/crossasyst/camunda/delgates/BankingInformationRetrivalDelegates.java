package com.crossasyst.camunda.delgates;

import com.crossasyst.camunda.service.BankAccountService;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Log4j2
public class BankingInformationRetrivalDelegates implements JavaDelegate {

    private final BankAccountService bankAccountService;

    @Autowired
    public BankingInformationRetrivalDelegates(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        log.info("Inside banking information retrival delegates.");

        Long accountNumber = (Long) execution.getVariable("accountNumber");
        bankAccountService.getAccounts(accountNumber);

        log.info("Get Banking information delegates method executed.");
    }
}
