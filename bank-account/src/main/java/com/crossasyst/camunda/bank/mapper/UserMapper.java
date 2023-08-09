package com.crossasyst.camunda.bank.mapper;

import com.crossasyst.camunda.bank.entity.UserEntity;
import com.crossasyst.camunda.bank.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "address" , target =  "addressEntities")
    @Mapping(source = "contact" , target =  "contactEntities")
    @Mapping(source = "identificationDocuments" , target =  "identificationEntity")
    UserEntity modelToEntity(User user);

    @Mapping(target = "address" , source =  "addressEntities")
    @Mapping(target = "contact" , source =  "contactEntities")
    @Mapping(target = "identificationDocuments" , source =  "identificationEntity")
    User entityToModel(UserEntity userEntity);
}
