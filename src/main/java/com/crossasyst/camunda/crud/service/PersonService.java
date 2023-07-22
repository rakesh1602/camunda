package com.crossasyst.camunda.crud.service;

import com.crossasyst.camunda.crud.entity.AddressEntity;
import com.crossasyst.camunda.crud.entity.PersonEntity;
import com.crossasyst.camunda.crud.mapper.PersonMapper;
import com.crossasyst.camunda.crud.model.Person;
import com.crossasyst.camunda.crud.repository.PersonRepository;
import com.crossasyst.camunda.crud.response.PersonResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class PersonService {

    private final PersonMapper personMapper;

    private final PersonRepository personRepository;

    private Person person = new Person();

    @Autowired
    public PersonService(PersonMapper personMapper, PersonRepository personRepository) {
        this.personMapper = personMapper;
        this.personRepository = personRepository;
    }

    public PersonResponse createPerson(Person person) {
        log.info("Inside the create person class");

        PersonEntity personEntity = personMapper.modelToEntity(person);
        List<AddressEntity> addressEntities = personEntity.getAddressEntity();

        if (addressEntities != null) {
            for (AddressEntity addressEntity : addressEntities) {
                addressEntity.setPersonEntity(personEntity);
            }
        }
        personRepository.save(personEntity);
        log.info("Person details saved successfully.");

        PersonResponse personResponse = new PersonResponse();
        personResponse.setPersonId(personEntity.getPersonId());
        log.info("Person id is : {}", personResponse.getPersonId());

        return personResponse;
    }

    public Person getPerson(Long personid) {

        log.info("Retrieving person details of person id {}", personid);

        Optional<PersonEntity> optionalPersonEntity = personRepository.findById(personid);

        if(optionalPersonEntity.isPresent()){
            person = personMapper.entityToModel(optionalPersonEntity.get());
        } else {
            throw new RuntimeException("Person details of person id "+ personid + " not found");
        }
        return person;
    }

    public Person updatePerson(Person person, Long personid) {

        log.info("Retrieving person details of person id {}", personid);

        Optional<PersonEntity> optionalPersonEntity = personRepository.findById(personid);

        Long personId = optionalPersonEntity.get().getPersonId();
        PersonEntity personEntity = optionalPersonEntity.get();

        PersonEntity newPersonEntity = personMapper.modelToEntity(person);
        newPersonEntity.setPersonId(personId);
        newPersonEntity.setAddressEntity(personEntity.getAddressEntity());
        return person;
    }

    public void deletePerson(Long personid) {

        log.info("Deleting person details for person id {}", personid);

        personRepository.deleteById(personid);
        log.info("Person details deleted for person id {}", personid);
    }
}
