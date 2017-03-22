package com.devefx.validation.constraints.impl;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import com.devefx.validation.ConstraintValidator;
import com.devefx.validation.Script;

public class OnlyFrontValidator extends ConstraintValidator implements Script {

    private ConstraintValidator validator;
    
    public OnlyFrontValidator(ConstraintValidator validator) {
        super(validator.getError().getCode(), validator.getError().getMessage());
        this.validator = validator;
    }

    @Override
    public boolean isValid(Map<String,Object> requestBody) throws Exception {
        return true;
    }

    @Override
    public void output(Writer out) throws IOException {
        if (validator instanceof Script) {
            ((Script) validator).output(out);
        }
    }
}
