package com.crossasyst.camunda.delgates;

import com.crossasyst.camunda.model.LoanApplicationForm;
import com.crossasyst.camunda.service.LoanApplicationFormService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Log4j2
public class LoanApplicationFormDelegates implements JavaDelegate {

    private final LoanApplicationFormService loanApplicationFormService;

    private final SendMessageDelegates sendMessageDelegates;

    private ObjectMapper objectMapper =new ObjectMapper();

    @Autowired
    public LoanApplicationFormDelegates(LoanApplicationFormService loanApplicationFormService, SendMessageDelegates sendMessageDelegates) {
        this.loanApplicationFormService = loanApplicationFormService;
        this.sendMessageDelegates = sendMessageDelegates;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("Inside loan application form delegate method.");
        log.info("Delegates {} ", execution.getVariables());

        LoanApplicationForm loanApplicationForm = objectMapper.convertValue(execution.getVariables(), LoanApplicationForm.class);
        Long userId = (Long) execution.getVariable("userId");

        loanApplicationFormService.createLoanApplicationForm(userId, loanApplicationForm);
        log.info("Loan application form method executed successfully.");

        execution.setVariable("loanRequest", userId);

        String messageName = "NewLoanRequests";
        String businessKey = "yourBusinessKey";
        Map<String, Object> processVariables = execution.getVariables();

        sendMessageDelegates.sendMessageToStartEvent(messageName, businessKey, processVariables);
    }
}
