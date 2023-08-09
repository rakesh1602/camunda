package com.crossasyst.camunda.delgates;

import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Log4j2
public class SendMessageDelegates {

    private final RuntimeService runtimeService;

    @Autowired
    public SendMessageDelegates(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    public void sendMessageToStartEvent(String messageName, String businessKey, Map<String, Object> processVariables) {
        runtimeService.createMessageCorrelation(messageName)
                .processInstanceBusinessKey(businessKey)
                .setVariables(processVariables)
                .correlateStartMessage();
    }
}
