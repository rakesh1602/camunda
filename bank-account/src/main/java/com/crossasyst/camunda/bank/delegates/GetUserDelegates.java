package com.crossasyst.camunda.bank.delegates;

import com.crossasyst.camunda.bank.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class GetUserDelegates implements JavaDelegate {

    private final UserService userService;

    @Autowired
    public GetUserDelegates(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        log.info("Inside Get user delegates method.");

        Long userId = (Long) execution.getVariable("userid");
        userService.getUserById(userId);

        log.info("Get user delegate method executed.");
    }
}
