package com.example.JOBSHOP.JOBSHOP.Validators.validatorsInterfaces.validatorsImpl;

import com.example.JOBSHOP.JOBSHOP.Validators.validatorsInterfaces.anyValidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class anyValidatorImpl implements ConstraintValidator<anyValidator,String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// Our custom validation logic here
		return false;
	}

}
