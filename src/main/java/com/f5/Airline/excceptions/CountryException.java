package com.f5.Airline.excceptions;

public class CountryException extends RuntimeException {
    public CountryException(String message) {
        super(message);

    }
    public CountryException(String message, Throwable cause) {
        super(message, cause);
    }

}
