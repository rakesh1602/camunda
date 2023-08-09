package com.crossasyst.camunda.bank.mapper;

import com.crossasyst.camunda.bank.entity.BeneficaryAccountEntity;
import com.crossasyst.camunda.bank.model.BeneficaryAccount;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BeneficaryMapper {
    BeneficaryAccountEntity modelToEntity(BeneficaryAccount beneficaryAccount);
}
