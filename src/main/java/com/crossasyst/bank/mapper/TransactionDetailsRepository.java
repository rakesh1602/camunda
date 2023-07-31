package com.crossasyst.bank.mapper;

import com.crossasyst.bank.entity.TransactionDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDetailsRepository extends JpaRepository<TransactionDetailsEntity, Long> {
}
