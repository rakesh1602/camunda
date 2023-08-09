package com.crossasyst.camunda.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "beneficary_account")
public class BeneficaryAccountEntity {

    @Id
    @Column(name = "beneficiary_account_no")
    private Long beneficiaryAccountNo;

    @Column(name = "beneficiary_full_name")
    private String beneficiaryFullName;

    @Column(name = "beneficiary_ifsc_code")
    private String beneficiaryIFSCCode;

    @Column(name = "beneficiary_account_type")
    private String beneficiaryAccountType;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountNumber")
    private AccountEntity accountEntity;

    @OneToMany(mappedBy = "beneficiaryAccount", cascade = CascadeType.ALL)
    private List<TransactionDetailsEntity> receivedTransactions;
}
