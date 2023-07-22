package com.crossasyst.camunda.crud.delegate;

import com.crossasyst.camunda.crud.model.Person;
import com.crossasyst.camunda.crud.service.PersonService;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class DeletePersonDelegate implements JavaDelegate {

    private final PersonService personService;

    @Autowired
    public DeletePersonDelegate(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("Inside DeletePersonDelegate method.");

        Long personId = (Long) delegateExecution.getVariable("personid");

        personService.deletePerson(personId);
        log.info("DeletePersonDelegate method executed.");
    }
}
