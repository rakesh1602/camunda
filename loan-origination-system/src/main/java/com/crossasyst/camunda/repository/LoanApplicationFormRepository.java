package com.crossasyst.camunda.repository;

import com.crossasyst.camunda.entity.LoanApplicationFormEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanApplicationFormRepository extends JpaRepository<LoanApplicationFormEntity, Long> {
}
