package com.crossasyst.camunda.delgates;

import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class DispatcherDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("Inside the dispatcher delegates.");

        String messageName = "sendLoanContract";
        String businessKey = "yourBusinessKey"; // Replace with the actual business key value

        // Set the business key on the process instance
        execution.setVariable("businessKey", businessKey);

        // Correlate the message
        execution.getProcessEngineServices().getRuntimeService().createMessageCorrelation(messageName)
                .processInstanceBusinessKey(businessKey)
                .setVariables(execution.getVariables())
                .correlate();
    }
}

