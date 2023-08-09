package com.crossasyst.camunda.bank.delegates;

import com.crossasyst.camunda.bank.model.Account;
import com.crossasyst.camunda.bank.model.Address;
import com.crossasyst.camunda.bank.model.Contact;
import com.crossasyst.camunda.bank.model.Identification;
import com.crossasyst.camunda.bank.model.User;
import com.crossasyst.camunda.bank.response.UserResponse;
import com.crossasyst.camunda.bank.service.AccountService;
import com.crossasyst.camunda.bank.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@Log4j2
public class CreateUserDelegates implements JavaDelegate {

    private final UserService userService;

    private final AccountService accountService;

    private final ObjectMapper objectMapper;

    @Autowired
    public CreateUserDelegates(UserService userService, AccountService accountService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.accountService = accountService;
        this.objectMapper = objectMapper;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        log.info("Inside Create person delegates method.");
        log.info("Delegates values {} ", execution.getVariables());

        User user = objectMapper.convertValue(execution.getVariables(), User.class);
        Address address = objectMapper.convertValue(execution.getVariables(), Address.class);
        Contact contact = objectMapper.convertValue(execution.getVariables(), Contact.class);
        Identification identification = objectMapper.convertValue(execution.getVariables(), Identification.class);
        Account account = objectMapper.convertValue(execution.getVariables(), Account.class);

        user.setAddress(Collections.singletonList(address));
        user.setContact(Collections.singletonList(contact));
        user.setIdentificationDocuments(identification);
        user.setAccount(account);

        user.getAddress().forEach(address1 -> address.setUser(user));
        user.getContact().forEach(contact1 -> contact.setUser(user));
        user.getIdentificationDocuments().setUser(user);
        user.getAccount().setUser(user);

        UserResponse userResponse = userService.createUser(user);

        Account account1 = user.getAccount();
        Long accountId = accountService.createAccount(account, userResponse.getId()).getId();

        execution.setVariable("userResponse", accountId);
        log.info("Create user method using java delegates executed.");
    }
}
