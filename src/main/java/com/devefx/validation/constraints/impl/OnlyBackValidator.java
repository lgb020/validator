package com.devefx.validation.constraints.impl;

import java.util.Map;

import com.devefx.validation.ConstraintValidator;

public class OnlyBackValidator extends ConstraintValidator {

    private ConstraintValidator validator;
    
    public OnlyBackValidator(ConstraintValidator validator) {
        super(validator.getError().getCode(), validator.getError().getMessage());
        this.validator = validator;
    }

    @Override
    public boolean isValid(Map<String,Object> requestBody) throws Exception {
        return validator.isValid(requestBody);
    }

}
