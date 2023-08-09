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
public class Contact {

    @NotNull(message = "Contact Number cannot be null")
    private String contactNo;

    @NotNull(message = "User cannot be null")
    private User user;
}
