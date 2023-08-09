package com.crossasyst.camunda.bank.delegates;

import com.crossasyst.camunda.bank.model.TransferRequest;
import com.crossasyst.camunda.bank.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class TransferFundDelegates implements JavaDelegate {

    private final TransactionService transactionService;

    private ObjectMapper objectMapper=new ObjectMapper();

    @Autowired
    public TransferFundDelegates(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        log.info("Inside transfer fund delegates.");
        log.info("Delegates values {} ", execution.getVariables());

        TransferRequest transferRequest = objectMapper.convertValue(execution.getVariables(), TransferRequest.class);
        transactionService.transferFunds(transferRequest);

        boolean transactionStatus = transactionService.transferFunds(transferRequest);

        execution.setVariable("status", transactionStatus);

        log.info("Transaction transfer delegate method executed successfully.");
    }
}
