package com.crossasyst.camunda.repository;

import com.crossasyst.camunda.entity.LoanContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanContractRepository extends JpaRepository<LoanContractEntity, Long> {
}
