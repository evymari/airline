package com.f5.Airline.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<ValidUsername, String> {

    private static final String USERNAME_REGEX = "^[A-Za-z0-9_]{4,}$";

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return username != null && username.matches(USERNAME_REGEX);
    }
}
