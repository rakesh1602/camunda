package com.crossasyst.camunda.asynchronous;

import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class PrintTheAge implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("Age is {} ", delegateExecution.getVariable("username"));
        log.info("asynchronous before or after will by apply at this point, " +
                "When there is a error process before or after this point will not execute." +
                "If asynchronous before or after is not applied even if there is a error process will be complete.");
    }
}
