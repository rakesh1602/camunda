package com.crossasyst.camunda.mapper;

import com.crossasyst.camunda.entity.LoanApplicationFormEntity;
import com.crossasyst.camunda.model.LoanApplicationForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoanApplicationFormMapper {

    LoanApplicationFormEntity modelToEntity (LoanApplicationForm loanApplicationForm);
}
