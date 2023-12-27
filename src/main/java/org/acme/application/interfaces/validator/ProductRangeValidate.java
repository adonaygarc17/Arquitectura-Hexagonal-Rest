package org.acme.application.interfaces.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.acme.application.interfaces.validator.impl.ProductRangeValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProductRangeValidator.class)
public @interface ProductRangeValidate {

    String message() default "";

    Class<?>[] groups() default {};

    Class< ? extends Payload>[] payload() default {};

}
