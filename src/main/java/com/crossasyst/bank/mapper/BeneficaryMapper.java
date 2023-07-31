package com.crossasyst.bank.mapper;

import com.crossasyst.bank.entity.BeneficaryAccountEntity;
import com.crossasyst.bank.model.BeneficaryAccount;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BeneficaryMapper {
    BeneficaryAccountEntity modelToEntity(BeneficaryAccount beneficaryAccount);
}
