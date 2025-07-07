package com.f5.Airline.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UsernameValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUsername {
    String message() default "El nombre de usuario debe tener al menos 4 caracteres y solo contener letras, números o guiones bajos.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
