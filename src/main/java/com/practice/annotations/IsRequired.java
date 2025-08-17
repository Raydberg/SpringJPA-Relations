package com.practice.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Se ejecuta en tiempo de ejecucion
@Retention(RetentionPolicy.RUNTIME)
// Encima de quien lo vamos anotar
@Target({ElementType.FIELD, ElementType.METHOD})
@Constraint(validatedBy = RequiredValidation.class)
public @interface IsRequired {
    String message() default "El campo es requerido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
