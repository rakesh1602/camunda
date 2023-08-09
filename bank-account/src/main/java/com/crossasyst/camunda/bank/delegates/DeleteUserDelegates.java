package com.crossasyst.camunda.bank.delegates;

import com.crossasyst.camunda.bank.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class DeleteUserDelegates implements JavaDelegate {

    private final UserService userService;

    @Autowired
    public DeleteUserDelegates(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        log.info("Inside delete user execute method.");
        Long userId = (Long) execution.getVariable("userid");

        userService.deleteUser(userId);
        log.info("Delete execution method executed.");
    }
}
