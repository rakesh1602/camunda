package com.crossasyst.camunda.delgates;

import com.crossasyst.camunda.service.LoanContractService;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Log4j2
public class LoanContractDelegates implements JavaDelegate {

    private final LoanContractService loanContractService;

    private final SendMessageDelegates sendMessageDelegates;

    @Autowired
    public LoanContractDelegates(LoanContractService loanContractService, SendMessageDelegates sendMessageDelegates) {
        this.loanContractService = loanContractService;
        this.sendMessageDelegates = sendMessageDelegates;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        log.info("Inside loan contract initialization.");

        Long accountNumber = (Long) execution.getVariable("accountNumber");
        Long amount = (Long) execution.getVariable("amount");

        Long loanContractId = loanContractService.generateLoanContract(accountNumber, amount);

        execution.setVariable("loanContractId", loanContractId);

        String messageName = "sendLoanContract";
        String businessKey = "yourBusinessKey";
        Map<String, Object> processVariables = execution.getVariables();

        sendMessageDelegates.sendMessageToStartEvent(messageName, businessKey, processVariables);
        log.info("Generate loan contract delegates method executed successfully.");
    }
}
