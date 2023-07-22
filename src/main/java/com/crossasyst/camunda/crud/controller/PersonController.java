package com.crossasyst.camunda.crud.controller;

import com.crossasyst.camunda.crud.model.Person;
import com.crossasyst.camunda.crud.response.PersonResponse;
import com.crossasyst.camunda.crud.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "/v1")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping(value = "/persons", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonResponse> createPerson(@RequestBody Person person){
        PersonResponse personResponse = personService.createPerson(person);
        return new ResponseEntity<>(personResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/persons/{personid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> getPerson(@PathVariable Long personid){
        Person person = personService.getPerson(personid);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }
    
    @PutMapping(value = "/persons/{personid}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long personid, @RequestBody Person person){
        person = personService.updatePerson(person, personid);
        return new ResponseEntity<>( person, HttpStatus.OK);
    }

    @DeleteMapping(value = "/persons/{personid}")
    public void deletePerson(@PathVariable Long personid){
        personService.deletePerson(personid);
    }
}
