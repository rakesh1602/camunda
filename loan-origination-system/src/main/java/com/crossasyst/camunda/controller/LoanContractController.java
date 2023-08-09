package com.crossasyst.camunda.controller;

import com.crossasyst.camunda.model.LoanContract;
import com.crossasyst.camunda.service.LoanContractService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanContractController {

    private final LoanContractService loanContractService;

    @Autowired
    public LoanContractController(LoanContractService loanContractService) {
        this.loanContractService = loanContractService;
    }

    @PostMapping(value = "/account/{accountId}/contracts/loan-contracts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> createLoanContractPDF(@PathVariable Long accountId, @RequestBody Long loanAmount) throws DocumentException {
        Long loanContractId = loanContractService.generateLoanContract(accountId, loanAmount);
        byte[] pdfBytes = loanContractService.getLoanContractPDF(loanContractId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "loan_contract.pdf");
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }


    @GetMapping(value = "/contracts/loan-contracts/{loanContractId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getLoanContractPDF(@PathVariable Long loanContractId){
        byte[] pdfBytes = loanContractService.getLoanContractPDF(loanContractId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "loan_contract.pdf");
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}
