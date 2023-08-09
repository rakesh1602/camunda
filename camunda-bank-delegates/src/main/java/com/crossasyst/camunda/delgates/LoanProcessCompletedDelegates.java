package com.crossasyst.camunda.delgates;

import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Log4j2
public class LoanProcessCompletedDelegates implements JavaDelegate {

    private final SendMessageDelegates sendMessageDelegates;

    @Autowired
    public LoanProcessCompletedDelegates(SendMessageDelegates sendMessageDelegates) {
        this.sendMessageDelegates = sendMessageDelegates;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String messageName = "loanProcessCompleted";
        String businessKey = "yourBusinessKey";
        Map<String, Object> processVariables = execution.getVariables();

        sendMessageDelegates.sendMessageToStartEvent(messageName, businessKey, processVariables);
        log.info("Loan process completed.");
    }
}
