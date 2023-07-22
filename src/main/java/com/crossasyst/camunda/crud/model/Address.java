package com.crossasyst.camunda.crud.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.camunda.bpm.engine.impl.identity.PasswordPolicyResultImpl;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String addressLineOne;

    private String addressLineTwo;

    private String city;

    private String state;

    private String zipCode;

    private Person person;
}
