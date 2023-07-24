package com.crossasyst.camunda.crud.delegate;

import com.crossasyst.camunda.crud.model.Address;
import com.crossasyst.camunda.crud.model.Person;
import com.crossasyst.camunda.crud.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Log4j2
public class CreatePersonDelegate implements JavaDelegate {

    private final PersonService personService;
    private final ObjectMapper objectMapper;

    @Autowired
    public CreatePersonDelegate(PersonService personService, ObjectMapper objectMapper) {
        this.personService = personService;
        this.objectMapper = objectMapper;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("Inside create person delegate method.");
        log.info("Delegate values {} ", delegateExecution.getVariables());

        Person person = objectMapper.convertValue(delegateExecution.getVariables(), Person.class);
        Address address = objectMapper.convertValue(delegateExecution.getVariables(), Address.class);

        // Get the existing list of addresses from the Person object or create a new list if it's null
        List<Address> addresses = person.getAddress();
        if (addresses == null) {
            addresses = new ArrayList<>();
        }

        // Add the new address to the list of addresses
        addresses.add(address);

        // Set the updated list of addresses in the Person object
        person.setAddress(addresses);

        personService.createPerson(person);
        log.info("Create person method using java delegates executed.");
    }
}
