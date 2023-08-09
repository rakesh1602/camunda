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
public class Address {

    @NotNull(message = "Address Line One cannot be null")
    private String addressLineOne;

    @NotNull(message = "Address Line Two cannot be null")
    private String addressLineTwo;

    @NotNull(message = "City cannot be null")
    private String city;

    @NotNull(message = "State cannot be null")
    private String state;

    private User user;
}
