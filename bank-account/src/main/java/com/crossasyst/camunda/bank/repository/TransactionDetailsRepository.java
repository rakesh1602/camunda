package com.crossasyst.camunda.bank.repository;

import com.crossasyst.camunda.bank.entity.TransactionDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDetailsRepository extends JpaRepository<TransactionDetailsEntity, Long> {
}
