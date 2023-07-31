package com.crossasyst.bank.mapper;

import com.crossasyst.bank.entity.AccountEntity;
import com.crossasyst.bank.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(source = "user", target = "userEntity")
    AccountEntity modelToEntity(Account account);
}
