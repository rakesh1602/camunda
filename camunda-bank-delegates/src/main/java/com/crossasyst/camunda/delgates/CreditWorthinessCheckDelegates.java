package com.crossasyst.camunda.delgates;

import com.crossasyst.camunda.service.CreditWorthinessService;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

@Component
@Log4j2
public class CreditWorthinessCheckDelegates implements JavaDelegate {

    private final CreditWorthinessService creditWorthinessService;

    private final SendMessageDelegates sendMessageDelegates;

    @Autowired
    public CreditWorthinessCheckDelegates(CreditWorthinessService creditWorthinessService, RuntimeService runtimeService, SendMessageDelegates sendMessageDelegates) {
        this.creditWorthinessService = creditWorthinessService;
        this.sendMessageDelegates = sendMessageDelegates;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("Inside credit worthiness check delegate.");

        Long accountNumber = (Long) execution.getVariable("accountNumber");

        Boolean isWorthy = creditWorthinessService.checkCreditWorthiness(accountNumber);
        execution.setVariable("isWorthy", isWorthy);

        if (!isWorthy) {
            log.warn("Account is not creditworthy. Sending notification to start event.");

            String messageName = "HighRiskNotApproved";
            String businessKey = "yourBusinessKey";
            Map<String, Object> processVariables = execution.getVariables();
            sendMessageDelegates.sendMessageToStartEvent(messageName, businessKey, processVariables);
            execution.setVariable("messageSent", true);
        }

        log.info("Credit worthiness delegate executed.");
    }
}

