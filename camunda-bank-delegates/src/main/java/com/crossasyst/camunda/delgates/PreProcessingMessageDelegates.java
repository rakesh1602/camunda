package com.crossasyst.camunda.delgates;

import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Log4j2
public class PreProcessingMessageDelegates implements JavaDelegate {

    private final SendMessageDelegates sendMessageDelegates;

    @Autowired
    public PreProcessingMessageDelegates(SendMessageDelegates sendMessageDelegates) {
        this.sendMessageDelegates = sendMessageDelegates;
    }


    @Override
    public void execute(DelegateExecution execution) throws Exception {

        Boolean isApprovedInPreProcessing = (Boolean) execution.getVariable("isApproved");

        String messageName;
        String businessKey = "yourBusinessKey";
        if(isApprovedInPreProcessing) {

            messageName = "ApprovedInPreProcessing";

        } else {
            messageName = "NotApprovedInPreProcessing";

        }
        Map<String, Object> processVariables = execution.getVariables();
        sendMessageDelegates.sendMessageToStartEvent(messageName, businessKey, processVariables);

        log.info("PreProcessingMessage delegates executed.");
    }
}
