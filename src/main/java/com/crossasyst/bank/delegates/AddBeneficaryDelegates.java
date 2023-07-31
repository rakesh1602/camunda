package com.crossasyst.bank.delegates;

import com.crossasyst.bank.model.BeneficaryAccount;
import com.crossasyst.bank.service.BeneficaryAccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class AddBeneficaryDelegates implements JavaDelegate {

    private final BeneficaryAccountService beneficaryAccountService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public AddBeneficaryDelegates(BeneficaryAccountService beneficaryAccountService) {
        this.beneficaryAccountService = beneficaryAccountService;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        log.info("Inside add beneficary account details delegate method.");
        log.info("Delegate values {} ", execution.getVariables());

        BeneficaryAccount beneficaryAccount = objectMapper.convertValue(execution.getVariables(), BeneficaryAccount.class);
        beneficaryAccountService.addBeneficary(beneficaryAccount);

        log.info("Add beneficary delegate methods executed.");
    }
}
