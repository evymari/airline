package com.f5.Airline.validation;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
