package com.crossasyst.camunda.crud.delegate;

import com.crossasyst.camunda.crud.model.Address;
import com.crossasyst.camunda.crud.model.Person;
import com.crossasyst.camunda.crud.service.PersonService;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Log4j2
public class UpdatePersonDelegate implements JavaDelegate {
    private final PersonService personService;

    @Autowired
    public UpdatePersonDelegate(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("Inside delete person delegate method");

        log.info("Delegate values {} ", delegateExecution.getVariables());

        Long personId = (Long) delegateExecution.getVariable("personid");

        String firstName = (String) delegateExecution.getVariable("firstName");
        String lastName = (String) delegateExecution.getVariable("lastName");

        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);

        String addressLineOne = (String) delegateExecution.getVariable("addressLineOne");
        String addressLineTwo = (String) delegateExecution.getVariable("addressLineTwo");
        String city = (String) delegateExecution.getVariable("city");
        String state = (String) delegateExecution.getVariable("state");
        String zipCode = (String) delegateExecution.getVariable("zipCode");

        // Create the Address object using the process variables
        Address address = new Address();
        address.setAddressLineOne(addressLineOne);
        address.setAddressLineTwo(addressLineTwo);
        address.setCity(city);
        address.setState(state);
        address.setZipCode(zipCode);

        // Get the existing list of addresses from the Person object or create a new list if it's null
        List<Address> addresses = person.getAddress();
        if (addresses == null) {
            addresses = new ArrayList<>();
        }

        // Add the new address to the list of addresses
        addresses.add(address);

        // Set the updated list of addresses in the Person object
        person.setAddress(addresses);

        personService.updatePerson(person, personId);
        log.info("update person method using java delegates executed.");
    }
}
