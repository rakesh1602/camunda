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
public class Identification {

    @NotNull(message = "Document Number cannot be null")
    private String documentNumber;

    @NotNull(message = "User cannot be null")
    private User user;
}
