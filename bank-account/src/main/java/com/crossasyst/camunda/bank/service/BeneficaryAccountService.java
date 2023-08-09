package com.crossasyst.camunda.bank.service;

import com.crossasyst.camunda.bank.entity.BeneficaryAccountEntity;
import com.crossasyst.camunda.bank.mapper.BeneficaryMapper;
import com.crossasyst.camunda.bank.repository.BeneficaryAccountRepository;
import com.crossasyst.camunda.bank.model.BeneficaryAccount;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class BeneficaryAccountService {

    private final BeneficaryMapper beneficaryMapper;

    private final BeneficaryAccountRepository beneficaryAccountRepository;

    @Autowired
    public BeneficaryAccountService(BeneficaryMapper beneficaryMapper, BeneficaryAccountRepository beneficaryAccountRepository) {
        this.beneficaryMapper = beneficaryMapper;
        this.beneficaryAccountRepository = beneficaryAccountRepository;
    }

    public BeneficaryAccount addBeneficary(BeneficaryAccount beneficaryAccount) {

        log.info("Adding beneficary details");

        BeneficaryAccountEntity beneficaryAccountEntity = beneficaryMapper.modelToEntity(beneficaryAccount);
        beneficaryAccountRepository.save(beneficaryAccountEntity);
        log.info("Beneficary added successfully. Beneficary account No and name is {} {}", beneficaryAccount.getBeneficiaryAccountNo(), beneficaryAccount.getBeneficiaryFullName());

        return beneficaryAccount;
    }
}
