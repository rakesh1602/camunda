package com.crossasyst.camunda.mapper;

import com.crossasyst.camunda.entity.LoanContractEntity;
import com.crossasyst.camunda.model.LoanContract;
import org.mapstruct.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Mapper(componentModel = "spring")
public interface LoanContractMapper {

    LoanContract modelToEntity(LoanContractEntity loanContractEntity);
}
