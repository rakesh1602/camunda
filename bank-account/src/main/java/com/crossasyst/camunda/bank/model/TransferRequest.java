package com.crossasyst.camunda.bank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequest {

    @NotNull(message = "Sender Account Number cannot be null")
    private Long senderAccountNumber;

    @NotNull(message = "Beneficiary Account Number cannot be null")
    private Long beneficiaryAccountNumber;

    @NotNull(message = "Amount cannot be null")
    private Double amount;
}
