package com.crossasyst.camunda.entity;

import com.crossasyst.camunda.bank.entity.AccountEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "loan_contract_entity")
public class LoanContractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long loanContractId;

    @Column(name = "loan_draft", columnDefinition = "bytea")
    private byte[] loanContactDraft;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_number", referencedColumnName = "account_number")
    private AccountEntity accountEntity;
}
