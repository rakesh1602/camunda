package com.crossasyst.camunda.controller;

import com.crossasyst.camunda.model.ClaimRequest;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MedicalClaimController {


    private final RuntimeService runtimeService;

    @Autowired
    public MedicalClaimController(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @PostMapping("/startClaimProcess")
    public void startClaimProcess(@RequestBody ClaimRequest request) {
        // Prepare variables to be passed to the process instance
        Map<String, Object> variables = new HashMap<>();
        variables.put("age", request.getAge());

        // Start the mediclaim process instance and pass the required variables.
        runtimeService.startProcessInstanceByKey("Process_0drwsk0", variables);
    }
}


