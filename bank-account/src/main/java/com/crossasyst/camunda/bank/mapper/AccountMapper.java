package com.crossasyst.camunda.bank.mapper;

import com.crossasyst.camunda.bank.entity.AccountEntity;
import com.crossasyst.camunda.bank.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(source = "user", target = "userEntity")
    AccountEntity modelToEntity(Account account);

    @Mapping(source = "userEntity", target = "user")
    Account entityToModel(AccountEntity accountEntity);
}
