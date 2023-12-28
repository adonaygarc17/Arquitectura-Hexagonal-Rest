package org.acme.application.interfaces.validator.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraintvalidation.SupportedValidationTarget;
import jakarta.validation.constraintvalidation.ValidationTarget;
import org.acme.application.interfaces.validator.ProductRangeValidate;


@ApplicationScoped
@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class ProductRangeValidator implements ConstraintValidator<ProductRangeValidate, Object []> {

    @Override
    public boolean isValid(Object[] value, ConstraintValidatorContext context){
        context.disableDefaultConstraintViolation();
        int limit = (int) value[0];
        int skip = (int) value[1];
        if(!assertValidLimitRange(limit)){
            context
                    .buildConstraintViolationWithTemplate("Rango de limite invalido, debe ser entre 1 y 100 ")
                    .addParameterNode(0)
                    .addPropertyNode("limit")
                    .addConstraintViolation();
            return false;
        }
        if(!assertValidSikpRange(limit, skip)){
            context
                    .buildConstraintViolationWithTemplate("Skip debe ser entre el rango de 0 a (100-limit)" )
                    .addParameterNode(1)
                    .addPropertyNode("skip")
                    .addConstraintViolation();
            return  false;

        }
        return true;
    }

    private boolean assertValidLimitRange(int limit) {return  limit >= 1  && limit <= 100;}

    private boolean assertValidSikpRange (int limit ,int skip) {return  skip >= 0 && skip <= (100 - limit);}
}
