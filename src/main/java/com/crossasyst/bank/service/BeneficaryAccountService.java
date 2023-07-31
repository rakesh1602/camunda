package com.crossasyst.bank.service;

import com.crossasyst.bank.entity.BeneficaryAccountEntity;
import com.crossasyst.bank.mapper.BeneficaryMapper;
import com.crossasyst.bank.model.BeneficaryAccount;
import com.crossasyst.bank.repository.BeneficaryAccountRepository;
import com.crossasyst.bank.response.UserResponse;
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
