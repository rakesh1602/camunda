package com.crossasyst.camunda.controller;

import com.crossasyst.camunda.service.CreditWorthinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreditWorthinessController {

    private final CreditWorthinessService creditWorthinessService;

    @Autowired
    public CreditWorthinessController(CreditWorthinessService creditWorthinessService) {
        this.creditWorthinessService = creditWorthinessService;
    }

    @GetMapping(value = "/accounts/{accountNumber}/credit-worthiness")
    public ResponseEntity<Boolean> checkCreditWorthiness(@PathVariable Long accountNumber) {
        Boolean isCreditWorthy = creditWorthinessService.checkCreditWorthiness(accountNumber);
        return new ResponseEntity<>(isCreditWorthy, HttpStatus.OK);
    }
}
