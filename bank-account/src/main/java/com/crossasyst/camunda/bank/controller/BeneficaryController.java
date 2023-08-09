package com.crossasyst.camunda.bank.controller;

import com.crossasyst.camunda.bank.model.BeneficaryAccount;
import com.crossasyst.camunda.bank.service.BeneficaryAccountService;
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
public class BeneficaryController {

    private final BeneficaryAccountService beneficaryAccountService;

    @Autowired
    public BeneficaryController(BeneficaryAccountService beneficaryAccountService) {
        this.beneficaryAccountService = beneficaryAccountService;
    }

    @PostMapping(value = "/add-beneficary", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BeneficaryAccount> addBeneficary(@RequestBody @Valid BeneficaryAccount beneficaryAccount){
         beneficaryAccount = beneficaryAccountService.addBeneficary(beneficaryAccount);
        return new ResponseEntity<>(beneficaryAccount, HttpStatus.OK);
    }
}
