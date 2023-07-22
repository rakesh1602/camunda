package com.crossasyst.camunda.crud.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;

    private String addressLineOne;

    private String addressLineTwo;

    private String city;

    private String state;

    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "person_id",referencedColumnName = "personId")
    private PersonEntity personEntity;
}
