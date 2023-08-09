package com.crossasyst.camunda.delgates;

import com.crossasyst.camunda.service.LoanContractService;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class GetLoanContractDelegates implements JavaDelegate {

    private final LoanContractService loanContractService;

    @Autowired
    public GetLoanContractDelegates(LoanContractService loanContractService) {
        this.loanContractService = loanContractService;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        log.info("Inside get loan contract delegates method.");
        Long loanContractId = (Long) execution.getVariable("loanContractId");

        loanContractService.getLoanContractPDF(loanContractId);
        log.info("Get Loan contract delegate method executed successfully.");
    }
}
