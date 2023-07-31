package com.crossasyst.bank.repository;

import com.crossasyst.bank.entity.BeneficaryAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficaryAccountRepository extends JpaRepository<BeneficaryAccountEntity, Long> {
}
