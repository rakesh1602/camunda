package com.crossasyst.camunda.controller;

import com.crossasyst.camunda.service.LoanApplicationFormService;
import com.crossasyst.camunda.model.LoanApplicationForm;
import com.crossasyst.camunda.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
public class LoanApplicationFormController {

    private final LoanApplicationFormService loanApplicationFormService;

    @Autowired
    public LoanApplicationFormController(LoanApplicationFormService loanApplicationFormService) {
        this.loanApplicationFormService = loanApplicationFormService;
    }

    @PostMapping(value = "/users/{userId}/loan-application-forms", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> createLoanApplicationForm(@PathVariable Long userId, @RequestBody @Valid LoanApplicationForm loanApplicationForm){
        Response response = loanApplicationFormService.createLoanApplicationForm(userId,loanApplicationForm);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
