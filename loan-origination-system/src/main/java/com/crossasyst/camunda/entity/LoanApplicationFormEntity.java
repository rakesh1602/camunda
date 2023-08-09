package com.crossasyst.camunda.entity;

import com.crossasyst.camunda.bank.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "loan_application_form")
public class LoanApplicationFormEntity {

    @Id
    @SequenceGenerator(sequenceName = "loan_application_form_id", name = "loan_application_form_id", initialValue = 1000, allocationSize = 1)
    @GeneratedValue(generator = "loan_application_form_id", strategy = GenerationType.SEQUENCE)
    private Long applicationFormNo;

    private String applicantName;

    private String contactNumber;

    private String email;

    private String address;

    private Date birthDate;

    private String employmentStatus;

    private double annualIncome;

    private double loanAmount;

    private int loanTermMonths;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
}
