package com.devefx.validation.constraints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.devefx.validation.ConstraintValidator;

/**
 * FieldValidator
 * Created by YYQ on 2016/5/26.
 */
public abstract class FieldValidator extends ConstraintValidator {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected final String field;

    public FieldValidator(String field, String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
        this.field = field;
    }
 
}
