package com.innovitech.usermanager.api.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddressVO {

    private Long id;

    private Integer postalCode;

    private String city;

    private String street;

    private Integer houseNumber;

    private UserVO user;

}