package com.crossasyst.camunda.crud.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "person")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long personId;

    private String firstName;

    private String lastName;

    @OneToMany(mappedBy = "personEntity", cascade = CascadeType.ALL)
    private List<AddressEntity> addressEntity;
}
