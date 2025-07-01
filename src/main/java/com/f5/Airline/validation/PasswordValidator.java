package com.f5.Airline.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator <ValidPassword, String>{

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) return false;

        return  password.length() >= 6 &&
                password.matches(".*[a-z].*") &&    // Al menos una minúscula
                password.matches(".*\\d.*");  // Al menos un número
    }
}
