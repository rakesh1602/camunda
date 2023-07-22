package com.crossasyst.camunda.crud.mapper;

import com.crossasyst.camunda.crud.entity.PersonEntity;
import com.crossasyst.camunda.crud.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    @Mapping(source = "address", target = "addressEntity")
    PersonEntity modelToEntity(Person person);

    @Mapping(source = "addressEntity", target = "address")
    Person entityToModel(PersonEntity personEntity);
}
