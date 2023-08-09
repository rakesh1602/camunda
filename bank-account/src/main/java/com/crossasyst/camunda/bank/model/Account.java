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
public class Account {

    @NotNull(message = "Account type cannot be null")
    private String accountType;

    @NotNull(message = "isActive cannot be null")
    private boolean isActive;

    @NotNull(message = "currentBalance cannot be null")
    private double currentBalance;

    private User user;

    private TranscationDetails transcationDetails;
}
