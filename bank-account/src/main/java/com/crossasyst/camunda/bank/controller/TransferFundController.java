package com.crossasyst.camunda.bank.controller;

import com.crossasyst.camunda.bank.model.TransferRequest;
import com.crossasyst.camunda.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
public class TransferFundController {

    private final TransactionService transactionService;

    @Autowired
    public TransferFundController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(value = "/transfer-funds", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> transferFund(@RequestBody @Valid TransferRequest transferRequest){
        boolean transferStatus = transactionService.transferFunds(transferRequest);
        return new ResponseEntity<>(transferStatus, HttpStatus.OK);
    }
}
