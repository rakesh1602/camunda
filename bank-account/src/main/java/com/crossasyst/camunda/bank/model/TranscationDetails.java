package com.crossasyst.camunda.bank.model;

import com.crossasyst.camunda.bank.entity.AccountEntity;
import com.crossasyst.camunda.bank.entity.BeneficaryAccountEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TranscationDetails {

    private double amount;

    private boolean status;

    private LocalDateTime timestamp;


    private AccountEntity senderAccount;

    private BeneficaryAccountEntity beneficiaryAccount;
}
