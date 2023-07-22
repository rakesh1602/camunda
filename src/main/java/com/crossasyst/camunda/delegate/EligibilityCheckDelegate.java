package com.crossasyst.camunda.delegate;

import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.value.IntegerValue;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class EligibilityCheckDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        // Perform eligibility check logic here.
        // You can access process variables using delegateExecution.getVariableTyped("variableName").
        // Simulate the check with a simple condition (e.g., age should be above 18).

        IntegerValue ageValue = delegateExecution.getVariableTyped("age");
        int age = (ageValue != null) ? ageValue.getValue() : 0; // Default to 0 if "age" variable is not set.

        int approvedAmount = 0;

        if (age >= 18) {
            log.info("Check if age is greater or equal to 18");
            approvedAmount = 5000; // Dummy approved amount for demonstration purposes.
            log.info("Amount approved: {}", approvedAmount);
            delegateExecution.setVariable("approvedAmount", approvedAmount);
        } else {
            log.info("Your approved amount is less than 5000");
        }
    }
}
