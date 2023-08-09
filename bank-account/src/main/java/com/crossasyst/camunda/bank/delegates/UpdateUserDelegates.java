package com.crossasyst.camunda.bank.delegates;

import com.crossasyst.camunda.bank.model.Address;
import com.crossasyst.camunda.bank.model.Contact;
import com.crossasyst.camunda.bank.model.Identification;
import com.crossasyst.camunda.bank.model.User;
import com.crossasyst.camunda.bank.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class UpdateUserDelegates implements JavaDelegate {

    private final UserService userService;

    private final ObjectMapper objectMapper;

    @Autowired
    public UpdateUserDelegates(UserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        log.info("Inside Create person delegates method.");
        log.info("Delegates values {} ", execution.getVariables());

        Long userId = (Long) execution.getVariable("userid");
        User user = objectMapper.convertValue(execution.getVariables(), User.class);
        Address address = objectMapper.convertValue(execution.getVariables(), Address.class);
        Contact contact = objectMapper.convertValue(execution.getVariables(), Contact.class);
        Identification identification = objectMapper.convertValue(execution.getVariables(), Identification.class);

        user.getAddress().forEach(address1 -> address.setUser(user));
        user.getContact().forEach(contact1 -> contact.setUser(user));
        user.setIdentificationDocuments(identification);

        userService.updateUser(user, userId);
        log.info("Create person delegates method executed.");
    }
}
