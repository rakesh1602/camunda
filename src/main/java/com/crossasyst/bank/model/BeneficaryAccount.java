package com.crossasyst.bank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BeneficaryAccount {

    @NotNull(message = "Beneficiary Account Number cannot be null")
    private Long beneficiaryAccountNo;

    @NotNull(message = "Beneficiary Full Name cannot be null")
    private String beneficiaryFullName;

    @NotNull(message = "Beneficiary IFSC Code cannot be null")
    private String beneficiaryIFSCCode;

    @NotNull(message = "Beneficiary Account Type cannot be null")
    private String beneficiaryAccountType;
}
