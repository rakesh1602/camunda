package com.crossasyst.bank.entity;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class AccountEntity {

    @Id
    @SequenceGenerator(sequenceName = "account_no_seq", name = "account_no_seq", initialValue = 1111111110, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_no_seq")
    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "current_balance")
    private double currentBalance;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "senderAccount", cascade = CascadeType.ALL)
    private List<TransactionDetailsEntity> sentTransactions;

    @OneToMany(mappedBy = "beneficiaryAccount", cascade = CascadeType.ALL)
    private List<TransactionDetailsEntity> receivedTransactions;
}
