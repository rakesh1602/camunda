package com.crossasyst.camunda.model;

import com.crossasyst.camunda.bank.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanApplicationForm {

    @NotBlank(message = "Applicant name cannot be blank.")
    private String applicantName;

    private String contactNumber;

    private String email;

    private String address;

    private Date birthDate;

    private String employmentStatus;

    private double annualIncome;

    private double loanAmount;

    private int loanTermMonths;

    private Long userId;
}
