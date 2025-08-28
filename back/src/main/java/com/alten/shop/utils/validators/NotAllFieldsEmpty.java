package com.alten.shop.utils.validators;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotAllFieldsEmptyValidator.class)
public @interface NotAllFieldsEmpty {

    String message() default "At least one field must be provided for the partial update";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
