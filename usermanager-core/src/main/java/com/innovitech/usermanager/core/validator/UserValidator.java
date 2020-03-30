package com.innovitech.usermanager.core.validator;

import com.innovitech.usermanager.api.exception.CustomException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import java.util.regex.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ApplicationScoped
public class UserValidator {

    private String name;

    private String email;

    private String password;


    public void isValidEmail() {
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        boolean isValid = pattern.matcher(this.email).find();
        if (!isValid) {
            throw new CustomException("Nem megfelelő email formátum", 400);
        }
    }

    public void isValidPassword() {
        if (password == null || this.password.trim().length() < 5) {
            throw new CustomException("A jelszónak legalább 3 hosszúnak kell lennie", 400);
        }
    }

    public void isValidName() {
        if (name == null || this.name.trim().length() <= 0) {
            throw new CustomException("A nevet meg kell adni", 400);
        }
    }

    public void validateAll() {
        isValidEmail();
        isValidName();
        isValidPassword();
    }
}
