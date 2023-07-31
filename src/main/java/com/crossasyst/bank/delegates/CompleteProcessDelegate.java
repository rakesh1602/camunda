package com.crossasyst.bank.delegates;

import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class CompleteProcessDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        log.info("Inside the complete process delegate method.");

        Boolean isActiveStatus = (Boolean) execution.getVariable("accountStatus");

        if(isActiveStatus){
            log.info("Your account opening process is successfully completed.");
        } else{
            log.info("Your account is not confirmed yet, please complete the activation process.");
        }

        log.info("Complete process delegate method executed.");
    }
}
