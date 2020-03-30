package com.innovitech.usermanager.core.database.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "address")
@ToString
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "postal_code")
    private Integer postalCode;

    private String city;

    private String street;

    @Column(name = "house_number")
    private Integer houseNumber;

    @ManyToOne
    private User user;

}
