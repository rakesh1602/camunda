package com.crossasyst.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class UserEntity {

    @Id
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userEntity")
    private List<AddressEntity> addressEntities;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userEntity")
    private List<ContactEntity> contactEntities;

    @OneToOne(cascade = CascadeType.ALL)
    private IdentificationEntity identificationEntity;

    @OneToOne(cascade = CascadeType.ALL)
    private AccountEntity accountEntity;
}
