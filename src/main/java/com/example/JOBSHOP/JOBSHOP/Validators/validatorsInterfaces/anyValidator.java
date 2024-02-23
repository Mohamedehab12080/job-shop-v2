package com.example.JOBSHOP.JOBSHOP.Validators.validatorsInterfaces;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.example.JOBSHOP.JOBSHOP.Validators.validatorsInterfaces.validatorsImpl.anyValidatorImpl;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = {anyValidatorImpl.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface anyValidator {

		String message() default "{validation.constraint.any-Validator.message}";
		Class<?>[] groups() default{ };
		Class<?extends Payload>[] payload() default{ };
}
