package com.innovitech.usermanager.core.validator;

import com.innovitech.usermanager.api.exception.CustomException;
import com.innovitech.usermanager.api.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ApplicationScoped
public class AddressValidator {

    private Long id;

    private Integer postalCode;

    private String city;

    private String street;

    private Integer houseNumber;

    private UserVO user;


    public void isValidPostalCode() {
        if (postalCode == null || postalCode<=0) {
            throw new CustomException("Nem megfelelő az irányítószám vagy nincs megadva",400);
        }
    }

    public void isValidCity() {
        if (city == null || city.trim().length()==0) {
            throw new CustomException("A város nevét meg kell adni.",400);
        }
    }

    public void isValidStreet() {
        if (street == null || street.trim().length()==0) {
            throw new CustomException("A utca nevét meg kell adni.",400);
        }
    }

    public void isValidHouseNumber() {
        if (houseNumber == null || houseNumber<0 ) {
            throw new CustomException("A házszám nem megfelelő vagy nincs megadva",400);
        }
    }

    public void isValidUser() {
        if (user == null) {
            throw new CustomException("A címet hozzá kell rendelni egy felhasnzálóhoz",400);
        }
    }

    public void validateAll() {
        isValidCity();
        isValidHouseNumber();
        isValidPostalCode();
        isValidStreet();
        isValidUser();
    }

}
